package sample;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
        addSprite("textures/aim/aim.png");
        addSprite("textures/aim/aim1.png");
        addSprite("textures/aim/aim2.png");
        addSprite("textures/aim/aim1.png");
    }
}
