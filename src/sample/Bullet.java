package sample;

/**
 * Created by Vlad on 11.02.2016.
 */
public class Bullet {
    private double xPosHero;
    private double yPosHero;
    public double angle;
    public double xPos;
    public double yPos;
    private double time;
    private double velocity;

    public Bullet (double time, double xPos, double yPos, double angle) {
        this.time = time;
        this.xPosHero = xPos;
        this.yPosHero = yPos;
        this.xPos = xPos;
        this.yPos = yPos;
        this.angle = angle;
        velocity = 3700;
    }

    public void move(double time) {
        xPos = xPosHero + velocity * (this.time - time) * Math.sin(angle);
        yPos = yPosHero + velocity * (this.time - time) * Math.cos(angle);
    }

    public double[] getPosition () {
        return new double[] {xPos, yPos};
    }

}
