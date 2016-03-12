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
import javafx.stage.Screen;
import javafx.stage.Stage;
import sample.level.Level;
import sample.spriteManagers.*;

import java.io.File;

public class Main extends Application {

    private GameWorld gameWorld;

    private Renderer renderer;

    int windowWidth = 1200;
    int windowHeight = 720;
    double xWindowCenter = windowWidth/2;
    double yWindowCenter = windowHeight/2;
    double alfa = 0;
    double beta = 0;
    double xPos = 100;
    double yPos = 100;
    double xDeltaPos = 10;
    double yDeltaPos = 10;
    double L = 115; // range between center of camera and user

    String musicFile = "src/sample/sounds/KDrew - Bullseye.mp3";
    //AudioClip music = new AudioClip(new File(musicFile).toURI().toString());

    String shotFile = "src/sample/sounds/sub_machine_gun_single_shot.mp3";
    //AudioClip shot = new AudioClip(new File(shotFile).toURI().toString());

    double time;

    EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            xPos = mouseEvent.getSceneX();
            yPos = mouseEvent.getSceneY();
            alfa = -Math.atan2( (yWindowCenter - (windowHeight-yPos)), (xWindowCenter - xPos));
            xDeltaPos = L * Math.cos(alfa);
            yDeltaPos = L * Math.sin(alfa);
            gameWorld.getHero().angle = alfa;
        }
    };

    EventHandler<KeyEvent> keyPressedHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            switch (event.getCode()) {
                case A:
                    gameWorld.getHero().moveRight = false;
                    gameWorld.getHero().moveLeft = true;
                    break;
                case W:
                    gameWorld.getHero().moveDown = false;
                    gameWorld.getHero().moveUp = true;
                    break;
                case D:
                    gameWorld.getHero().moveLeft = false;
                    gameWorld.getHero().moveRight = true;
                    break;
                case S:
                    gameWorld.getHero().moveUp = false;
                    gameWorld.getHero().moveDown = true;
                    break;
                case SPACE:
                    gameWorld.getHero().pickUp = true;
                    break;
            }
        }
    };

    EventHandler<KeyEvent> keyReleasedHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            switch (event.getCode()) {
                case A:
                    gameWorld.getHero().moveLeft = false;
                    break;
                case W:
                    gameWorld.getHero().moveUp = false;
                    break;
                case D:
                    gameWorld.getHero().moveRight = false;
                    break;
                case S:
                    gameWorld.getHero().moveDown = false;
                    break;
                case SPACE:
                    gameWorld.getHero().pickUp = true;
                    break;
            }
        }
    };

    EventHandler<MouseEvent> mousePressedHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            //shot.play();
            gameWorld.getHero().lefMouseClicked = true;
        }
    };

    EventHandler<MouseEvent> mouseReleasedHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            //Create attack
            gameWorld.getHero().lefMouseClicked = false;
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
        theScene.setOnMousePressed(mousePressedHandler);
        theScene.setOnMouseReleased(mouseReleasedHandler);

        // Invisible cursor
        theScene.setCursor(Cursor.NONE);

        //set full screen
        primaryStage.setFullScreen(true);
        windowWidth = (int)Screen.getPrimary().getVisualBounds().getWidth();
        windowHeight = (int)Screen.getPrimary().getVisualBounds().getHeight() + 50;
        xWindowCenter = windowWidth/2;
        yWindowCenter = windowHeight/2;

        Canvas canvas = new Canvas( windowWidth, windowHeight );
        root.getChildren().add( canvas );
        // Init graphics context
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Init gameworld
        gameWorld = new GameWorld();
        // Init renderer
        renderer = new Renderer(gc, gameWorld);

        // Create sprite managers


        //Playing audio
        //music.play();

        final long startNanoTime = System.nanoTime();
        ImageView textureView = new ImageView();

        // Main Game Loop
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                // Get delta time
                time = (currentNanoTime - startNanoTime) / 1000000000.0;
                // Update world
                gameWorld.update(time);
                // Render world
                renderer.render(windowWidth, windowHeight, time, alfa, xWindowCenter, yWindowCenter, xDeltaPos, yDeltaPos, xPos, yPos);

            }
        }.start();

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
