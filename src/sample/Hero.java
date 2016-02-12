package sample;

/**
 * Created by Vlad on 10.02.2016.
 */
public class Hero {

    public int xPosHero;
    public int yPosHero;

    public boolean MoveLeft;
    public boolean MoveUp;
    public boolean MoveRight;
    public boolean MoveDown;

    public boolean ShotState;
    public int spentTimeShot;

    public Hero() {
        xPosHero = 500;
        yPosHero = 399;
    }

    public void update() {
        if (spentTimeShot > 0) {
            spentTimeShot--;
        } else {
            ShotState = false;
        }
        if (MoveLeft) {
            xPosHero -= 8;
        }
        if (MoveUp) {
            yPosHero -= 8;
        }
        if (MoveRight) {
            xPosHero += 8;
        }

        if (MoveDown) {
            yPosHero += 8;
        }
    }
}
