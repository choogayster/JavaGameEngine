package sample;

import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 * Created by Vlad on 11.02.2016.
 */
public class Bullet {

    public double angle;
    public double xPos;
    public double yPos;
    public Line collider;
    private double xPosHero;
    private double yPosHero;
    private double time;
    private final double velocity = 2000;

    public Bullet (double time, double xPos, double yPos, double angle) {
        this.time = time;
        this.xPosHero = xPos;
        this.yPosHero = yPos;
        this.xPos = xPos;
        this.yPos = yPos;
        this.angle = angle;
        collider = new Line(
                xPos + Math.cos(angle),
                yPos + Math.sin(angle),
                xPos - Math.cos(angle),
                yPos - Math.sin(angle));
    }

    public void move(double time) {
        // Set new bullet position
        xPos = xPosHero + velocity * (this.time - time) * Math.cos(angle);
        yPos = yPosHero + velocity * (this.time - time) * Math.sin(angle);
        // Set new collider's position
        collider.setStartX(xPos + Math.cos(angle));
        collider.setStartY(yPos + Math.sin(angle));
        collider.setEndX(xPos - Math.cos(angle));
        collider.setEndY(yPos - Math.sin(angle));
    }

}
