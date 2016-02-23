package sample;

import javafx.scene.shape.Rectangle;

import java.util.Random;

/**
 * Created by Vlad on 12.02.2016.
 */
public class Enemy {
    private EnemyRails enemyRails;
    private int currentNumberOfPoint = 0;
    private EnemyRails.Point currentPointOnRails;
    private double xPos;
    private double yPos;
    private double width;
    private double height;
    private Rectangle collider;

    private double velocity = 2;

    public boolean MoveLeft;
    public boolean MoveUp;
    public boolean MoveRight;
    public boolean MoveDown;

    public Enemy(int xPos, int yPos, int width, int height, EnemyRails enemyRails) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        collider = new Rectangle(xPos-width, yPos-height, width, height);
        this.enemyRails = enemyRails;
        currentPointOnRails = enemyRails.getPoint(0);
    }

    public Enemy(int width, int height, EnemyRails enemyRails) {
        this.width = width;
        this.height = height;
        collider = new Rectangle(xPos-width, yPos-height, width, height);
        this.enemyRails = enemyRails;
        currentPointOnRails = enemyRails.getPoint(0);
        this.xPos = currentPointOnRails.x;
        this.yPos = currentPointOnRails.y;
    }

    public void update() {
        if (Math.abs(currentPointOnRails.x - xPos) < 7) {
            xPos = currentPointOnRails.x;
        }
        if (Math.abs(currentPointOnRails.y - yPos) < 7) {
            yPos = currentPointOnRails.y;
        }
        if (xPos == currentPointOnRails.x && yPos == currentPointOnRails.y) {
            // If current point is reached, generate index of next point
            Random rand = new Random();
            int nextStep = rand.nextInt(2);
            currentPointOnRails = currentPointOnRails.next.get(nextStep);

        } else {
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
        if (MoveLeft) {
            xPos-=velocity;
        }
        if (MoveUp) {
            yPos-=velocity;
        }
        if (MoveRight) {
            xPos+=velocity;
        }
        if (MoveDown) {
            yPos+=velocity;
        }

        // Setting new collider's position
        collider.setX(xPos-width);
        collider.setY(yPos-height);

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

    public Rectangle getCollider() {
        return collider;
    }
}
