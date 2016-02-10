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
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

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

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle( "AnimatedImage Example" );

        Group root = new Group();
        Scene theScene = new Scene( root );
        primaryStage.setScene( theScene );

        theScene.setOnMouseMoved(mouseHandler);
        theScene.setCursor(Cursor.NONE);
        Canvas canvas = new Canvas( xWindowSize, yWindowSize );
        root.getChildren().add( canvas );

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Image light = new Image(getClass().getResource( "textures/light.png").toExternalForm());
        Image light01 = new Image(getClass().getResource( "textures/light01.png").toExternalForm());
        Image hero = new Image(getClass().getResource( "textures/hero.png").toExternalForm());
        ImageView heroView = new ImageView(hero);

        GameWorld gw = new GameWorld();

        SpriteManagerAim sma = new SpriteManagerAim();
        sma.setDuration(0.2);

        final long startNanoTime = System.nanoTime();

        new AnimationTimer() {
            public void handle(long currentNanoTime) {

                double t = (currentNanoTime - startNanoTime) / 1000000000.0;

                // Clear the screen before rendering
                gc.clearRect(0, 0, xWindowSize, yWindowSize);

                for (int i = 0; i < gw.getSizeMapX(); i++) {
                    for (int j = 0; j < gw.getSizeMapY(); j++) {
                        gc.drawImage(gw.getTileImage(i,j),
                                i * gw.getTileSize() - 40 + xDeltaPos,
                                j * gw.getTileSize() - 40 + yDeltaPos,
                                gw.getTileSize(),
                                gw.getTileSize());
                    }
                }

                gc.drawImage(light,
                        xPos - 200 / 2,
                        yPos - 200 / 2,
                        200, 200);
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
