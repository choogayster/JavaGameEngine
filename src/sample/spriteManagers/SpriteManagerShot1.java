package sample.spriteManagers;

/**
 * Created by Vlad on 12.02.2016.
 */
public class SpriteManagerShot1 extends SpriteManager {

    public SpriteManagerShot1(double duration) {
        super(duration);
    }
    @Override
    public void loadImages() {
        addSprite("heroshot/1.png");
        addSprite("heroshot/2.png");
        addSprite("heroshot/3.png");
        addSprite("heroshot/4.png");
        addSprite("heroshot/5.png");
        addSprite("heroshot/6.png");
    }
}
