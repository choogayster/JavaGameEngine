package sample;

import javafx.scene.image.Image;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import sample.level.Level;
import sample.level.Level1;
import sample.level.Level2;

import java.util.*;

/**
 * Created by Vlad on 10.02.2016.
 */
public class GameWorld {

    private final int heroSpeed = 10;

    public Level level;

    private Hero hero;

    public boolean makeShake = false;

    public List<Bullet> bullets;
    public List<Event> events;
    public List<Enemy> enemies;
    public List<Armor> armors;
    public List<MedicalKit> medkits;

    GameBehavior behavior;

    public GameWorld(double startTime) {
        level = new Level2();
        hero = new Hero();
        bullets = new ArrayList<>();
        events = new ArrayList<>(1000);
        enemies = new ArrayList<>();
        behavior = new GameBehavior(this, startTime);
        //enemies.add(new Enemy(40, 40, level.rails.get(0), 69, getHero()));
    }

    public void addBullet (Bullet bullet) {
        bullets.add(bullet);
    }

    /*This function update game world*/
    public void update(double time) {

        //long t0 = System.currentTimeMillis();

        checkHeroAroundEnemys();
        //long t1 = System.currentTimeMillis();

        checkBulletCollision(time);
        //long t2 = System.currentTimeMillis();

        checkHeroCollision();
        //long t3 = System.currentTimeMillis();

        checkInactiveEvents();
        //long t4 = System.currentTimeMillis();

        for (Bullet bullet : bullets) {
            bullet.move(time);
        }
        //long t5 = System.currentTimeMillis();

        for (Enemy enemy : enemies) {
            enemy.update();
            if (enemy.inAttackState) {
                checkEnemyAttack(enemy, time);
            }
        }
        //long t6 = System.currentTimeMillis();

        hero.update(heroSpeed);
        //long t7 = System.currentTimeMillis();

        if (hero.lefMouseClicked == true) {
            checkAttack(time);
        }
        //long t8 = System.currentTimeMillis();

        if (hero.weapon.isMeleeAttack()) {
            if (hero.attack == true) {
                makeAttack(time);
            }
        }
        //long t9 = System.currentTimeMillis();

        /*System.out.println("[" + (t1-t0) + " " +(t2-t1)+ " " +(t3-t2)+ " "+(t4-t3)+ " "
s                +(t5-t4)+ " "+(t6-t5)+ " "+(t7-t6)+ " "+(t8-t7)+ " "+(t9-t8)+ "]");*/
        behavior.doBehavior(time);
    }

    private void checkInactiveEvents() {
        for(int i = 0; i<events.size(); i++) {
            if (events.get(i).active == false) {
                events.remove(i);
            }
        }
    }

    private void checkEnemyAttack(Enemy enemy, double time) {
        if (enemy.weapon.holder > 0) {
            if (enemy.timeOfLastAttack == -1 || (time - enemy.timeOfLastAttack) > enemy.weapon.getAttackDelay()) {
                enemy.attack = true;
                enemy.timeOfLastAttack = time;
                if (enemy.weapon.isRangeAttack()) {
                    makeEnemyAttack(enemy, time);
                }

            }
            if (time - enemy.timeOfLastAttack > enemy.weapon.getColliderActivityTime()) {
                enemy.attack = false;
            }
        }
    }

    private void makeEnemyAttack(Enemy enemy, double time) {
        if (enemy.weapon.isMeleeAttack()) {
            //
        }
        if (enemy.weapon.isRangeAttack()) {
            enemy.weapon.holder--;
            double angle = Math.PI*3/2 - Math.atan2(hero.xPosHero-enemy.getxPos(), hero.yPosHero-enemy.getyPos());
            addBullet(new Bullet(time, enemy.getxPos() - 10*Math.cos(enemy.angle), enemy.getyPos()- 10*Math.sin(enemy.angle), angle,
                    enemy.weapon.getColliderWidth(), enemy.weapon.getColliderHeight(), true,
                    enemy.weapon.getBulletVelocity(), enemy.weapon.getBulletDamage()));
        }
    }

    private void checkAttack(double time) {
        if (hero.weapon.holder > 0) {
            if (hero.timeOfLastAttack == -1 || (time - hero.timeOfLastAttack) > hero.weapon.getAttackDelay()) {
                hero.attack = true;
                hero.timeOfLastAttack = time;
                if (hero.weapon.isRangeAttack()) {
                    makeAttack(time);
                    hero.drawShotState = true;
                }
            }
            if (time - hero.timeOfLastAttack > hero.weapon.getColliderActivityTime()) {
                hero.attack = false;
            }
        }
    }

    private void makeAttack(double time) {
        if (hero.weapon.isMeleeAttack()) {
            checkHeroMeleeWeaponCollision();
        }
        if (hero.weapon.isRangeAttack()) {
            makeShake = true;
            hero.weapon.holder--;
            addBullet(new Bullet(time, hero.xPosHero - 20*Math.cos(hero.angle), hero.yPosHero - 20*Math.sin(hero.angle), hero.angle,
                    hero.weapon.getColliderWidth(), hero.weapon.getColliderHeight(), false,
                    hero.weapon.getBulletVelocity(), hero.weapon.getBulletDamage()));
        }
    }

    private void checkHeroAroundEnemys() {
        for (Enemy enemy : enemies) {
            // Create ray from enemy to hero
            Line line = new Line(enemy.getxPos(), enemy.getyPos(), hero.xPosHero, hero.yPosHero);
            // Intersection between ray (from enemy to hero) and wall
            boolean isIntersection = false;
            for (Level.Wall wall : level.walls) {
                // Check intersection between ray and walls
                if (line.getBoundsInParent().intersects(wall.getCollider().getBoundsInParent())) {
                    isIntersection = true;
                    break;
                }
            }
            if (isIntersection) {
                enemy.setInAttackState(false);
            } else {
                enemy.setInAttackState(true);
            }
        }
    }

    private void checkBulletCollision(double time) {
        for (int i = 0; i < bullets.size(); i++) {
            boolean flagOfMustRemoved = false;
            // Check collision between bullet and walls
            for (Level.Wall wall : level.walls) {
                Rectangle rect = wall.getCollider();
                if (bullets.get(i).collider.getBoundsInParent().intersects(rect.getBoundsInParent())) {
                    flagOfMustRemoved = true;
                    events.add(new Event(1, (int)bullets.get(i).xPos, (int)bullets.get(i).yPos));
                    break;
                }
            }
            // Check collision between bullet and enemies
            if (bullets.get(i).enemyBullet == false) {
                for (int j = 0; j < enemies.size(); j++) {
                    Polygon rect = enemies.get(j).getCollider();
                    if (bullets.get(i).collider.getBoundsInParent().intersects(rect.getBoundsInParent())) {
                        flagOfMustRemoved = true;
                        enemies.remove(j);
                    }
                }
            }

            // Remove bullet if was collision
            if (flagOfMustRemoved) {
                bullets.remove(i);
            }
        }
    }

    private void checkHeroCollision() {
        Rectangle heroColliderCheck = hero.collider;

        checkLeftCollision(heroColliderCheck);
        checkRightCollision(heroColliderCheck);
        checkUpCollision(heroColliderCheck);
        checkDownCollision(heroColliderCheck);

    }

    private void checkHeroMeleeWeaponCollision() {
        for (int j = 0; j < enemies.size(); j++) {
            Polygon rect = enemies.get(j).getCollider();
            if (hero.colliderWeapon.getBoundsInParent().intersects(rect.getBoundsInParent())) {
                enemies.remove(j);
                //System.out.println("intersects");
                //System.out.println(hero.colliderWeapon.getPoints());
            }
        }
    }

    private void checkLeftCollision(Rectangle heroColliderCheck) {
        if (hero.moveLeft) {
            heroColliderCheck.setX(heroColliderCheck.getX()-heroSpeed);
            boolean collision = false;
            for (Level.Wall wall : level.walls) {
                if (heroColliderCheck.getBoundsInParent().intersects(wall.getCollider().getBoundsInParent())) {
                    collision = true;
                }
            }
            if (collision) {
                hero.stopMoveLeft = true;
            } else {
                hero.stopMoveLeft = false;
            }
            heroColliderCheck.setX(heroColliderCheck.getX()+heroSpeed);
        }
    }

    private void checkRightCollision(Rectangle heroColliderCheck) {
        if (hero.moveRight) {
            heroColliderCheck.setX(heroColliderCheck.getX()+heroSpeed);
            boolean collision = false;
            for (Level.Wall wall : level.walls) {
                if (heroColliderCheck.getBoundsInParent().intersects(wall.getCollider().getBoundsInParent())) {
                    collision = true;
                }
            }
            if (collision) {
                hero.stopMoveRight = true;
            } else {
                hero.stopMoveRight = false;
            }
            heroColliderCheck.setX(heroColliderCheck.getX()-heroSpeed);
        }
    }

    private void checkUpCollision(Rectangle heroColliderCheck) {
        if (hero.moveUp) {
            heroColliderCheck.setY(heroColliderCheck.getY()-heroSpeed);
            boolean collision = false;
            for (Level.Wall wall : level.walls) {
                if (heroColliderCheck.getBoundsInParent().intersects(wall.getCollider().getBoundsInParent())) {
                    collision = true;
                }
            }
            if (collision) {
                hero.stopMoveUp = true;
            } else {
                hero.stopMoveUp = false;
            }
            heroColliderCheck.setY(heroColliderCheck.getY()+heroSpeed);
        }
    }

    private void checkDownCollision(Rectangle heroColliderCheck) {
        if (hero.moveDown) {
            heroColliderCheck.setY(heroColliderCheck.getY()+heroSpeed);
            boolean collision = false;
            for (Level.Wall wall : level.walls) {
                if (heroColliderCheck.getBoundsInParent().intersects(wall.getCollider().getBoundsInParent())) {
                    collision = true;
                }
            }
            if (collision) {
                hero.stopMoveDown = true;
            } else {
                hero.stopMoveDown = false;
            }
            heroColliderCheck.setY(heroColliderCheck.getY()-heroSpeed);
        }
    }


    public Hero getHero() {
        return hero;
    }

    class Event {
        public int id; // 1- explosion
        public int xPos;
        public int yPos;
        public boolean active;
        public int indexOfAnimation = 0;
        public int duration = 3;
        public final int constDuration = 3;
        public Event (int id, int x, int y) {
            this.id = id;
            this.xPos = x;
            this.yPos = y;
            active = true;
        }
    }
}
