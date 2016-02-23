package sample;

import javafx.scene.shape.Rectangle;

/**
 * Created by Vlad on 10.02.2016.
 */
public class Hero {

    public int xPosHero;
    public int yPosHero;

    public boolean MoveLeft;
    public boolean MoveUp;
    public boolean MoveRight;
    public boolean MoveDown;

    public boolean stopMoveLeft = false;
    public boolean stopMoveUp = false;
    public boolean stopMoveRight = false;
    public boolean stopMoveDown = false;

    public boolean ShotState;
    public int spentTimeShot;
    public Rectangle collider;

    public Hero() {
        xPosHero = 500;
        yPosHero = 399;
        collider = new Rectangle(xPosHero-10, yPosHero-10, 25,35);
    }

    public void update(int heroSpeed) {
        if (spentTimeShot > 0) {
            spentTimeShot--;
        } else {
            ShotState = false;
        }

        if (MoveLeft && !stopMoveLeft) {
            xPosHero -= heroSpeed;
        }
        if (MoveUp && !stopMoveUp) {
            yPosHero -= heroSpeed;
        }
        if (MoveRight && !stopMoveRight) {
            xPosHero += heroSpeed;
        }
        if (MoveDown && !stopMoveDown) {
            yPosHero += heroSpeed;
        }

        // Setting new collider's position
        collider.setX(xPosHero-10);
        collider.setY(yPosHero-10);

    }
}
