package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.level.Level;
import sample.spriteManagers.SpriteManagerAim;
import sample.spriteManagers.SpriteManagerBg;
import sample.spriteManagers.SpriteManagerShot1;

import java.io.File;

public class Main extends Application {

    GameWorld gameWorld;
    int xWindowSize = 1200;
    int yWindowSize = 700;
    double xWindowCenter = xWindowSize/2;
    double yWindowCenter = yWindowSize/2;
    double alfa = 0;
    double beta = 0;
    double xPos = 100;
    double yPos = 100;
    double xDeltaPos = 10;
    double yDeltaPos = 10;
    double L = 115; // range between center of camera and user

    String musicFile = "src/sample/sounds/KDrew - Bullseye.mp3";
    AudioClip music = new AudioClip(new File(musicFile).toURI().toString());

    String shotFile = "src/sample/sounds/sub_machine_gun_single_shot.mp3";
    AudioClip shot = new AudioClip(new File(shotFile).toURI().toString());

    double time;

    EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            xPos = mouseEvent.getSceneX();
            yPos = mouseEvent.getSceneY();

            alfa = Math.atan( (yWindowCenter - (yWindowSize-yPos)) / (xWindowCenter - xPos));

            if (xPos > xWindowCenter && (yWindowSize-yPos) > yWindowCenter ) {
                xDeltaPos = -Math.abs(L * Math.cos(alfa));
                yDeltaPos = Math.abs(L * Math.sin(alfa));
            }
            else if (xPos < xWindowCenter && (yWindowSize-yPos) > yWindowCenter ) {
                xDeltaPos = Math.abs(L * Math.cos(alfa));
                yDeltaPos = Math.abs(L * Math.sin(alfa));
            }
            else if (xPos < xWindowCenter && (yWindowSize-yPos) < yWindowCenter ) {
                xDeltaPos = Math.abs(L * Math.cos(alfa));
                yDeltaPos = -Math.abs(L * Math.sin(alfa));
            }
            else if (xPos > xWindowCenter && (yWindowSize-yPos) < yWindowCenter ) {
                xDeltaPos = -Math.abs(L * Math.cos(alfa));
                yDeltaPos = -Math.abs(L * Math.sin(alfa));
            }
        }
    };

    EventHandler<KeyEvent> keyPressedHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            if (event.getCode() == KeyCode.A) {
                gameWorld.getHero().MoveRight = false;
                gameWorld.getHero().MoveLeft = true;
            }
            if (event.getCode() == KeyCode.W) {
                gameWorld.getHero().MoveDown = false;
                gameWorld.getHero().MoveUp = true;
            }
            if (event.getCode() == KeyCode.D) {
                gameWorld.getHero().MoveLeft = false;
                gameWorld.getHero().MoveRight = true;
            }
            if (event.getCode() == KeyCode.S) {
                gameWorld.getHero().MoveUp = false;
                gameWorld.getHero().MoveDown = true;
            }
        }
    };

    EventHandler<KeyEvent> keyReleasedHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            switch (event.getCode()) {
                case A:
                    gameWorld.getHero().MoveLeft = false;
                    break;
                case W:
                    gameWorld.getHero().MoveUp = false;
                    break;
                case D:
                    gameWorld.getHero().MoveRight = false;
                    break;
                case S:
                    gameWorld.getHero().MoveDown = false;
                    break;
            }
        }
    };

    EventHandler<MouseEvent> mouseClickHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            gameWorld.addBullet(
                    new Bullet(time, gameWorld.getHero().xPosHero, gameWorld.getHero().yPosHero, -beta));
            /*gameWorld.addBullet(
                    new Bullet(time, gameWorld.getHero().xPosHero-Math.cos(-beta)*4, gameWorld.getHero().yPosHero-Math.sin(-beta)*4, -beta));
            gameWorld.addBullet(
                    new Bullet(time, gameWorld.getHero().xPosHero-Math.cos(-beta)*8, gameWorld.getHero().yPosHero-Math.sin(-beta)*8, -beta));*/
            gameWorld.getHero().ShotState = true;
            gameWorld.getHero().spentTimeShot = 3;//shot.play();
        }
    };

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle( "AnimatedImage Example" );

        Group root = new Group();
        Scene theScene = new Scene( root );
        primaryStage.setScene( theScene );

        theScene.setOnMouseMoved(mouseHandler);
        theScene.setOnMouseDragged(mouseHandler);
        theScene.setOnKeyPressed(keyPressedHandler);
        theScene.setOnKeyReleased(keyReleasedHandler);
        theScene.setOnMousePressed(mouseClickHandler);

        theScene.setCursor(Cursor.NONE);

        Canvas canvas = new Canvas( xWindowSize, yWindowSize );

        root.getChildren().add( canvas );

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gameWorld = new GameWorld();

        Image light = new Image(getClass().getResource( "textures/light.png").toExternalForm());
        Image light01 = new Image(getClass().getResource( "textures/light01.png").toExternalForm());
        Image bullet = new Image(getClass().getResource( "textures/bullet.png").toExternalForm());

        ImageView heroView = new ImageView(gameWorld.getHeroTexture());
        ImageView heroShotView = new ImageView();
        ImageView bulletView = new ImageView(bullet);

        // Create sprite managers
        SpriteManagerAim sma = new SpriteManagerAim();
        sma.setDuration(0.25);

        SpriteManagerBg smb = new SpriteManagerBg();
        smb.setDuration(0.1);

        SpriteManagerShot1 sms1 = new SpriteManagerShot1();
        sms1.setDuration(0.8);

        //Playing audio
        //music.play();

        final long startNanoTime = System.nanoTime();
        ImageView textureView = new ImageView();

        new AnimationTimer() {
            public void handle(long currentNanoTime) {

                time = (currentNanoTime - startNanoTime) / 1000000000.0;

                // Clear the screen before rendering
                gc.clearRect(0, 0, xWindowSize, yWindowSize);

                // Update world
                gameWorld.update(time);

                // Draw underground light
                gc.drawImage(smb.getSprite(time),0,-150, 1200, 1200);

                // Draw ground textures

                for (Level.Ground ground : gameWorld.level.grounds) {
                    gc.drawImage(ground.getTexture(),
                            ground.getxCoord() - gameWorld.getHero().xPosHero + xWindowCenter + xDeltaPos,
                            ground.getyCoord() - gameWorld.getHero().yPosHero + yWindowCenter + yDeltaPos);
                }

                // Draw enemies
                for (Enemy enemy : gameWorld.level.enemies) {
                    gc.setFill(Color.YELLOWGREEN);
                    gc.fillRect(
                            (enemy.getxPos()-enemy.getWidth()/2) - gameWorld.getHero().xPosHero + xWindowCenter + xDeltaPos,
                            (enemy.getyPos()-enemy.getHeight()/2) - gameWorld.getHero().yPosHero + yWindowCenter + yDeltaPos,
                            enemy.getWidth(),
                            enemy.getHeight());
                }

                // Draw the light under hero
                gc.drawImage(light,
                        (xWindowCenter + xDeltaPos) - light01.getWidth()*10/2,
                        (yWindowCenter + yDeltaPos) - light01.getHeight()*10/2,
                        light01.getWidth()*10,
                        light01.getHeight()*10);

                SnapshotParameters params = new SnapshotParameters();

                // Draw bullet
                for (Bullet bullet_ : gameWorld.bullets) {
                    bulletView.setRotate(90 - bullet_.angle * 180/Math.PI);
                    params = new SnapshotParameters();
                    params.setFill(Color.TRANSPARENT);
                    Image bullet = bulletView.snapshot(params, null);
                    gc.drawImage(bullet,
                            bullet_.xPos  - gameWorld.getHero().xPosHero + xWindowCenter + xDeltaPos,
                            bullet_.yPos  - gameWorld.getHero().yPosHero + yWindowCenter + yDeltaPos,
                            6,
                            6);
                }

                // If hero is in shot state draw current shot sprite
                if (gameWorld.getHero().ShotState) {
                    heroShotView.setImage(sms1.getSprite(time));
                    beta = Math.atan2( (yWindowCenter - (yWindowSize-yPos)), (xWindowCenter - xPos));
                    heroShotView.setRotate(90 - beta * 180/Math.PI);
                    params.setFill(Color.TRANSPARENT);
                    Image rotatedImage = heroShotView.snapshot(params, null);
                    gc.drawImage(rotatedImage,
                            (xWindowCenter + xDeltaPos) - rotatedImage.getWidth() * 2 / 2,
                            (yWindowCenter + yDeltaPos) - rotatedImage.getHeight() * 2 / 2,
                            rotatedImage.getWidth() * 2,
                            rotatedImage.getHeight() * 2
                    );
                }
                // If hero isn't shot state draw hero
                else {
                    beta = Math.atan2((yWindowCenter - (yWindowSize - yPos)), (xWindowCenter - xPos));
                    heroView.setRotate(90 - beta * 180 / Math.PI);
                    params.setFill(Color.TRANSPARENT);
                    Image rotatedImage = heroView.snapshot(params, null);
                    gc.drawImage(rotatedImage,
                            (xWindowCenter + xDeltaPos) - rotatedImage.getWidth() / 2,
                            (yWindowCenter + yDeltaPos) - rotatedImage.getHeight() / 2
                    );
                }

                // Draw walls
                for (Level.Wall wall : gameWorld.level.walls) {
                    gc.drawImage(wall.getTexture(),
                            wall.getxCoord() - gameWorld.getHero().xPosHero + xWindowCenter + xDeltaPos,
                            wall.getyCoord() - gameWorld.getHero().yPosHero + yWindowCenter + yDeltaPos);
                }

                // Draw aim
                gc.drawImage(sma.getSprite(time),
                        xPos - sma.getSize()[0] / 2,
                        yPos - sma.getSize()[1] / 2,
                        sma.getSize()[0],
                        sma.getSize()[1]);

            }
        }.start();

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
