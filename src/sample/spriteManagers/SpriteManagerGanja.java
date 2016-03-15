package sample.spriteManagers;

import javafx.scene.image.Image;

/**
 * Created by vlad on 15.03.2016.
 */
public class SpriteManagerGanja extends SpriteManager {
    public SpriteManagerGanja(double v) {
        super(v);
    }
    @Override
    public void loadImages() {
        addSprite("ganja/ganja1.png");
        addSprite("ganja/ganja2.png");
        addSprite("ganja/ganja3.png");
        addSprite("ganja/ganja4.png");
        addSprite("ganja/ganja5.png");
        addSprite("ganja/ganja6.png");
        addSprite("ganja/ganja7.png");
        addSprite("ganja/ganja8.png");
        addSprite("ganja/ganja9.png");
    }
}
