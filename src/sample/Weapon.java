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
    private int holder;
    private double attackSpread;
    private double attackDelay;
    private Rectangle collider; // Collider for melee attack weapon
    private double colliderLifetime;

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
                collider = new Rectangle(0, 0, 60, 40);
                break;
            case 1:
                // knife fighting
                meleeAttack = true;
                rangeAttack = false;
                attackDelay = 0.5;
                holder = Integer.MAX_VALUE;
                attackSpread = 0;
                collider = new Rectangle(0, 0, 70, 50);
                break;
            case 2:
                // baseball-bat
                meleeAttack = true;
                rangeAttack = false;
                attackDelay = 0.7;
                holder = Integer.MAX_VALUE;
                attackSpread = 0;
                collider = new Rectangle(0, 0, 80, 70);
                break;
            case 3:
                // baseball-bat
                meleeAttack = true;
                rangeAttack = false;
                attackDelay = 0.7;
                holder = Integer.MAX_VALUE;
                attackSpread = 0;
                collider = new Rectangle(0, 0, 80, 70);
                break;
            default:
                // hand-to-hand fighting by default
                meleeAttack = true;
                rangeAttack = false;
                nonKillingAttack = true;
                holder = Integer.MAX_VALUE;
                attackDelay = 0.8;
                attackSpread = 0;
                collider = new Rectangle(0, 0, 60, 40);

        }
    }
}
