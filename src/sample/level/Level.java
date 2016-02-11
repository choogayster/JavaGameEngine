package sample.level;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vlad on 11.02.2016.
 */
public abstract class Level {
    public List<Image> textureWalls;
    public List<Image> textureGrounds;
    public List<Ground> grounds;
    public List<Wall> walls;

    public Level () {
        textureWalls = new ArrayList<>();
        textureGrounds = new ArrayList<>();
        grounds = new ArrayList<>();
        walls = new ArrayList<>();
        loadGroundTextures();
        loadWallTextures();
        createWalls();
        createGrounds();
    }

    public abstract void loadGroundTextures();

    public abstract void loadWallTextures();

    public abstract void createWalls();

    public abstract void createGrounds();

    public void loadGroundTexture(String textureName) {
        Image image = new Image(getClass().getResource( textureName ).toExternalForm());
        textureGrounds.add(image);
    }

    public void loadWallTexture(String textureName) {
        Image image = new Image(getClass().getResource( textureName ).toExternalForm());
        textureWalls.add(image);
    }

    public class Ground {
        private int xCoord;
        private int yCoord;
        private int width;
        private int height;
        private Image texture;
        public Ground (Image texture, int xCoord, int yCoord, int width, int height) {
            this.texture = texture;
            this.xCoord = xCoord;
            this.yCoord = yCoord;
            this.width = width;
            this.height = height;
        }

        public int getxCoord() {
            return xCoord;
        }

        public void setxCoord(int xCoord) {
            this.xCoord = xCoord;
        }

        public int getyCoord() {
            return yCoord;
        }

        public void setyCoord(int yCoord) {
            this.yCoord = yCoord;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public Image getTexture() {
            return texture;
        }

        public void setTexture(Image texture) {
            this.texture = texture;
        }
    }
    public class Wall {
        private int xCoord;
        private int yCoord;
        private int width;
        private int height;
        private Image texture;
        private Rectangle collider;
        public Wall (Image texture, int xCoord, int yCoord, int width, int height, Rectangle collider) {
            this.texture = texture;
            this.xCoord = xCoord;
            this.yCoord = yCoord;
            this.width = width;
            this.height = height;
            this.collider = collider;
        }

        public int getxCoord() {
            return xCoord;
        }

        public void setxCoord(int xCoord) {
            this.xCoord = xCoord;
        }

        public int getyCoord() {
            return yCoord;
        }

        public void setyCoord(int yCoord) {
            this.yCoord = yCoord;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public Image getTexture() {
            return texture;
        }

        public void setTexture(Image texture) {
            this.texture = texture;
        }

        public Rectangle getCollider() {
            return collider;
        }

        public void setCollider(Rectangle collider) {
            this.collider = collider;
        }
    }
}
