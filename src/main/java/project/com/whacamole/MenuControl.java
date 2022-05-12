package project.com.whacamole;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MenuControl implements Initializable {

    @FXML
    private Button Play;

    @FXML
    private ImageView PlayField;

    @FXML
    private Text ScoreField;



    @FXML
    void PlayAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) Play.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(GameControl.class.getResource("game_template.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public Text getScoreField() {
        return ScoreField;
    }
}
