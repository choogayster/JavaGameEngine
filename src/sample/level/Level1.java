package sample.level;

import javafx.scene.shape.Rectangle;
import sample.Enemy;
import sample.EnemyRails;

/**
 * Created by Vlad on 11.02.2016.
 */
public class Level1 extends Level {

    public Level1 () {

    }

    @Override
    public void loadGroundTextures() {
        loadGroundTexture("level1/ground1.png");
    }

    @Override
    public void loadWallTextures() {
        loadWallTexture("level1/wall1.png");
        loadWallTexture("level1/wall2.png");
        loadWallTexture("level1/wall3.png");
        loadWallTexture("level1/wall4.png");
    }

    @Override
    public void createEnemyRails() {
        EnemyRails rail = new EnemyRails();
        rail.addPoint(480, 300);
        rail.addPoint(780, 300);
        rail.addPoint(780, 420);
        rail.addPoint(480, 420);
        rail.bindById(0, 1);
        rail.bindById(1, 2);
        rail.bindById(2, 3);
        rail.bindById(3, 0);
        rails.add(rail);
    }

    @Override
    public void createEnemies() {
        Enemy enemy = new Enemy(40, 40, rails.get(0), 0);
        enemies.add(enemy);
    }

    @Override
    public void createWalls() {

        walls.add(new Level.Wall(textureWalls.get(3), 120, 472, new Rectangle(123, 475, 24, 24) ));
        walls.add(new Level.Wall(textureWalls.get(3), 360, 472, new Rectangle(363, 475, 24, 24) ));
        walls.add(new Level.Wall(textureWalls.get(3), 360, 232, new Rectangle(363, 235, 24, 24) ));
        walls.add(new Level.Wall(textureWalls.get(3), 840, 232, new Rectangle(843, 235, 24, 24) ));
        walls.add(new Level.Wall(textureWalls.get(3), 840, 472, new Rectangle(843, 475, 24, 24) ));
        walls.add(new Level.Wall(textureWalls.get(3), 600, 472, new Rectangle(603, 475, 24, 24) ));
        walls.add(new Level.Wall(textureWalls.get(3), 600, 712, new Rectangle(603, 715, 24, 24) ));
        walls.add(new Level.Wall(textureWalls.get(3), 120, 712, new Rectangle(123, 715, 24, 24) ));

        walls.add(new Level.Wall(textureWalls.get(0), 390, 235, new Rectangle(390, 235, 450, 24) ));
        walls.add(new Level.Wall(textureWalls.get(0), 150, 715, new Rectangle(150, 715, 450, 24) ));

        walls.add(new Level.Wall(textureWalls.get(1), 150, 475, new Rectangle(150, 475, 210, 24) ));
        walls.add(new Level.Wall(textureWalls.get(1), 630, 475, new Rectangle(630, 475, 210, 24) ));

        walls.add(new Level.Wall(textureWalls.get(2), 123, 502, new Rectangle(123, 502, 24, 210) ));
        walls.add(new Level.Wall(textureWalls.get(2), 363, 262, new Rectangle(363, 262, 24, 210) ));
        walls.add(new Level.Wall(textureWalls.get(2), 603, 502, new Rectangle(603, 502, 24, 210) ));
        walls.add(new Level.Wall(textureWalls.get(2), 843, 262, new Rectangle(843, 262, 24, 210) ));

    }

    @Override
    public void createGrounds() {

        grounds.add(new Level.Ground(textureGrounds.get(0), 135, 247));

    }
}
