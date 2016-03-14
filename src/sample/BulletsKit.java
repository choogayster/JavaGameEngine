package sample;

import javafx.scene.shape.Rectangle;

/**
 * Created by vlad on 14.03.2016.
 */
public class BulletsKit {
    public double xPos;
    public double yPos;
    public Rectangle collider;
    final public int colliderWidth = 40;
    final public int colliderHeight = 40;

    /*regenerate 30% bullets*/
    public BulletsKit (double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        collider = new Rectangle(xPos - colliderWidth / 2, yPos - colliderHeight / 2, colliderWidth, colliderHeight);
    }
}
