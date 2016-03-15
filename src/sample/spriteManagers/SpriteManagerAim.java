package sample.spriteManagers;

import javafx.scene.image.Image;

/**
 * Created by Vlad on 09.02.2016.
 */
public class SpriteManagerAim extends SpriteManager {

    public SpriteManagerAim(double duration) {
        super(duration);
    }

    @Override
    public void loadImages() {
        Image image;
        addSprite("aim/1.png");
        addSprite("aim/2.png");
        addSprite("aim/3.png");
        addSprite("aim/4.png");
        addSprite("aim/5.png");
        addSprite("aim/4.png");
        addSprite("aim/3.png");
        addSprite("aim/2.png");

    }
}
