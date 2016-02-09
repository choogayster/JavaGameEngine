package sample;

import javafx.scene.image.Image;

/**
 * Created by Vlad on 09.02.2016.
 */
public interface SpriteManager {

    void loadImages ();

    void addSprite (String name);

    void deleteSprite (int index);

    void setSprite (Image image, int index);

    Image getSprite (double time);

    double getDuration();

    void setDuration(double duration);

    int[] getSize();

    void setSize(int h, int w);

}
