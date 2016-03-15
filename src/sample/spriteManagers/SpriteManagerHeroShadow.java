package sample.spriteManagers;

import javafx.scene.image.Image;
import sample.spriteManagers.SpriteManager;

/**
 * Created by vlad on 15.03.2016.
 */
public class SpriteManagerHeroShadow extends SpriteManager {
    public SpriteManagerHeroShadow(double v) {
        super(v);
    }
    public void loadImages() {
        addSprite("heroshadow/1.png");
        addSprite("heroshadow/2.png");
        addSprite("heroshadow/3.png");
    }
}
