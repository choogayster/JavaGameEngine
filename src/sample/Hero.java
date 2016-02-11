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

    public Hero() {
        xPosHero = 300;
        yPosHero = 300;
    }

    public void update() {
        if (MoveLeft) {
            xPosHero-=8;
        }
        if (MoveUp) {
            yPosHero-=8;
        }
        if (MoveRight) {
            xPosHero+=8;
        }
        if (MoveDown) {
            yPosHero+=8;
        }
    }
}
