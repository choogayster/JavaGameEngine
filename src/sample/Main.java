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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class Main extends Application {
    GameWorld gameWorld;

    int xWindowSize = 700;
    int yWindowSize = 700;

    double xWindowCenter = xWindowSize/2;
    double yWindowCenter = yWindowSize/2;

    double alfa = 0;

    double xPos = 100;
    double yPos = 100;

    double xDeltaPos = 10;
    double yDeltaPos = 10;

    double L = 70; // range between center of camera and user

    EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            xPos = mouseEvent.getSceneX();
            yPos = mouseEvent.getSceneY();

            alfa = Math.atan( (yWindowCenter - (yWindowSize-yPos)) / (xWindowCenter - xPos));

            if (xPos > xWindowCenter && (yWindowSize-yPos) > yWindowCenter ) {
                xDeltaPos = -Math.abs(L * Math.cos(alfa));;
                yDeltaPos = Math.abs(L * Math.sin(alfa));
            }
            else if (xPos < xWindowCenter && (yWindowSize-yPos) > yWindowCenter ) {
                xDeltaPos = Math.abs(L * Math.cos(alfa));;
                yDeltaPos = Math.abs(L * Math.sin(alfa));
            }
            else if (xPos < xWindowCenter && (yWindowSize-yPos) < yWindowCenter ) {
                xDeltaPos = Math.abs(L * Math.cos(alfa));;
                yDeltaPos = -Math.abs(L * Math.sin(alfa));
            }
            else if (xPos > xWindowCenter && (yWindowSize-yPos) < yWindowCenter ) {
                xDeltaPos = -Math.abs(L * Math.cos(alfa));;
                yDeltaPos = -Math.abs(L * Math.sin(alfa));
            }
        }
    };

    EventHandler<KeyEvent> keyPressedHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            switch (event.getCode()) {
                case A:
                    gameWorld.getHero().MoveLeft = true;
                    break;
                case W:
                    gameWorld.getHero().MoveUp = true;
                    break;
                case D:
                    gameWorld.getHero().MoveRight = true;
                    break;
                case S:
                    gameWorld.getHero().MoveDown = true;
                    break;
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

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle( "AnimatedImage Example" );

        Group root = new Group();
        Scene theScene = new Scene( root );
        primaryStage.setScene( theScene );

        theScene.setOnMouseMoved(mouseHandler);
        theScene.setOnKeyPressed(keyPressedHandler);
        theScene.setOnKeyReleased(keyReleasedHandler);

        theScene.setCursor(Cursor.NONE);

        Canvas canvas = new Canvas( xWindowSize, yWindowSize );

        root.getChildren().add( canvas );

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Image light = new Image(getClass().getResource( "textures/light.png").toExternalForm());
        Image light01 = new Image(getClass().getResource( "textures/light01.png").toExternalForm());
        Image hero = new Image(getClass().getResource( "textures/hero.png").toExternalForm());
        ImageView heroView = new ImageView(hero);

        //Playing audio
        String musicFile = "src/sample/sounds/M.O.O.N. - Dust.mp3";     // For example
        AudioClip plonkSound = new AudioClip(new File(musicFile).toURI().toString());
        plonkSound.play();

        gameWorld = new GameWorld();

        SpriteManagerAim sma = new SpriteManagerAim();
        sma.setDuration(0.2);

        SpriteManagerBg smb = new SpriteManagerBg();
        smb.setDuration(0.2);

        final long startNanoTime = System.nanoTime();

        new AnimationTimer() {
            public void handle(long currentNanoTime) {

                double t = (currentNanoTime - startNanoTime) / 1000000000.0;

                // Clear the screen before rendering
                gc.clearRect(0, 0, xWindowSize, yWindowSize);

                // Update world
                gameWorld.getHero().update();

                gc.drawImage(smb.getSprite(t),0,0);

                for (int i = 0; i < gameWorld.getSizeMapX(); i++) {
                    for (int j = 0; j < gameWorld.getSizeMapY(); j++) {
                        gc.drawImage(gameWorld.getTileImage(i,j),
                                i * gameWorld.getTileSize() - gameWorld.getHero().xPos + xWindowCenter + xDeltaPos,
                                j * gameWorld.getTileSize() - gameWorld.getHero().yPos + yWindowCenter + yDeltaPos,
                                gameWorld.getTileSize(),
                                gameWorld.getTileSize());
                    }
                }

                gc.drawImage(light,
                        xPos - 200 / 2,
                        yPos - 200 / 2,
                        200, 200);

                for (Wall wall : gameWorld.getWallMap()) {
                    gc.drawImage(wall.getImage(),
                            wall.getCoord()[0] - gameWorld.getHero().xPos + xWindowCenter + xDeltaPos,
                            wall.getCoord()[1] - gameWorld.getHero().yPos + yWindowCenter + yDeltaPos,
                            30, 30);
                }

                gc.drawImage(sma.getSprite(t),
                        xPos - sma.getSize()[0] / 2,
                        yPos - sma.getSize()[1] / 2,
                        sma.getSize()[0],
                        sma.getSize()[1]);

                gc.drawImage(light,
                        (xWindowCenter + xDeltaPos) - light01.getWidth()*10/2,
                        (yWindowCenter + yDeltaPos) - light01.getHeight()*10/2,
                        light01.getWidth()*10,
                        light01.getHeight()*10);

                double beta = Math.atan2( (yWindowCenter - (yWindowSize-yPos)), (xWindowCenter - xPos));
                heroView.setRotate(270 - beta * 180/Math.PI);
                SnapshotParameters params = new SnapshotParameters();
                params.setFill(Color.TRANSPARENT);
                Image rotatedImage = heroView.snapshot(params, null);
                gc.drawImage(rotatedImage,
                        (xWindowCenter + xDeltaPos) - rotatedImage.getWidth()*1.5/2,
                        (yWindowCenter + yDeltaPos) - rotatedImage.getHeight()*1.5/2,
                        rotatedImage.getWidth()*1.5,
                        rotatedImage.getHeight()*1.5
                );
            }
        }.start();

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
