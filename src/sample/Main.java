package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.Random;

public class Main extends Application {

    double xPos = 100;
    double yPos = 100;

    EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
           /* xPos = mouseEvent.getSceneX();
            yPos = mouseEvent.getSceneY();*/
            xPos = mouseEvent.getSceneX();
            yPos = mouseEvent.getSceneY();
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
        Canvas canvas = new Canvas( 700, 700 );
        root.getChildren().add( canvas );

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Image aim = new Image(getClass().getResource( "textures/aim.png").toExternalForm());
        Image texture1 = new Image(getClass().getResource( "textures/texture01.jpg").toExternalForm());
        Image texture2 = new Image(getClass().getResource( "textures/texture02.jpg").toExternalForm());
        Image texture3 = new Image(getClass().getResource( "textures/texture03.jpg").toExternalForm());
        Image light = new Image(getClass().getResource( "textures/light.png").toExternalForm());

        Random random = new Random();
        int [][] map = new int[11][11];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = random.nextInt(3);
            }
        }


        final long startNanoTime = System.nanoTime();

        new AnimationTimer() {
            public void handle(long currentNanoTime) {

                double t = (currentNanoTime - startNanoTime) / 1000000000.0;

                // Clear the screen before rendering
                gc.clearRect(0, 0, 700, 700);

                int numberOfRectsOnVertical = 1 + (int) (canvas.getHeight() / texture1.getHeight());
                int numberOfRectsOnHorizontal = 1 + (int) (canvas.getWidth() / texture1.getWidth());

                for (int i = 0; i < map.length; i++) {
                    for (int j = 0; j < map[0].length; j++) {
                        switch (map[i][j]) {
                            case 0:
                                gc.drawImage(texture1, i * 70 - 10, j * 70 - 10, 70, 70); break;
                            case 1:
                                gc.drawImage(texture2, i * 70 - 10, j * 70 - 10, 70, 70); break;
                            case 2:
                                gc.drawImage(texture3, i * 70 - 10, j * 70 - 10, 70, 70); break;
                        }
                    }
                }

                gc.drawImage(light,
                        xPos - 150 / 2,
                        yPos - 150 / 2,
                        150, 150);
                gc.drawImage(aim,
                        xPos - 30 / 2,
                        yPos - 30 / 2,
                        30, 30);
                gc.restore();
            }
        }.start();

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
