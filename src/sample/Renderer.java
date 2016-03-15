package sample;

import javafx.collections.ObservableList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import sample.level.Level;
import sample.spriteManagers.*;

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
        loadSpriteManagers();
    }

    private void loadSpriteManagers() {
        spriteManagers.add(new SpriteManagerBg(0.4));
        spriteManagers.add(new SpriteManagerAim(0.05));
        spriteManagers.add(new SpriteManagerHero(0.05));
        spriteManagers.add(new SpriteManagerShot1(0.05));
        spriteManagers.add(new SpriteManagerExplosion(0.1));
        spriteManagers.get(3).setSingleAnimation(true);
        spriteManagers.add(new SpriteManagerGanja(0.1));
        spriteManagers.add(new SpriteManagerHeroShadow(0.05));
    }

    public void loadStaticImages() {
        staticImages.add(new Image(getClass().getResource( "textures/light.png").toExternalForm()));
        staticImages.add(new Image(getClass().getResource( "textures/bullets/bullet1.png").toExternalForm()));
        staticImages.add(new Image(getClass().getResource( "textures/kitShadow.png").toExternalForm()));
        staticImages.add(new Image(getClass().getResource( "textures/medkit1.png").toExternalForm()));
        staticImages.add(new Image(getClass().getResource( "textures/armorkit1.png").toExternalForm()));
        staticImages.add(new Image(getClass().getResource( "textures/bulletkit1.png").toExternalForm()));
    }

    // MAIN RENDERING FUNCTION
    public void render( int windowWidth, int windowHeight, double time, double alfa,
            double xWindowCenter, double yWindowCenter, double xDeltaPos, double yDeltaPos, double xPos, double yPos)
    {
        setPropetries(windowWidth, windowHeight, time, alfa, xWindowCenter, yWindowCenter, xDeltaPos, yDeltaPos,xPos, yPos);

        drawBackground();

        context.save();
        if (world.makeShake) {
            if (shakeSwitcher) {
                context.translate(7 * Math.cos(alfa), 7 * Math.sin(alfa));
                shakeSwitcher = false;
            } else {
                context.translate(-7 * Math.cos(alfa), -7 * Math.sin(alfa));
                shakeSwitcher = true;
            }
        }

        drawGrounds();
        //drawLightUnderHero();
        drawAmmo(time);
        drawBullets();
        drawEnemies();
        drawHero();
        drawWalls();
        drawEevents();

        context.restore();

        drawAim();

        world.makeShake = false;

    }

    private void drawAmmo(double time) {
        drawMedicalKits();
        drawArmorKits();
        drawBulletKits();
        drawGanja(time);
    }

    private void drawGanja(double time) {
        for (Ganja ganj : world.ganjas) {
            Image img = spriteManagers.get(5).getSprite(time);
            context.drawImage(img,
                    ganj.xPos - world.getHero().xPosHero + xWindowCenter + xDeltaPos-img.getWidth() / 2 - ganj.step,
                    ganj.yPos - world.getHero().yPosHero + yWindowCenter + yDeltaPos-img.getHeight() / 2 - ganj.step);

            if (ganj.duration == 0) {
                if (ganj.direction) {
                    ganj.step++;
                    if (ganj.step >= 3) {
                        ganj.direction = false;
                    }
                } else {
                    ganj.step--;
                    if (ganj.step <= 0) {
                        ganj.direction = true;
                    }
                }
                ganj.duration  = 3;
            } ganj.duration --;
        }
    }

    private void drawBulletKits() {
        for (BulletsKit bulkit : world.bulletsKits) {
            // draw shadow of kits
            Image img = staticImages.get(2);
            context.drawImage(img,
                    bulkit.xPos - world.getHero().xPosHero + xWindowCenter + xDeltaPos-img.getWidth() / 2 + bulkit.step,
                    bulkit.yPos - world.getHero().yPosHero + yWindowCenter + yDeltaPos-img.getHeight() / 2 + bulkit.step);
            // draw med kits
            img = staticImages.get(5);
            context.drawImage(img,
                    bulkit.xPos - world.getHero().xPosHero + xWindowCenter + xDeltaPos-img.getWidth() / 2 - bulkit.step,
                    bulkit.yPos - world.getHero().yPosHero + yWindowCenter + yDeltaPos-img.getHeight() / 2 - bulkit.step);

            if (bulkit.duration == 0) {
                if (bulkit.direction) {
                    bulkit.step++;
                    if (bulkit.step >= 3) {
                        bulkit.direction = false;
                    }
                } else {
                    bulkit.step--;
                    if (bulkit.step <= 0) {
                        bulkit.direction = true;
                    }
                }
                bulkit.duration  = 4;
            } bulkit.duration --;
        }
    }

    private void drawArmorKits() {
        for (Armor arm : world.armors) {
            // draw shadow of kits
            Image img = staticImages.get(2);
            context.drawImage(img,
                    arm.xPos - world.getHero().xPosHero + xWindowCenter + xDeltaPos-img.getWidth() / 2 + arm.step,
                    arm.yPos - world.getHero().yPosHero + yWindowCenter + yDeltaPos-img.getHeight() / 2 + arm.step);
            // draw med kits
            img = staticImages.get(4);
            context.drawImage(img,
                    arm.xPos - world.getHero().xPosHero + xWindowCenter + xDeltaPos-img.getWidth() / 2 - arm.step,
                    arm.yPos - world.getHero().yPosHero + yWindowCenter + yDeltaPos-img.getHeight() / 2 - arm.step);

            if (arm.duration == 0) {
                if (arm.direction) {
                    arm.step++;
                    if (arm.step >= 3) {
                        arm.direction = false;
                    }
                } else {
                    arm.step--;
                    if (arm.step <= 0) {
                        arm.direction = true;
                    }
                }
                arm.duration  = 4;
            } arm.duration --;
        }
    }

    private void drawMedicalKits () {
        for (MedicalKit med : world.medKits) {
            // draw shadow of kits
            Image img = staticImages.get(2);
            context.drawImage(img,
                    med.xPos - world.getHero().xPosHero + xWindowCenter + xDeltaPos-img.getWidth() / 2 + med.step,
                    med.yPos - world.getHero().yPosHero + yWindowCenter + yDeltaPos-img.getHeight() / 2 + med.step);
            // draw med kits
            img = staticImages.get(3);
            context.drawImage(img,
                    med.xPos - world.getHero().xPosHero + xWindowCenter + xDeltaPos-img.getWidth() / 2 - med.step,
                    med.yPos - world.getHero().yPosHero + yWindowCenter + yDeltaPos-img.getHeight() / 2 - med.step);

            if (med.duration == 0) {
                if (med.direction) {
                    med.step++;
                    if (med.step >= 3) {
                        med.direction = false;
                    }
                } else {
                    med.step--;
                    if (med.step <= 0) {
                        med.direction = true;
                    }
                }
                med.duration  = 3;
            } med.duration --;
        }
    }

    private void drawEevents() {
        for (GameWorld.Event event:world.events) {
            if (event.id == 1) {
                if (event.active) {
                    SpriteManager sm = spriteManagers.get(4);
                    if (event.duration == 0) {
                        event.indexOfAnimation++;
                        event.duration = event.constDuration;
                    }
                    Image img = sm.getSpriteById(event.indexOfAnimation);
                    event.duration--;
                    context.drawImage(img,
                            event.xPos- world.getHero().xPosHero + xWindowCenter + xDeltaPos-img.getWidth() / 2,
                            event.yPos- world.getHero().yPosHero + yWindowCenter + yDeltaPos-img.getHeight() / 2);
                    if (event.indexOfAnimation == sm.getCollectionSize() - 1) {
                        event.active = false;
                    }
                }
            }
        }
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
        for (Enemy enemy : world.enemies) {
            context.save();
            context.translate(
                    enemy.getxPos()- world.getHero().xPosHero + xWindowCenter + xDeltaPos,
                    enemy.getyPos() - world.getHero().yPosHero + yWindowCenter + yDeltaPos);
            context.rotate(enemy.angle * 180/Math.PI);
            context.setFill(Color.YELLOWGREEN);
            context.fillRect(
                    (-enemy.getWidth()/2) ,
                    (-enemy.getHeight()/2),
                    enemy.getWidth(),
                    enemy.getHeight());

            context.restore();

            // Draw collider
            ObservableList<Double> points = enemy.getCollider().getPoints();
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

    }

    private void drawLightUnderHero() {
        context.drawImage(staticImages.get(0),
                (xWindowCenter + xDeltaPos) - staticImages.get(0).getWidth()*10/2,
                (yWindowCenter + yDeltaPos) - staticImages.get(0).getHeight()*10/2,
                staticImages.get(0).getWidth()*10,
                staticImages.get(0).getHeight()*10);
    }

    private void drawBullets() {
        for (Bullet bullet_ : world.bullets) {
            // Draw collider
            /*ObservableList<Double> points = bullet_.collider.getPoints();
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
            context.strokePolygon(xpoints1, ypoints1, xpoints1.length);*/

            context.save();
            context.translate(
                    bullet_.xPos - world.getHero().xPosHero + xWindowCenter + xDeltaPos,
                    bullet_.yPos - world.getHero().yPosHero + yWindowCenter + yDeltaPos);
            context.rotate(bullet_.angle * 180/Math.PI);
            //context.setFill(Color.YELLOWGREEN);
            /*context.fillRect(
                    (-enemy.getWidth()/2) ,
                    (-enemy.getHeight()/2),
                    enemy.getWidth(),
                    enemy.getHeight());*/
            context.drawImage(staticImages.get(1), -staticImages.get(1).getWidth()/2, -staticImages.get(1).getHeight()/2);

            context.restore();

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
        // Draw hero shadow

        // Rotate hero sprite
        context.rotate(alfa * 180/Math.PI - 90);
        // Draw hero
        Image img = spriteManagers.get(6).getSprite(time);
        int currentIndexOfwakSprite = spriteManagers.get(2).indexOfCurrentSprite(time);
        int step = -10;
        switch (currentIndexOfwakSprite) {
            case 0: step = -18; break;
            case 1: step = -16; break;
            case 2: step = -14; break;
            case 3: step = -12; break;
            case 4: step = -10; break;
            case 5: step = -12; break;
            case 6: step = -14; break;
            case 7: step = -16; break;
        }
        context.drawImage(img, -img.getWidth()/2+step, -img.getHeight()/2+5);
        if (world.getHero().drawShotState == false) {
            img = spriteManagers.get(2).getSprite(time);
            context.drawImage(img, -img.getWidth()/2, -img.getHeight()/2);
        } else {
            SpriteManager sm = spriteManagers.get(3);
            if (world.getHero().durationShotAnimation == 0) {
                world.getHero().indexOfAnimation++;
                world.getHero().durationShotAnimation = world.getHero().constDuration;
            }
            img = sm.getSpriteById(world.getHero().indexOfAnimation);
            world.getHero().durationShotAnimation--;
            context.drawImage(img, -img.getWidth()/2, -img.getHeight()/2);
            if (world.getHero().indexOfAnimation == sm.getCollectionSize() - 1) {
                world.getHero().drawShotState = false;
                world.getHero().indexOfAnimation = 0;
            }
        }


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
        context.drawImage(spriteManagers.get(1).getSprite(time),
                xPos - 50,
                yPos - 50);
    }


}
