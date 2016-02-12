package sample.spriteManagers;

import javafx.scene.image.Image;

/**
 * Created by Vlad on 09.02.2016.
 */
public class SpriteManagerAim extends SpriteManager {

    public SpriteManagerAim() {
        size[0] = 40;
        size[1] = 40;
    }

    @Override
    public void loadImages() {
        Image image;
        addSprite("aim/aim.png");
        addSprite("aim/aim1.png");
        addSprite("aim/aim2.png");
        addSprite("aim/aim1.png");
    }
}
