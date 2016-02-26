package sample;

import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import java.util.Random;

/**
 * Created by Vlad on 12.02.2016.
 */
public class Enemy {
    private EnemyRails enemyRails;
    private EnemyRails pathToHero;
    private int currentNumberOfPoint = 0;
    private EnemyRails.Point currentPointOnRails;
    private double xPos;
    private double yPos;
    private double width;
    private double height;
    //private Rectangle collider;
    private Polygon collider;

    private double velocity = 2;

    public boolean MoveLeft;
    public boolean MoveUp;
    public boolean MoveRight;
    public boolean MoveDown;

    private boolean inAttackState = false;

    double angle = Math.PI / 2;

    public Enemy(int xPos, int yPos, int w, int h, EnemyRails enemyRails) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = w;
        this.height = h;
        //collider = new Rectangle(xPos-width, yPos-height, width, height);

        collider = new Polygon(
                xPos - h/2*Math.cos(angle) + w/2*Math.cos(angle + Math.PI/2),
                yPos - h/2*Math.sin(angle) + w/2*Math.sin(angle + Math.PI/2),
                xPos - h/2*Math.cos(angle) + w/2*Math.cos(angle - Math.PI/2),
                yPos - h/2*Math.sin(angle) + w/2*Math.sin(angle - Math.PI/2),
                xPos + h/2*Math.cos(angle)  + w/2*Math.cos(angle - Math.PI/2),
                yPos + h/2*Math.sin(angle) + w/2*Math.sin(angle - Math.PI/2),
                xPos + h/2*Math.cos(angle) + w/2*Math.cos(angle + Math.PI/2),
                yPos + h/2*Math.sin(angle) + w/2*Math.sin(angle + Math.PI/2));

        this.enemyRails = enemyRails;
        currentPointOnRails = enemyRails.getPoint(0);
    }

    public Enemy(int w, int h, EnemyRails enemyRails) {
        this.width = w;
        this.height = h;
        //collider = new Rectangle(xPos-width, yPos-height, width, height);
        collider = new Polygon(
                xPos - h/2*Math.cos(angle) + w/2*Math.cos(angle + Math.PI/2),
                yPos - h/2*Math.sin(angle) + w/2*Math.sin(angle + Math.PI/2),
                xPos - h/2*Math.cos(angle) + w/2*Math.cos(angle - Math.PI/2),
                yPos - h/2*Math.sin(angle) + w/2*Math.sin(angle - Math.PI/2),
                xPos + h/2*Math.cos(angle)  + w/2*Math.cos(angle - Math.PI/2),
                yPos + h/2*Math.sin(angle) + w/2*Math.sin(angle - Math.PI/2),
                xPos + h/2*Math.cos(angle) + w/2*Math.cos(angle + Math.PI/2),
                yPos + h/2*Math.sin(angle) + w/2*Math.sin(angle + Math.PI/2));
        this.enemyRails = enemyRails;
        currentPointOnRails = enemyRails.getPoint(0);
        this.xPos = currentPointOnRails.x;
        this.yPos = currentPointOnRails.y;
    }

    public void update() {
        // If enemy not attacks hero
        if (!inAttackState) {
            // Approximation of X enemy's coordinates
            if (Math.abs(currentPointOnRails.x - xPos) < 7) {
                xPos = currentPointOnRails.x;
            }
            // Approximation of Y enemy's coordinates
            if (Math.abs(currentPointOnRails.y - yPos) < 7) {
                yPos = currentPointOnRails.y;
            }
            // If current point is reached, generate index of next point
            if (xPos == currentPointOnRails.x && yPos == currentPointOnRails.y) {
                Random rand = new Random();
                int nextStep = rand.nextInt(2);
                currentPointOnRails = currentPointOnRails.next.get(nextStep);
            }
            // Change enemy's state to moving, if current point isn't reached
            else {
                if (currentPointOnRails.x > xPos) {
                    MoveLeft = false;
                    MoveRight = true;
                } else if (currentPointOnRails.x < xPos) {
                    MoveRight = false;
                    MoveLeft = true;
                }
                if (currentPointOnRails.y > yPos) {
                    MoveUp = false;
                    MoveDown = true;
                }
                if (currentPointOnRails.y < yPos) {
                    MoveDown = false;
                    MoveUp = true;
                }
            }
            // Change enemy's position
            if (MoveLeft) {
                xPos -= velocity;
            }
            if (MoveUp) {
                yPos -= velocity;
            }
            if (MoveRight) {
                xPos += velocity;
            }
            if (MoveDown) {
                yPos += velocity;
            }
            // Setting new collider's position
            /*collider.setX(xPos - width);
            collider.setY(yPos - height);*/
            collider = new Polygon(
                    xPos - height/2*Math.cos(angle) + width/2*Math.cos(angle + Math.PI/2),
                    yPos - height/2*Math.sin(angle) + width/2*Math.sin(angle + Math.PI/2),
                    xPos - height/2*Math.cos(angle) + width/2*Math.cos(angle - Math.PI/2),
                    yPos - height/2*Math.sin(angle) + width/2*Math.sin(angle - Math.PI/2),
                    xPos + height/2*Math.cos(angle)  + width/2*Math.cos(angle - Math.PI/2),
                    yPos + height/2*Math.sin(angle) + width/2*Math.sin(angle - Math.PI/2),
                    xPos + height/2*Math.cos(angle) + width/2*Math.cos(angle + Math.PI/2),
                    yPos + height/2*Math.sin(angle) + width/2*Math.sin(angle + Math.PI/2));
        }
        // If enemy attacks hero
        else {
            // Setting new collider's position
            /*collider.setX(xPos - width);
            collider.setY(yPos - height);*/
            collider = new Polygon(
                    xPos - height/2*Math.cos(angle) + width/2*Math.cos(angle + Math.PI/2),
                    yPos - height/2*Math.sin(angle) + width/2*Math.sin(angle + Math.PI/2),
                    xPos - height/2*Math.cos(angle) + width/2*Math.cos(angle - Math.PI/2),
                    yPos - height/2*Math.sin(angle) + width/2*Math.sin(angle - Math.PI/2),
                    xPos + height/2*Math.cos(angle)  + width/2*Math.cos(angle - Math.PI/2),
                    yPos + height/2*Math.sin(angle) + width/2*Math.sin(angle - Math.PI/2),
                    xPos + height/2*Math.cos(angle) + width/2*Math.cos(angle + Math.PI/2),
                    yPos + height/2*Math.sin(angle) + width/2*Math.sin(angle + Math.PI/2));
        }
    }

    public double getyPos() {
        return yPos;
    }

    public double getxPos() {
        return xPos;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public Polygon getCollider() {
        return collider;
    }

    public boolean isInAttackState() {
        return inAttackState;
    }

    public void setInAttackState(boolean inAttackState) {
        this.inAttackState = inAttackState;
    }

    public void buildPathToHero(double xPosHero, double yPosHero) {
        // TODO
    }
}
