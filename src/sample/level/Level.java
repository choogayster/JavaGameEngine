package sample.level;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import sample.Enemy;
import sample.EnemyRails;

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
    public List<EnemyRails> rails;
    public List<Enemy> enemies;

    public Level () {
        textureWalls = new ArrayList<>();
        textureGrounds = new ArrayList<>();
        grounds = new ArrayList<>();
        walls = new ArrayList<>();
        rails = new ArrayList<>();
        enemies = new ArrayList<>();
        loadGroundTextures();
        loadWallTextures();
        createWalls();
        createGrounds();
        createEnemyRails();
        createEnemies();
    }

    public abstract void loadGroundTextures();

    public abstract void loadWallTextures();

    public abstract void createWalls();

    public abstract void createGrounds();

    public abstract void createEnemyRails();

    public abstract void createEnemies();

    public void loadGroundTexture(String textureName) {
        Image image = new Image(getClass().getResource( textureName ).toExternalForm());
        textureGrounds.add(image);
    }

    public void loadWallTexture(String textureName) {
        Image image = new Image(getClass().getResource( textureName ).toExternalForm());
        textureWalls.add(image);
    }

    public class Ground {
        private double xCoord;
        private double yCoord;
        private double width;
        private double height;
        private Image texture;
        public Ground (Image texture, double xCoord, double yCoord ) {
            this.texture = texture;
            this.xCoord = xCoord;
            this.yCoord = yCoord;
            this.width = texture.getWidth();
            this.height = texture.getHeight();
        }

        public double getxCoord() {
            return xCoord;
        }

        public void setxCoord(int xCoord) {
            this.xCoord = xCoord;
        }

        public double getyCoord() {
            return yCoord;
        }

        public void setyCoord(int yCoord) {
            this.yCoord = yCoord;
        }

        public double getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public double getHeight() {
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
        public Wall (Image texture, int xCoord, int yCoord, Rectangle collider) {
            this.texture = texture;
            this.xCoord = xCoord;
            this.yCoord = yCoord;
            this.width = (int) texture.getWidth();
            this.height = (int) texture.getHeight();
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
