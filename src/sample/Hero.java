package sample;

import javafx.scene.shape.Polygon;
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

    public boolean pickUp;

    public boolean stopMoveLeft = false;
    public boolean stopMoveUp = false;
    public boolean stopMoveRight = false;
    public boolean stopMoveDown = false;

    public double timeOfLastAttack = -1;
    public Rectangle collider;

    public Weapon weapon;
    public Polygon colliderWeapon;
    public boolean lefMouseClicked;
    public boolean attack;

    public boolean drawShotState = false;

    // Shot animation parameters
    public int indexOfAnimation = 0;
    public int durationShotAnimation = 1;
    public final int constDuration = 1;

    public Hero() {
        xPosHero = 500;
        yPosHero = 399;
        collider = new Rectangle(xPosHero-10, yPosHero-10, 20, 20);
        weapon = new Weapon(4);
        colliderWeapon = new Polygon();
        setColliderWeapon();
    }

    private void setColliderWeapon() {
        double w = weapon.getColliderWidth();
        double h = weapon.getColliderHeight();
        colliderWeapon = new Polygon(
                xPosHero - h*Math.cos(angle) + w/2*Math.cos(angle + Math.PI/2),
                yPosHero - h*Math.sin(angle) + w/2*Math.sin(angle + Math.PI/2),
                xPosHero - h*Math.cos(angle) + w/2*Math.cos(angle - Math.PI/2),
                yPosHero - h*Math.sin(angle) + w/2*Math.sin(angle - Math.PI/2),
                xPosHero + w/2*Math.cos(angle - Math.PI/2),
                yPosHero + w/2*Math.sin(angle - Math.PI/2),
                xPosHero + w/2*Math.cos(angle + Math.PI/2),
                yPosHero + w/2*Math.sin(angle + Math.PI/2));
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

        setColliderWeapon();

    }
}
