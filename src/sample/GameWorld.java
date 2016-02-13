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
        }

        for (Bullet bullet : bullets) {
            bullet.move(time);
        }


        // Check colision
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
                Rectangle rect = level.enemies.get(i).getCollider();
                Enemy enemy = level.enemies.get(i);
                if (bullets.get(i).collider.getBoundsInParent().intersects(rect.getBoundsInParent())) {
                    flagOfMustRemoved = true;
                    level.enemies.remove(enemy);
                }
            }

            // Check collision between bullet and bounds of location
            if (bullets.get(i).xPos > 1000 || bullets.get(i).yPos > 1000 || bullets.get(i).xPos < 0 || bullets.get(i).yPos < 0) {
                flagOfMustRemoved = true;
            }
            // Remove collistion if was collision
            if (flagOfMustRemoved) {
                bullets.remove(i);
            }
        }


        // Check hero's collision
        Rectangle heroColliderCheck = hero.collider;

        if (hero.MoveLeft) {
            heroColliderCheck.setX(heroColliderCheck.getX()-8);
            for (Level.Wall wall : level.walls) {
                if (heroColliderCheck.getBoundsInParent().intersects(wall.getCollider().getBoundsInParent())) {
                    hero.MoveLeft = false;
                }
            }
        }
        if (hero.MoveRight) {
            heroColliderCheck.setX(heroColliderCheck.getX()+8);
            for (Level.Wall wall : level.walls) {
                if (heroColliderCheck.getBoundsInParent().intersects(wall.getCollider().getBoundsInParent())) {
                    hero.MoveRight = false;
                }
            }
        }
        if (hero.MoveUp) {
            heroColliderCheck.setY(heroColliderCheck.getY()-8);
            for (Level.Wall wall : level.walls) {
                if (heroColliderCheck.getBoundsInParent().intersects(wall.getCollider().getBoundsInParent())) {
                    hero.MoveUp = false;
                }
            }
        }
        if (hero.MoveDown) {
            heroColliderCheck.setY(heroColliderCheck.getY()+8);
            for (Level.Wall wall : level.walls) {
                if (heroColliderCheck.getBoundsInParent().intersects(wall.getCollider().getBoundsInParent())) {
                    hero.MoveDown = false;
                }
            }
        }
        hero.update();

    }

    public Hero getHero() {
        return hero;
    }

    public Image getHeroTexture () {
        return heroTexture;
    }

}
