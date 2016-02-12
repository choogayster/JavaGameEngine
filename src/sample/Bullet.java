package sample;

import javafx.scene.shape.Rectangle;

/**
 * Created by Vlad on 11.02.2016.
 */
public class Bullet {
    private double xPosHero;
    private double yPosHero;
    public double angle;
    public double xPos;
    public double yPos;
    public Rectangle collider;
    private double time;
    private final double velocity = 2000;
    private final int colliderSize = 6;


    public Bullet (double time, double xPos, double yPos, double angle) {
        this.time = time;
        this.xPosHero = xPos;
        this.yPosHero = yPos;
        this.xPos = xPos;
        this.yPos = yPos;
        this.angle = angle;
        collider = new Rectangle(xPos-colliderSize/2, yPos-colliderSize/2, colliderSize, colliderSize);
    }

    public void move(double time) {
        xPos = xPosHero + velocity * (this.time - time) * Math.sin(angle);
        yPos = yPosHero + velocity * (this.time - time) * Math.cos(angle);
        collider.setX(xPos);
        collider.setY(yPos);
    }

    public double[] getPosition () {
        return new double[] {xPos, yPos};
    }

}
