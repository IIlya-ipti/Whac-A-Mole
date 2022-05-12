package project.com.whacamole;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(MenuControl.class.getResource("menu_template.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Game!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}