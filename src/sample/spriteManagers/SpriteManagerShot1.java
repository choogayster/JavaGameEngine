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
        addSprite("heroDefault/hero11.png");
        addSprite("heroDefault/hero12.png");
        addSprite("heroDefault/hero13.png");
        addSprite("heroDefault/hero14.png");
        addSprite("heroDefault/hero15.png");
        addSprite("heroDefault/hero16.png");
    }
}
