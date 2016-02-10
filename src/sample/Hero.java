package sample;

/**
 * Created by Vlad on 10.02.2016.
 */
public class Hero {

    public int xPos;
    public int yPos;

    public boolean MoveLeft;
    public boolean MoveUp;
    public boolean MoveRight;
    public boolean MoveDown;

    public Hero() {
        xPos = 300;
        yPos = 300;
    }

    public void update() {
        if (MoveLeft) {
            xPos-=7;
        }
        if (MoveUp) {
            yPos-=7;
        }
        if (MoveRight) {
            xPos+=7;
        }
        if (MoveDown) {
            yPos+=7;
        }
    }
}
