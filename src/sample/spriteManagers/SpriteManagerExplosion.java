package sample.spriteManagers;

import javafx.scene.image.Image;

/**
 * Created by Vlad on 28.02.2016.
 */
public class SpriteManagerExplosion extends SpriteManager {
    public SpriteManagerExplosion(double duration) {
        super(duration);
        size[0] = 40;
        size[1] = 40;
    }

    @Override
    public void loadImages() {
        addSprite("explosion1/1.png");
        addSprite("explosion1/2.png");
        addSprite("explosion1/3.png");
        addSprite("explosion1/4.png");
    }
}
