package sample;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import sample.level.Level;
import sample.spriteManagers.SpriteManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vlad on 23.02.2016.
 */
public class Renderer {

    private GraphicsContext context;
    private GameWorld world;
    private List<SpriteManager> spriteManagers;
    private List<Image> staticImages;

    public Renderer(GraphicsContext context, GameWorld world) {
        this.context = context;
        this.world = world;
        spriteManagers = new ArrayList<>();
        staticImages = new ArrayList<>();
        loadStaticImages();

    }

    public void loadStaticImages() {
        Image light = new Image(getClass().getResource( "textures/light.png").toExternalForm());
        staticImages.add(light);
        Image bullet = new Image(getClass().getResource( "textures/bullet.png").toExternalForm());
        staticImages.add(bullet);
    }

    public void addSpriteManager(SpriteManager manager, double duration) {
        manager.setDuration(duration);
        spriteManagers.add(manager);

    }

    public void render(
            int windowWidth,
            int windowHeight,
            double time,
            double alfa,
            double xWindowCenter,
            double yWindowCenter,
            double xDeltaPos,
            double yDeltaPos,
            double xPos,
            double yPos)
    {
        // Draw background
        context.drawImage(spriteManagers.get(0).getSprite(time), 0, -100, windowWidth, windowHeight+200);

        // Draw ground textures
        for (Level.Ground ground : world.level.grounds) {
            context.drawImage(ground.getTexture(),
                    ground.getxCoord() - world.getHero().xPosHero + xWindowCenter + xDeltaPos,
                    ground.getyCoord() - world.getHero().yPosHero + yWindowCenter + yDeltaPos);
        }

        // Draw enemies
        for (Enemy enemy : world.level.enemies) {
            context.setFill(Color.YELLOWGREEN);
            context.fillRect(
                    (enemy.getxPos()-enemy.getWidth()/2) - world.getHero().xPosHero + xWindowCenter + xDeltaPos,
                    (enemy.getyPos()-enemy.getHeight()/2) - world.getHero().yPosHero + yWindowCenter + yDeltaPos,
                    enemy.getWidth(),
                    enemy.getHeight());
        }

        // Draw the light under hero
        context.drawImage(staticImages.get(0),
                (xWindowCenter + xDeltaPos) - staticImages.get(0).getWidth()*10/2,
                (yWindowCenter + yDeltaPos) - staticImages.get(0).getHeight()*10/2,
                staticImages.get(0).getWidth()*10,
                staticImages.get(0).getHeight()*10);


        SnapshotParameters params = new SnapshotParameters();

        // Draw bullet
        ImageView bulletView = new ImageView(staticImages.get(1));
        for (Bullet bullet_ : world.bullets) {
            context.drawImage(staticImages.get(1),
                    bullet_.xPos  - world.getHero().xPosHero + xWindowCenter + xDeltaPos,
                    bullet_.yPos  - world.getHero().yPosHero + yWindowCenter + yDeltaPos);
        }


        // HERO DRAWING
        context.save();
        // Set axis of rotation into center of hero sprite
        context.translate((xWindowCenter + xDeltaPos) , (yWindowCenter + yDeltaPos) );
        // Rotate hero sprite
        context.rotate(alfa * 180/Math.PI);
        if (world.getHero().ShotState) {
            context.setFill(Color.GREEN);
            context.fillRect(-30, -30, 60, 60);

        } else {
            context.setFill(Color.BLACK);
            context.fillRect(-30, -30, 60, 60);
        }
        context.restore();

        // Draw walls
        for (Level.Wall wall : world.level.walls) {
            context.drawImage(wall.getTexture(),
                    wall.getxCoord() - world.getHero().xPosHero + xWindowCenter + xDeltaPos,
                    wall.getyCoord() - world.getHero().yPosHero + yWindowCenter + yDeltaPos);
        }

        // Draw aim
        context.drawImage(spriteManagers.get(2).getSprite(time),
                xPos - spriteManagers.get(2).getSize()[0] / 2,
                yPos - spriteManagers.get(2).getSize()[1] / 2,
                spriteManagers.get(2).getSize()[0],
                spriteManagers.get(2).getSize()[1]);

    }
}
