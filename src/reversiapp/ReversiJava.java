package reversiapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ReversiJava extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // load root from fxml
            BorderPane root = (BorderPane) FXMLLoader.load(this.getClass().getResource("GameFXML.fxml"));
            // create scene
            Scene scene = new Scene(root, 400, 400);
            scene.getStylesheets().add(this.getClass().getResource("reversiapp.css").toExternalForm());
            // set stage to use scene
            primaryStage.setScene(scene);
            // show stage
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
