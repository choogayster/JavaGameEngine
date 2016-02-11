package sample;

import javafx.scene.image.Image;

/**
 * Created by Vlad on 10.02.2016.
 */
public class Wall {
    private Image image;
    private int[] coord;
    private int xSizeWall;
    private int ySizeWall;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int[] getCoord() {
        return coord;
    }

    public void setCoord(int[] coord) {
        this.coord = coord;
    }

    public Wall(Image image, int x, int y) {
        coord = new int[2];
        coord[0] = x;
        coord[1] = y;
        this.image = image;
        xSizeWall = 30;
        ySizeWall = 30;
    }
}
