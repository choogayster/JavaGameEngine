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
        addSprite("herowalk/1.png");
        addSprite("herowalk/2.png");
        addSprite("herowalk/3.png");
        addSprite("herowalk/4.png");
        addSprite("herowalk/5.png");
        addSprite("herowalk/4.png");
        addSprite("herowalk/3.png");
        addSprite("herowalk/2.png");
    }
}
