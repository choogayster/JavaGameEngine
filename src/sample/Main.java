package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        StackPane root = new StackPane();

        Rectangle rect = new Rectangle(0, 0, 100, 100);
        rect.setFill(Color.GREENYELLOW);

        DropShadow ds = new DropShadow(15, Color.DARKGREEN);

        rect.setEffect(ds);

        root.getChildren().add(rect);

        Scene scene = new Scene(root, 250, 200, Color.WHITESMOKE);

        primaryStage.setTitle("DropShadow");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
