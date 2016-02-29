package sample;

import javafx.scene.shape.Rectangle;

/**
 * Created by Vlad on 24.02.2016.
 */
public class Weapon {
    private int type;
    private boolean meleeAttack;
    private boolean rangeAttack;
    private boolean nonKillingAttack; // In case hand-to-hand fighting
    public int holder;
    private double attackSpread;
    private double attackDelay;
    private int colliderWidth; // Collider for melee attack weapon
    private int colliderHeight; // Collider for melee attack weapon

    private double bulletVelocity = 0;
    private double bulletDamage = 0;

    public double getBulletVelocity() {
        return bulletVelocity;
    }

    public double getBulletDamage() {
        return bulletDamage;
    }

    private double colliderActivityTime = 0.25;

    public int getColliderHeight() {
        return colliderHeight;
    }

    public int getColliderWidth() {
        return colliderWidth;
    }

    public Weapon(int type) {
        this.type = type;
        if (type != 0) {
            nonKillingAttack = false;
        } else {
            nonKillingAttack = true;
        }
        switch (type) {
            case 0:
                // hand-to-hand fighting
                meleeAttack = true;
                rangeAttack = false;
                holder = Integer.MAX_VALUE;
                attackSpread = 0;
                attackDelay = 0.8;
                colliderWidth =  70;
                colliderHeight = 50;
                break;
            case 1:
                // knife fighting
                meleeAttack = true;
                rangeAttack = false;
                attackDelay = 0.5;
                holder = Integer.MAX_VALUE;
                attackSpread = 0;
                colliderWidth =  70;
                colliderHeight = 60;
                break;
            case 2:
                // baseball-bat
                meleeAttack = true;
                rangeAttack = false;
                attackDelay = 0.7;
                holder = Integer.MAX_VALUE;
                attackSpread = 0;
                colliderWidth =  80;
                colliderHeight = 70;
                break;
            case 3:
                // weapon 1
                meleeAttack = false;
                rangeAttack = true;
                attackDelay = 0.1;
                holder = 1000;
                attackSpread = 0;
                colliderWidth =  15;
                colliderHeight = 40;
                bulletVelocity = 1500;
                bulletDamage = 1;
                break;
            case 4:
                // weapon 2
                meleeAttack = false;
                rangeAttack = true;
                attackDelay = 0.1;
                holder = 1000;
                attackSpread = 0;
                colliderWidth =  15;
                colliderHeight = 40;
                bulletVelocity = 1500;
                bulletDamage = 1;
                break;

        }
    }

    public double getAttackDelay() {
        return attackDelay;
    }

    public boolean isRangeAttack() {
        return rangeAttack;
    }

    public boolean isMeleeAttack() {
        return meleeAttack;
    }

    public double getColliderActivityTime() {
        return colliderActivityTime;
    }
}
