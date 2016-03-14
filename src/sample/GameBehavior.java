package sample;

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
        if ((startTime - time)%2 == 0) {
            gameWorld.enemies.add(new Enemy(40, 40, gameWorld.level.rails.get(0), 69, gameWorld.getHero()));
        }
    }
}
