package sample;

import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

/**
 * Created by Vlad on 11.02.2016.
 */
public class Bullet {

    public boolean enemyBullet;
    public double angle;
    public double xPos;
    public double yPos;
    //public Line collider;
    public Polygon collider;
    private int h;
    private int w;
    private double xPosHero;
    private double yPosHero;
    private double time;
    private final double velocity = 1500;

    public Bullet (double time, double xPos, double yPos, double angle, int w, int h, boolean enemyBullet) {
        this.time = time;
        this.xPosHero = xPos;
        this.yPosHero = yPos;
        this.xPos = xPos;
        this.yPos = yPos;
        this.w = w;
        this.h = h;
        this.angle = angle;
        this.enemyBullet = enemyBullet;
        collider = new Polygon(
                xPos - h/2*Math.cos(angle) + w/2*Math.cos(angle + Math.PI/2),
                yPos - h/2*Math.sin(angle) + w/2*Math.sin(angle + Math.PI/2),
                xPos - h/2*Math.cos(angle) + w/2*Math.cos(angle - Math.PI/2),
                yPos - h/2*Math.sin(angle) + w/2*Math.sin(angle - Math.PI/2),
                xPos + h/2*Math.cos(angle)  + w/2*Math.cos(angle - Math.PI/2),
                yPos + h/2*Math.sin(angle) + w/2*Math.sin(angle - Math.PI/2),
                xPos + h/2*Math.cos(angle) + w/2*Math.cos(angle + Math.PI/2),
                yPos + h/2*Math.sin(angle) + w/2*Math.sin(angle + Math.PI/2));
    }

    public void move(double time) {
        // Set new bullet position
        xPos = xPosHero + velocity * (this.time - time) * Math.cos(angle);
        yPos = yPosHero + velocity * (this.time - time) * Math.sin(angle);
        // Set new collider's position
        /*collider.setStartX(xPos + 50*Math.cos(angle));
        collider.setStartY(yPos + 50*Math.sin(angle));
        collider.setEndX(xPos - Math.cos(angle));
        collider.setEndY(yPos - Math.sin(angle));*/
        collider = new Polygon(
                xPos - h/2*Math.cos(angle) + w/2*Math.cos(angle + Math.PI/2),
                yPos - h/2*Math.sin(angle) + w/2*Math.sin(angle + Math.PI/2),
                xPos - h/2*Math.cos(angle) + w/2*Math.cos(angle - Math.PI/2),
                yPos - h/2*Math.sin(angle) + w/2*Math.sin(angle - Math.PI/2),
                xPos + h/2*Math.cos(angle)  + w/2*Math.cos(angle - Math.PI/2),
                yPos + h/2*Math.sin(angle) + w/2*Math.sin(angle - Math.PI/2),
                xPos + h/2*Math.cos(angle) + w/2*Math.cos(angle + Math.PI/2),
                yPos + h/2*Math.sin(angle) + w/2*Math.sin(angle + Math.PI/2));
    }

}
