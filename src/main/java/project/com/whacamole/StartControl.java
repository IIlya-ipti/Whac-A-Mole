package project.com.whacamole;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class StartControl {

    @FXML
    private Text BestScore;

    @FXML
    private Button Play;

    @FXML
    private Text Score;

    @FXML
    private Button MenuField;


    @FXML
    void MenuAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) Play.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MenuControl.class.getResource("menu_template.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);


        //  set best score
        MenuControl menuControl = fxmlLoader.getController();
        menuControl.getScoreField().setText(BestScore.getText());
    }

    @FXML
    void PlayAction(ActionEvent event)  throws IOException {
        Stage stage = (Stage) Play.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(GameControl.class.getResource("game_template.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public Text getBestScore() {
        return BestScore;
    }

    public Text getScore() {
        return Score;
    }
}
