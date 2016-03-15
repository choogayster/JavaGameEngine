package sample;

import javafx.scene.shape.Rectangle;

/**
 * Created by vlad on 14.03.2016.
 */
public class MedicalKit {
    public double xPos;
    public double yPos;
    public Rectangle collider;
    public int regeneration;
    public int id; // level of regeneration
    final public int colliderWidth = 40;
    final public int colliderHeight = 40;

    // Properties for animation
    public int duration = 3;
    public int step = 0;
    public boolean direction = true;


    public MedicalKit(double xPos, double yPos, int id) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.id = id;
        collider = new Rectangle(xPos - colliderWidth/2, yPos - colliderHeight/2, colliderWidth, colliderHeight);
        switch (id) {
            case 1:
                regeneration = 1;
                break;
            case 2:
                regeneration = 2;
                break;
            case 3:
                regeneration = 3;
                break;
            default:
                regeneration = 3;
                break;
        }
    }
}
