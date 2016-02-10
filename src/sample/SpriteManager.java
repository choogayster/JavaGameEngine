package sample;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vlad on 09.02.2016.
 */
public abstract class SpriteManager {

    List<Image> sprites;
    double duration;
    int size[];

    public SpriteManager() {
        sprites = new ArrayList<>();
        duration = 0.017;
        size = new int[2];
        loadImages();
    }

    // Extended classes must overide this method!!!
    abstract public void loadImages ();

    public void addSprite (String imageName) {
        Image image = new Image(getClass().getResource( imageName ).toExternalForm());
        sprites.add(image);
    }

    public void deleteSprite (int index) {
        sprites.remove(index);
    }

    public void setSprite (String imageName, int index) {
        Image image = new Image(getClass().getResource( imageName ).toExternalForm());
        sprites.set(index, image);
    }

    public Image getSprite(double time) {
        int index = (int)((time % (sprites.size() * duration)) / duration);
        return sprites.get(index);
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public int[] getSize() {
        return size;
    }

    public void setSize(int h, int w) {
        size[0] = h;
        size[1] = w;
    }

}
