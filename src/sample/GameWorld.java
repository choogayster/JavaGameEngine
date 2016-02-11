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

    private int tileSize;
    private Image heroTexture;
    private Rectangle heroCollider;
    private Hero hero;

    public GameWorld() {
        level = new Level1();
        level = new Level1();
        tileSize = 60;
        hero = new Hero();
        heroTexture = new Image(getClass().getResource( "textures/hero.png ").toExternalForm());
        heroCollider = new Rectangle(
                hero.xPosHero - heroTexture.getWidth()/2 ,
                hero.yPosHero - heroTexture.getHeight()/2 ,
                heroTexture.getWidth(),
                heroTexture.getHeight());

    }

    //  UPDATE GAME WORLD!!!
    public void update() {
        Rectangle heroColliderCheck = new Rectangle(
                hero.xPosHero - heroCollider.getWidth()/2,
                hero.yPosHero - heroCollider.getHeight()/2,
                heroCollider.getWidth(),
                heroCollider.getHeight());
        if (hero.MoveLeft) {
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
        }
        hero.update();

    }


    public int getTileSize() {
        return tileSize;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHeroNewCollider() {
        heroCollider = new Rectangle(
                hero.xPosHero - heroTexture.getHeight()/2,
                hero.yPosHero - heroTexture.getWidth()/2,
                heroTexture.getHeight(),
                heroTexture.getWidth());
    }

    public Image getHeroTexture () {
        return heroTexture;
    }


/*  DEBUG ONLY!!!
    public Rectangle getHeroColliderRect () {
        return heroCollider;
    }

    public Rectangle getWallColliderRect () {
        return colliders.get(0);
    }*/
}
