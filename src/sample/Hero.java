package sample;

import javafx.scene.shape.Rectangle;

/**
 * Created by Vlad on 10.02.2016.
 */
public class Hero {

    public int xPosHero;
    public int yPosHero;
    public double angle;

    public boolean moveLeft;
    public boolean moveUp;
    public boolean moveRight;
    public boolean moveDown;

    public boolean stopMoveLeft = false;
    public boolean stopMoveUp = false;
    public boolean stopMoveRight = false;
    public boolean stopMoveDown = false;

    public boolean attack;

    public Rectangle collider;

    public Weapon weapon;

    public Hero() {
        xPosHero = 500;
        yPosHero = 399;
        collider = new Rectangle(xPosHero-10, yPosHero-10, 25,35);
        weapon = new Weapon(0);
    }

    public void update(int heroSpeed) {
        /*if (spentTimeShot > 0) {
            spentTimeShot--;
        } else {
            ShotState = false;
        }*/

        if (moveLeft && !stopMoveLeft) {
            xPosHero -= heroSpeed;
        }
        if (moveUp && !stopMoveUp) {
            yPosHero -= heroSpeed;
        }
        if (moveRight && !stopMoveRight) {
            xPosHero += heroSpeed;
        }
        if (moveDown && !stopMoveDown) {
            yPosHero += heroSpeed;
        }

        // Setting new collider's position
        collider.setX(xPosHero-10);
        collider.setY(yPosHero-10);

    }
}
