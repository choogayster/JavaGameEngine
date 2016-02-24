package sample;

import javafx.scene.image.Image;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import sample.level.Level;
import sample.level.Level1;

import java.util.*;

/**
 * Created by Vlad on 10.02.2016.
 */
public class GameWorld {

    private final int heroSpeed = 10;

    public Level level;

    private Image heroTexture;
    private Rectangle heroCollider;
    private Hero hero;

    public List<Bullet> bullets;

    public GameWorld() {
        level = new Level1();
        hero = new Hero();
        heroTexture = new Image(getClass().getResource( "textures/hero02.png ").toExternalForm());
        heroCollider = new Rectangle(
                hero.xPosHero - heroTexture.getWidth()/2 ,
                hero.yPosHero - heroTexture.getHeight()/2 ,
                heroTexture.getWidth(),
                heroTexture.getHeight());
        bullets = new ArrayList<>();
    }

    public void addBullet (Bullet bullet) {
        bullets.add(bullet);
    }

    /*This function update game world*/
    public void update(double time) {


        checkHeroAroundEnemys();
        checkBulletCollision();
        checkHeroCollision();

        for (Bullet bullet : bullets) {
            bullet.move(time);
        }

        for (Enemy enemy : level.enemies) {
            enemy.update();
        }

        hero.update(heroSpeed);

        if (hero.attack == true) {
            createAttack(time);
        }
    }

    private void checkHeroAroundEnemys() {
        for (Enemy enemy : level.enemies) {
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

    private void checkBulletCollision() {
        for (int i = 0; i < bullets.size(); i++) {
            boolean flagOfMustRemoved = false;
            // Check collision between bullet and walls
            for (Level.Wall wall : level.walls) {
                Rectangle rect = wall.getCollider();
                if (bullets.get(i).collider.getBoundsInParent().intersects(rect.getBoundsInParent())) {
                    flagOfMustRemoved = true;
                }
            }
            // Check collision between bullet and enemies
            for (int j = 0; j < level.enemies.size(); j++) {
                Rectangle rect = level.enemies.get(j).getCollider();
                if (bullets.get(i).collider.getBoundsInParent().intersects(rect.getBoundsInParent())) {
                    flagOfMustRemoved = true;
                    level.enemies.remove(j);
                }
            }

            // Check collision between bullet and bounds of location
            if (bullets.get(i).xPos > 1000 || bullets.get(i).yPos > 1000 || bullets.get(i).xPos < 0 || bullets.get(i).yPos < 0) {
                flagOfMustRemoved = true;
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

    private void createAttack(double time) {
        addBullet( new Bullet(time, hero.xPosHero, hero.yPosHero, hero.angle));
    }

    public Hero getHero() {
        return hero;
    }

}
