package sample;

import javafx.scene.shape.Rectangle;

/**
 * Created by vlad on 14.03.2016.
 */
public class Armor {
    public double xPos;
    public double yPos;
    public Rectangle collider;
    public int armor;
    public int id; // level of armor
    public double chance; // chance to block damage
    final public int colliderWidth = 40;
    final public int colliderHeight = 40;

    // Properties for animation
    public int duration = 3;
    public int step = 0;
    public boolean direction = true;


    public Armor (double xPos, double yPos, int id) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.id = id;
        collider = new Rectangle(xPos - colliderWidth/2, yPos - colliderHeight/2, colliderWidth, colliderHeight);
        switch (id) {
            case 1:
                armor = 10;
                chance = 0.3;
                break;
            case 2:
                armor = 10;
                chance = 0.5;
                break;
            case 3:
                armor = 20;
                chance = 0.4;
                break;
            case 4:
                armor = 20;
                chance = 0.6;
                break;
            case 5:
                armor = 30;
                chance = 0.5;
                break;
            case 6:
                armor = 30;
                chance = 0.7;
                break;
            default:
                armor = 30;
                chance = 0.7;
                break;
        }
    }
}
