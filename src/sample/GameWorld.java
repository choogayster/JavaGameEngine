package sample;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import sample.level.Level;
import sample.level.Level1;

import java.util.*;

/**
 * Created by Vlad on 10.02.2016.
 */
public class GameWorld {

    public Level level;

    private Image heroTexture;
    private Rectangle heroCollider;
    private Hero hero;

    public List<Bullet> bullets;

    public GameWorld() {
        level = new Level1();
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

    //  UPDATE GAME WORLD!!!
    public void update(double time) {

        for (Enemy enemy : level.enemies) {
            enemy.update();
            //System.out.println(enemy.getxPos() + " " + enemy.getyPos());
        }

        for (Bullet bullet : bullets) {
            bullet.move(time);
        }

        boolean flagOfMustRemoved;
        for (int i = 0; i < bullets.size(); i++) {
            flagOfMustRemoved = false;
            for (Level.Wall wall : level.walls) {
                Rectangle rect = wall.getCollider();
                if (bullets.get(i).collider.getBoundsInParent().intersects(rect.getBoundsInParent())) {
                    flagOfMustRemoved = true;
                }
            }
            for (Enemy enemy : level.enemies) {
                Rectangle rect = enemy.getCollider();
                if (bullets.get(i).collider.getBoundsInParent().intersects(rect.getBoundsInParent())) {
                    System.out.println("intesect");
                    flagOfMustRemoved = true;
                }
            }
            if (bullets.get(i).xPos > 1000 || bullets.get(i).yPos > 1000 || bullets.get(i).xPos < 0 || bullets.get(i).yPos < 0) {
                flagOfMustRemoved = true;
                //bullets.remove(i);
            }
            if (flagOfMustRemoved) {
                bullets.remove(i);
            }
        }

        Rectangle heroColliderCheck = new Rectangle(
                hero.xPosHero - heroCollider.getWidth()/2,
                hero.yPosHero - heroCollider.getHeight()/2,
                heroCollider.getWidth(),
                heroCollider.getHeight());

        /*if (hero.MoveLeft) {
            heroColliderCheck.setX(heroColliderCheck.getX()-10);
        }
        if (hero.MoveRight) {
            heroColliderCheck.setX(heroColliderCheck.getX()+10);
        }
        if (hero.MoveUp) {
            heroColliderCheck.setY(heroColliderCheck.getY()-10);
        }
        if (hero.MoveDown) {
            heroColliderCheck.setY(heroColliderCheck.getY()+10);
        }
        for (Level.Wall wall : level.walls) {
            Rectangle rect = wall.getCollider();
            if (heroColliderCheck.getBoundsInParent().intersects(rect.getBoundsInParent())) {
                hero.MoveLeft = hero.MoveDown = hero.MoveRight = hero.MoveUp = false;
                return;
            }
        }*/
        if (hero.MoveLeft) {
            heroColliderCheck.setX(heroColliderCheck.getX()-8);
            for (Level.Wall wall : level.walls) {
                Rectangle rect = wall.getCollider();
                if (heroColliderCheck.getBoundsInParent().intersects(rect.getBoundsInParent())) {
                    hero.MoveLeft = false;
                    //return;
                }
            }
        }
        if (hero.MoveRight) {
            heroColliderCheck.setX(heroColliderCheck.getX()+8);
            for (Level.Wall wall : level.walls) {
                Rectangle rect = wall.getCollider();
                if (heroColliderCheck.getBoundsInParent().intersects(rect.getBoundsInParent())) {
                    hero.MoveRight = false;
                    //return;
                }
            }
        }
        if (hero.MoveUp) {
            heroColliderCheck.setY(heroColliderCheck.getY()-8);
            for (Level.Wall wall : level.walls) {
                Rectangle rect = wall.getCollider();
                if (heroColliderCheck.getBoundsInParent().intersects(rect.getBoundsInParent())) {
                    hero.MoveUp = false;
                    //return;
                }
            }
        }
        if (hero.MoveDown) {
            heroColliderCheck.setY(heroColliderCheck.getY()+8);
            for (Level.Wall wall : level.walls) {
                Rectangle rect = wall.getCollider();
                if (heroColliderCheck.getBoundsInParent().intersects(rect.getBoundsInParent())) {
                    hero.MoveDown = false;
                    //return;
                }
            }
        }
        hero.update();

    }

    public Hero getHero() {
        return hero;
    }

    public void setHeroNewCollider() {
        heroCollider = new Rectangle(
                hero.xPosHero - heroTexture.getHeight()/2/2,
                hero.yPosHero - heroTexture.getWidth()/2/2,
                heroTexture.getHeight()/2,
                heroTexture.getWidth()/2);
    }

    public Image getHeroTexture () {
        return heroTexture;
    }

}
