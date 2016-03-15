package sample;

import java.util.Random;

/**
 * Created by vlad on 14.03.2016.
 */
public class GameBehavior {
    private GameWorld gameWorld;
    private double startTime;

    public GameBehavior(GameWorld gameWorld, double startTime) {
        this.gameWorld = gameWorld;
        this.startTime = startTime;
    }

    public void doBehavior(double time) {
        // Spawn mobs
        if (time  % 2 < 0.02) {
            gameWorld.enemies.add(new Enemy(40, 40, gameWorld.level.rails.get(0), 69, gameWorld.getHero()));
        }
        // Spawn medkids
        if (time % 4 < 0.02) {
            Random r = new Random();
            int rangeX = r.nextInt(70);
            int rangeY = r.nextInt(70);
            gameWorld.medKits.add(new MedicalKit(280 + rangeX, 280 + rangeY, 1));
        }
        // Spawn bulletkits
        if (time % 7 < 0.02) {
            Random r = new Random();
            int rangeX = r.nextInt(70);
            int rangeY = r.nextInt(70);
            gameWorld.armors.add(new Armor(280 + rangeX, 280 + rangeY, 1));
        }
        // Spawn armorkits
        if (time % 10 < 0.02) {
            Random r = new Random();
            int rangeX = r.nextInt(70);
            int rangeY = r.nextInt(70);
            gameWorld.bulletsKits.add(new BulletsKit(280 + rangeX, 280 + rangeY));
        }
        // Spawn ganja
        if (time % 30 < 0.02) {
            Random r = new Random();
            int rangeX = r.nextInt(70);
            int rangeY = r.nextInt(70);
            gameWorld.ganjas.add(new Ganja(1000 + rangeX, 1000 + rangeY));
        }

    }
}
