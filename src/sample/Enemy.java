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
    private EnemyRails.Point currentPointOnRails;

    private double xPos;
    private double yPos;

    private double width;
    private double height;
    private Polygon collider;

    private double velocity = 2;

    public boolean MoveLeft;
    public boolean MoveUp;
    public boolean MoveRight;
    public boolean MoveDown;

    public double timeOfLastAttack = -1;
    public boolean inAttackState = false;
    public Weapon weapon;
    public Polygon colliderWeapon;
    public boolean lefMouseClicked;
    public boolean attack;

    public Hero target;

    double angle = Math.PI / 2;


    public Enemy(int w, int h, EnemyRails enemyRails, int startPoint, Hero target) {
        this.width = w;
        this.height = h;
        collider = new Polygon(
                xPos - h/2*Math.cos(angle) + w/2*Math.cos(angle + Math.PI/2),
                yPos - h/2*Math.sin(angle) + w/2*Math.sin(angle + Math.PI/2),
                xPos - h/2*Math.cos(angle) + w/2*Math.cos(angle - Math.PI/2),
                yPos - h/2*Math.sin(angle) + w/2*Math.sin(angle - Math.PI/2),
                xPos + h/2*Math.cos(angle)  + w/2*Math.cos(angle - Math.PI/2),
                yPos + h/2*Math.sin(angle) + w/2*Math.sin(angle - Math.PI/2),
                xPos + h/2*Math.cos(angle) + w/2*Math.cos(angle + Math.PI/2),
                yPos + h/2*Math.sin(angle) + w/2*Math.sin(angle + Math.PI/2));

        weapon = new Weapon(3);

        this.enemyRails = enemyRails;
        currentPointOnRails = enemyRails.getPoint(startPoint);
        this.xPos = currentPointOnRails.x;
        this.yPos = currentPointOnRails.y;
        this.target = target;
    }

    public void update() {
        // If enemy not attacks hero
        if (!inAttackState) {
            // Approximation of X enemy's coordinates
            if (Math.abs(currentPointOnRails.x - xPos) < 3) {
                xPos = currentPointOnRails.x;
            }
            // Approximation of Y enemy's coordinates
            if (Math.abs(currentPointOnRails.y - yPos) < 3) {
                yPos = currentPointOnRails.y;
            }
            // If current point is reached, generate index of next point
            if (xPos == currentPointOnRails.x && yPos == currentPointOnRails.y) {
                Random rand = new Random();
                int nextStep = rand.nextInt(currentPointOnRails.next.size());
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
            // Set angle
            if (MoveRight) {
                angle = Math.PI;
            }
            if (MoveLeft) {
                angle = 0.0;
            }
            if (MoveDown) {
                angle = -Math.PI/2;
            }
            if (MoveUp) {
                angle = Math.PI/2;
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
            angle = Math.PI*3/2 - Math.atan2(target.xPosHero-xPos, target.yPosHero-yPos);
            if (weapon.isMeleeAttack()) {
                xPos -= velocity*Math.cos(angle);
                yPos -= velocity*Math.sin(angle);
            }

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

    public void setInAttackState(boolean inAttackState) {
        this.inAttackState = inAttackState;
    }

}
