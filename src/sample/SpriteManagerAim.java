package sample;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Vlad on 09.02.2016.
 */
public class SpriteManagerAim implements SpriteManager {

    List<Image> sprites;
    double duration;
    int size[];

    public SpriteManagerAim() {
        sprites = new ArrayList<>();
        duration = 0.017;
        size = new int[2];
        size[0] = 40;
        size[1] = 40;
        loadImages();

    }

    @Override
    public void loadImages() {
        Image image;
        image = new Image(getClass().getResource( "textures/aim/aim.png").toExternalForm());
        sprites.add(image);
        image = new Image(getClass().getResource( "textures/aim/aim1.png").toExternalForm());
        sprites.add(image);
        image = new Image(getClass().getResource( "textures/aim/aim2.png").toExternalForm());
        sprites.add(image);
        image = new Image(getClass().getResource( "textures/aim/aim1.png").toExternalForm());
        sprites.add(image);
    }

    @Override
    public void addSprite(String name) {

    }

    @Override
    public void deleteSprite(int index) {

    }

    @Override
    public void setSprite(Image image, int index) {

    }

    @Override
    public Image getSprite(double time) {
        int index = (int)((time % (sprites.size() * duration)) / duration);
        return sprites.get(index);
    }

    @Override
    public double getDuration() {
        return 0;
    }

    @Override
    public void setDuration(double duration) {
        this.duration = duration;
    }

    @Override
    public int[] getSize() {
        return size;
    }

    @Override
    public void setSize(int h, int w) {
        size[0] = h;
        size[1] = w;
    }

}
