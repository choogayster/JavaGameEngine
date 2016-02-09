package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    double xPos = 100;
    double yPos = 100;

    EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent mouseEvent) {
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
        Canvas canvas = new Canvas( 512, 512 );
        root.getChildren().add( canvas );

        GraphicsContext gc = canvas.getGraphicsContext2D();

        final long startNanoTime = System.nanoTime();

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                gc.clearRect(0, 0, 512, 512);
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;

                gc.setFill(Color.FORESTGREEN);
                gc.fillOval(xPos-40, yPos-40, 80, 80);
                gc.restore();
            }
        }.start();

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
