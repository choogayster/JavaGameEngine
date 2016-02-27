package sample.level;

import javafx.scene.shape.Rectangle;
import sample.Enemy;
import sample.EnemyRails;

/**
 * Created by Vlad on 27.02.2016.
 */
public class Level2 extends Level {
    @Override
    public void loadGroundTextures() {
        loadGroundTexture("level2/ground.png");
    }

    @Override
    public void loadWallTextures() {
        for (int i = 1; i <= 30; i++) {
            loadWallTexture("level2/"+i+".png");
        }
    }

    @Override
    public void createEnemyRails() {
        /*EnemyRails rail = new EnemyRails();
        rail.addPoint(480, 300);
        rail.addPoint(780, 300);
        rail.addPoint(780, 420);
        rail.addPoint(480, 420);
        rail.bindById(0, 1);
        rail.bindById(1, 2);
        rail.bindById(2, 3);
        rail.bindById(3, 0);
        rails.add(rail);*/
    }

    @Override
    public void createEnemies() {
        /*Enemy enemy = new Enemy(40, 40, rails.get(0));
        enemies.add(enemy);*/
    }

    @Override
    public void createWalls() {

        walls.add(new Level.Wall(textureWalls.get(0), 4, 4, new Rectangle(4, 4, 600, 76)));
        walls.add(new Level.Wall(textureWalls.get(1), 4, 91, new Rectangle(4, 80, 76, 444)));
        walls.add(new Level.Wall(textureWalls.get(2), 528, 91, new Rectangle(528, 80, 76, 300)));
        walls.add(new Level.Wall(textureWalls.get(3), 91, 524, new Rectangle(4, 524, 300, 76)));
        walls.add(new Level.Wall(textureWalls.get(4), 604, 304, new Rectangle(604, 304, 868, 76)));
        walls.add(new Level.Wall(textureWalls.get(5), 1396, 4, new Rectangle(1396, 4, 76, 300)));
        walls.add(new Level.Wall(textureWalls.get(6), 1483, 4, new Rectangle(1472, 4, 524, 76)));
        walls.add(new Level.Wall(textureWalls.get(7), 1920, 91, new Rectangle(1920, 80, 76, 520)));
        walls.add(new Level.Wall(textureWalls.get(8), 1624, 524, new Rectangle(1624, 524, 296, 76)));
        walls.add(new Level.Wall(textureWalls.get(9), 1624, 600, new Rectangle(1624, 600, 76, 876)));
        walls.add(new Level.Wall(textureWalls.get(10), 1700, 1400, new Rectangle(1700, 1400, 296, 76)));
        walls.add(new Level.Wall(textureWalls.get(11), 1920, 1487, new Rectangle(1920, 1476, 76, 520)));
        walls.add(new Level.Wall(textureWalls.get(12), 1396, 1920, new Rectangle(1396, 1920, 524, 76)));
        walls.add(new Level.Wall(textureWalls.get(13), 1396, 1624, new Rectangle(1396, 1624, 76, 296)));
        walls.add(new Level.Wall(textureWalls.get(14), 528, 1624, new Rectangle(528, 1624, 864, 76)));
        walls.add(new Level.Wall(textureWalls.get(15), 528, 1700, new Rectangle(528, 1700, 76, 296)));
        walls.add(new Level.Wall(textureWalls.get(16), 4, 1920, new Rectangle(4, 1920, 576, 76)));
        walls.add(new Level.Wall(textureWalls.get(17), 4, 1400, new Rectangle(4, 1400, 76, 520)));
        walls.add(new Level.Wall(textureWalls.get(18), 91, 1400, new Rectangle(80, 1400, 300, 76)));
        walls.add(new Level.Wall(textureWalls.get(19), 304, 524, new Rectangle(304, 524, 76, 876)));
        walls.add(new Level.Wall(textureWalls.get(20), 528, 584, new Rectangle(528, 584, 76, 292)));
        walls.add(new Level.Wall(textureWalls.get(21), 1000, 548, new Rectangle(1000, 548, 468, 76)));
        walls.add(new Level.Wall(textureWalls.get(22), 1376, 1124, new Rectangle(1376, 1124, 76, 292)));
        walls.add(new Level.Wall(textureWalls.get(23), 528, 1360, new Rectangle(528, 1360, 468, 76)));

        walls.add(new Level.Wall(textureWalls.get(24), 680, 1000, new Rectangle(680, 1000, 40, 240)));
        walls.add(new Level.Wall(textureWalls.get(25), 604, 1124, new Rectangle(604, 1124, 76, 84)));
        walls.add(new Level.Wall(textureWalls.get(26), 680, 1240, new Rectangle(680, 1240, 240, 40)));
        walls.add(new Level.Wall(textureWalls.get(27), 1132, 712, new Rectangle(1132, 712, 200, 40)));
        walls.add(new Level.Wall(textureWalls.get(28), 1332, 712, new Rectangle(1332, 712, 40, 280)));
        walls.add(new Level.Wall(textureWalls.get(29), 1383, 800, new Rectangle(1372, 800, 76, 76)));

    }

    @Override
    public void createGrounds() {

        grounds.add(new Level.Ground(textureGrounds.get(0), 0, 0));

    }
}
