package project.com.whacamole;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class GameMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(MenuControl.class.getResource("menu_template.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Game!");
        stage.setScene(scene);

        // add icon
        stage.getIcons().add(new Image(new File("src/main/resources/project/com/whacamole/icon.png").toURI().toString()));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}