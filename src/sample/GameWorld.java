package sample;

import javafx.scene.image.Image;

import java.util.*;

/**
 * Created by Vlad on 10.02.2016.
 */
public class GameWorld {

    private int [][] tileMap;
    private int tileSize;
    private List<Image> textures;
    private List<Image> wallTextures;
    private List<Wall> wallMap;
    private Hero hero;

    public GameWorld() {
        tileMap = new int[10][10];
        tileSize = 60;
        textures = new ArrayList<>();
        wallTextures = new ArrayList<>();
        wallMap = new ArrayList<>();
        hero = new Hero();
        loadTextures();
        loadWalls();
        fillTileMap();
        buildWall();
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

    private void loadWalls() {
        loadWall("textures/wall/tileWall01.jpg");
        loadWall("textures/wall/tileWall02.png");
        loadWall("textures/wall/tileWall03.png");
    }

    private void buildWall() {
        wallMap.add(new Wall(wallTextures.get(0), -15, -15));

        for (int i = 0; i < 19; i++) {
            wallMap.add(new Wall(wallTextures.get(1), 15+i*30, -15));
        }

        wallMap.add(new Wall(wallTextures.get(0), 585, -15));

        for (int i = 0; i < 19; i++) {
            wallMap.add(new Wall(wallTextures.get(2), 585, 15+i*30));
        }

        wallMap.add(new Wall(wallTextures.get(0), 585, 585));

        for (int i = 0; i < 19; i++) {
            wallMap.add(new Wall(wallTextures.get(1), 15+i*30, 585));
        }

        wallMap.add(new Wall(wallTextures.get(0), -15, 585));

        for (int i = 0; i < 19; i++) {
            wallMap.add(new Wall(wallTextures.get(2), -15, 15+i*30));
        }
    }

    private void loadWall(String wallName) {
        Image image = new Image(getClass().getResource( wallName ).toExternalForm());
        wallTextures.add(image);
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

    public Wall getWall(int index) { return wallMap.get(index); }

    public List<Wall> getWallMap () { return wallMap; }

    public Hero getHero() {
        return hero;
    }

}
