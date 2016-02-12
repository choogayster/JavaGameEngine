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

    public boolean ShotState;
    public int spentTimeShot;
    public Rectangle collider;

    public Hero() {
        xPosHero = 500;
        yPosHero = 399;
        collider = new Rectangle(xPosHero-25, yPosHero-25, 50, 50);
    }

    public void update() {
        if (spentTimeShot > 0) {
            spentTimeShot--;
        } else {
            ShotState = false;
        }

        if (MoveLeft) {
            xPosHero -= 8;
        }
        if (MoveUp) {
            yPosHero -= 8;
        }
        if (MoveRight) {
            xPosHero += 8;
        }
        if (MoveDown) {
            yPosHero += 8;
        }

        // Setting new collider's position
        collider.setX(xPosHero-25);
        collider.setY(yPosHero-25);

    }
}
