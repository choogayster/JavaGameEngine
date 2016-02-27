package sample.spriteManagers;

/**
 * Created by Vlad on 16.02.2016.
 */
public class SpriteManagerHero extends SpriteManager {
    public SpriteManagerHero(double duration) {
        super(duration);
    }

    @Override
    public void loadImages() {
        addSprite("heroDefault/hero01.png");
        addSprite("heroDefault/hero02.png");
        addSprite("heroDefault/hero03.png");
        addSprite("heroDefault/hero04.png");
        addSprite("heroDefault/hero05.png");
        addSprite("heroDefault/hero06.png");
    }
}
