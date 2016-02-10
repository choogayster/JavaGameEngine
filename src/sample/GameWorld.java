package sample;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Vlad on 10.02.2016.
 */
public class GameWorld {

    private int [][] tileMap;
    private int tileSize;
    private List<Image> textures;
    private Hero hero;

    public GameWorld() {
        tileMap = new int[10][10];
        tileSize = 67;
        textures = new ArrayList<>();
        hero = new Hero();
        loadTextures();
        fillTileMap();
    }

    private void fillTileMap() {
        Random random = new Random();
        for (int i = 0; i < tileMap.length; i++) {
            for (int j = 0; j < tileMap[0].length; j++) {
                tileMap[i][j] = random.nextInt(3);
            }
        }
    }

    private void loadTextures() {
        loadTexture("textures/texture01.jpg");
        loadTexture("textures/texture02.jpg");
        loadTexture("textures/texture03.jpg");
    }

    private void loadTexture(String textureName) {
        Image image = new Image(getClass().getResource( textureName ).toExternalForm());
        textures.add(image);
    }

    public Image getTileImage(int x, int y) {
        return textures.get( tileMap[x][y] );
    }

    public int getTileSize() {
        return tileSize;
    }

    public int getSizeMapX() {
        return tileMap.length;
    }

    public int getSizeMapY() {
        return tileMap[0].length;
    }

    public Hero getHero() {
        return hero;
    }

}
