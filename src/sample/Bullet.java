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
    public Polygon collider;
    public int h;
    public int w;
    private double xPosHero;
    private double yPosHero;
    private double time;
    private double velocity = 1500;
    private double damage;
    // Parameters for updating bullet position and collider
    private double h_d_2cos;
    private double h_d_2sin;
    private double w_d_2cosAnlePlusPI_d_2;
    private double w_d_2sinAnlePlusPI_d_2;
    private double w_d_2cosAnleMinusPI_d_2;
    private double w_d_2sinAnleMinusPI_d_2;


    public Bullet (double time, double xPos, double yPos, double angle, int w, int h, boolean enemyBullet, double velocity, double damage ) {
        this.time = time;
        this.xPosHero = xPos;
        this.yPosHero = yPos;
        this.xPos = xPos;
        this.yPos = yPos;
        this.w = w;
        this.h = h;
        this.angle = angle;
        this.enemyBullet = enemyBullet;
        /*collider = new Polygon(
                xPos - h/2*Math.cos(angle) + w/2*Math.cos(angle + Math.PI/2),
                yPos - h/2*Math.sin(angle) + w/2*Math.sin(angle + Math.PI/2),
                xPos - h/2*Math.cos(angle) + w/2*Math.cos(angle - Math.PI/2),
                yPos - h/2*Math.sin(angle) + w/2*Math.sin(angle - Math.PI/2),
                xPos + h/2*Math.cos(angle)  + w/2*Math.cos(angle - Math.PI/2),
                yPos + h/2*Math.sin(angle) + w/2*Math.sin(angle - Math.PI/2),
                xPos + h/2*Math.cos(angle) + w/2*Math.cos(angle + Math.PI/2),
                yPos + h/2*Math.sin(angle) + w/2*Math.sin(angle + Math.PI/2));*/
        h_d_2cos = h/2*Math.cos(angle);
        h_d_2sin = h/2*Math.sin(angle);
        w_d_2cosAnlePlusPI_d_2 = w/2*Math.cos(angle + Math.PI/2);
        w_d_2sinAnlePlusPI_d_2 = w/2*Math.sin(angle + Math.PI/2);
        w_d_2cosAnleMinusPI_d_2 = w/2*Math.cos(angle - Math.PI/2);
        w_d_2sinAnleMinusPI_d_2 = w/2*Math.sin(angle - Math.PI/2);
        collider = new Polygon(
                xPos - h_d_2cos + w_d_2cosAnlePlusPI_d_2,
                yPos - h_d_2sin + w_d_2sinAnlePlusPI_d_2,
                xPos - h_d_2cos + w_d_2cosAnleMinusPI_d_2,
                yPos - h_d_2sin + w_d_2sinAnleMinusPI_d_2,
                xPos + h_d_2cos + w_d_2cosAnleMinusPI_d_2,
                yPos + h_d_2sin + w_d_2sinAnleMinusPI_d_2,
                xPos + h_d_2cos + w_d_2cosAnlePlusPI_d_2,
                yPos + h_d_2sin + w_d_2sinAnlePlusPI_d_2 );

        this.velocity = velocity;
        this.damage = damage;
    }

    public void move(double time) {
        // Set new bullet position
        xPos = xPosHero + velocity * (this.time - time) * Math.cos(angle);
        yPos = yPosHero + velocity * (this.time - time) * Math.sin(angle);
        // Set new collider's position
        collider = new Polygon(
                xPos - h_d_2cos + w_d_2cosAnlePlusPI_d_2,
                yPos - h_d_2sin + w_d_2sinAnlePlusPI_d_2,
                xPos - h_d_2cos + w_d_2cosAnleMinusPI_d_2,
                yPos - h_d_2sin + w_d_2sinAnleMinusPI_d_2,
                xPos + h_d_2cos + w_d_2cosAnleMinusPI_d_2,
                yPos + h_d_2sin + w_d_2sinAnleMinusPI_d_2,
                xPos + h_d_2cos + w_d_2cosAnlePlusPI_d_2,
                yPos + h_d_2sin + w_d_2sinAnlePlusPI_d_2 );
    }
}
