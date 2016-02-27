package sample.spriteManagers;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vlad on 09.02.2016.
 */
public abstract class SpriteManager {

    protected List<Image> sprites;
    protected double duration;
    protected int size[];
    protected boolean singleAnimation;
    public int index;

    public SpriteManager(double duration) {
        sprites = new ArrayList<>();
        this.duration = duration;
        size = new int[2];
        singleAnimation = false;
        loadImages();
        index = 0;
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
        if (!singleAnimation) {
            int index = (int) ((time % (sprites.size() * duration)) / duration);
            return sprites.get(index);
        } else {
            int index = (int) ((time % (sprites.size() * duration)) / duration);
            return sprites.get(index);
        }
    }

    public int indexOfCurrentSprite(double time) {
        int index = (int) ((time % (sprites.size() * duration)) / duration);
        return index;
    }

    public Image getSpriteById(int index) {
        return sprites.get(index);
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public int getCollectionSize() {
        return sprites.size();
    }

    public void setSingleAnimation(boolean singleAnimation) {
        this.singleAnimation = singleAnimation;
    }

}
