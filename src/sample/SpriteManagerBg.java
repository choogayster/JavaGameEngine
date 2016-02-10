package sample;

/**
 * Created by Vlad on 10.02.2016.
 */
public class SpriteManagerBg extends SpriteManager {
    public SpriteManagerBg() {
        size[0] = 700;
        size[1] = 700;
    }
    @Override
    public void loadImages() {
        for (int i = 1; i <= 42; i++) {
            if (i < 10) {
                addSprite("textures/bg/backgroundSprite0" + i + ".jpg");
            }
            else {
                addSprite("textures/bg/backgroundSprite" + i + ".jpg");
            }
        }
    }
}
