package sample;

import javafx.collections.ObservableList;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
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

    // properties of screen
    private int windowWidth;
    private int windowHeight;
    private double time;
    private double alfa;
    private double xWindowCenter;
    private double yWindowCenter;
    private double xDeltaPos;
    private double yDeltaPos;
    private double xPos;
    private double yPos;

    private boolean inShake = false;
    private boolean shakeSwitcher = true;

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

    // MAIN RENDERING FUNCTION
    public void render( int windowWidth, int windowHeight, double time, double alfa,
            double xWindowCenter, double yWindowCenter, double xDeltaPos, double yDeltaPos, double xPos, double yPos)
    {
        setPropetries(windowWidth, windowHeight, time, alfa, xWindowCenter, yWindowCenter, xDeltaPos, yDeltaPos,xPos, yPos);

        drawBackground();

        context.save();
        if (world.makeShake) {
            if (shakeSwitcher == true) {
                context.translate(5 * Math.cos(alfa), 5 * Math.sin(alfa));
                shakeSwitcher = false;
            } else {
                context.translate(-5 * Math.cos(alfa), -5 * Math.sin(alfa));
                shakeSwitcher = true;
            }
        }

        drawGrounds();
        drawEnemies();
        drawLightUnderHero();
        drawBullets();
        drawHero();
        drawWalls();

        context.restore();

        drawAim();

        world.makeShake = false;

    }


    private void setPropetries(int windowWidth, int windowHeight, double time, double alfa,
                               double xWindowCenter, double yWindowCenter, double xDeltaPos, double yDeltaPos, double xPos, double yPos) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.time = time;
        this.alfa = alfa;
        this.xWindowCenter = xWindowCenter;
        this.yWindowCenter = yWindowCenter;
        this.xDeltaPos = xDeltaPos;
        this.yDeltaPos = yDeltaPos;
        this.xPos = xPos;
        this.yPos = yPos;

    }

    private void drawBackground() {
        context.drawImage(spriteManagers.get(0).getSprite(time), 0, -100, windowWidth, windowHeight+300);
    }

    private void drawGrounds() {
        for (Level.Ground ground : world.level.grounds) {
            context.drawImage(ground.getTexture(),
                    ground.getxCoord() - world.getHero().xPosHero + xWindowCenter + xDeltaPos,
                    ground.getyCoord() - world.getHero().yPosHero + yWindowCenter + yDeltaPos);
        }
    }

    private void drawEnemies() {
        for (Enemy enemy : world.level.enemies) {
            context.setFill(Color.YELLOWGREEN);
            context.fillRect(
                    (enemy.getxPos()-enemy.getWidth()/2) - world.getHero().xPosHero + xWindowCenter + xDeltaPos,
                    (enemy.getyPos()-enemy.getHeight()/2) - world.getHero().yPosHero + yWindowCenter + yDeltaPos,
                    enemy.getWidth(),
                    enemy.getHeight());
        }
    }

    private void drawLightUnderHero() {
        context.drawImage(staticImages.get(0),
                (xWindowCenter + xDeltaPos) - staticImages.get(0).getWidth()*10/2,
                (yWindowCenter + yDeltaPos) - staticImages.get(0).getHeight()*10/2,
                staticImages.get(0).getWidth()*10,
                staticImages.get(0).getHeight()*10);
    }

    private void  drawBullets() {
        for (Bullet bullet_ : world.bullets) {
            // Draw collider
            ObservableList<Double> points = bullet_.collider.getPoints();
            double xpoints1[] = {
                    points.get(0) - world.getHero().xPosHero + xWindowCenter + xDeltaPos,
                    points.get(2) - world.getHero().xPosHero + xWindowCenter + xDeltaPos,
                    points.get(4) - world.getHero().xPosHero + xWindowCenter + xDeltaPos,
                    points.get(6) - world.getHero().xPosHero + xWindowCenter + xDeltaPos};
            double ypoints1[] = {
                    points.get(1) - world.getHero().yPosHero + yWindowCenter + yDeltaPos,
                    points.get(3) - world.getHero().yPosHero + yWindowCenter + yDeltaPos,
                    points.get(5) - world.getHero().yPosHero + yWindowCenter + yDeltaPos,
                    points.get(7) - world.getHero().yPosHero + yWindowCenter + yDeltaPos};
            context.strokePolygon(xpoints1, ypoints1, xpoints1.length);

            // Draw bullet
            /*context.drawImage(staticImages.get(1),
                    bullet_.xPos - world.getHero().xPosHero + xWindowCenter + xDeltaPos,
                    bullet_.yPos - world.getHero().yPosHero + yWindowCenter + yDeltaPos);*/
        }
    }

    private void drawHero( ) {
        if (world.getHero().weapon.isMeleeAttack()) {
            // Draw attack colider
            ObservableList<Double> points = world.getHero().colliderWeapon.getPoints();
            double xpoints1[] = {
                    points.get(0) - world.getHero().xPosHero + xWindowCenter + xDeltaPos,
                    points.get(2) - world.getHero().xPosHero + xWindowCenter + xDeltaPos,
                    points.get(4) - world.getHero().xPosHero + xWindowCenter + xDeltaPos,
                    points.get(6) - world.getHero().xPosHero + xWindowCenter + xDeltaPos};
            double ypoints1[] = {
                    points.get(1) - world.getHero().yPosHero + yWindowCenter + yDeltaPos,
                    points.get(3) - world.getHero().yPosHero + yWindowCenter + yDeltaPos,
                    points.get(5) - world.getHero().yPosHero + yWindowCenter + yDeltaPos,
                    points.get(7) - world.getHero().yPosHero + yWindowCenter + yDeltaPos};

            context.strokePolygon(xpoints1, ypoints1, xpoints1.length);
        }
        // Draw hero
        context.save();
        // Set axis of rotation into center of hero sprite
        context.translate((xWindowCenter + xDeltaPos) , (yWindowCenter + yDeltaPos) );
        // Rotate hero sprite
        context.rotate(alfa * 180/Math.PI);
        context.setFill(Color.BLACK);
        /*if (world.getHero().attack == true) {
            context.setFill(Color.GREEN);
        }*/
        context.fillRect(-20, -20, 40, 40);

        context.restore();
    }

    private void drawWalls() {
        for (Level.Wall wall : world.level.walls) {
            context.drawImage(wall.getTexture(),
                    wall.getxCoord() - world.getHero().xPosHero + xWindowCenter + xDeltaPos,
                    wall.getyCoord() - world.getHero().yPosHero + yWindowCenter + yDeltaPos);
        }
    }

    private void drawAim() {
        context.drawImage(spriteManagers.get(2).getSprite(time),
                xPos - spriteManagers.get(2).getSize()[0] / 2,
                yPos - spriteManagers.get(2).getSize()[1] / 2,
                spriteManagers.get(2).getSize()[0],
                spriteManagers.get(2).getSize()[1]);
    }


}
