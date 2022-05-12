package project.com.whacamole;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import project.com.whacamole.GameEngine.Game;

import javax.xml.parsers.SAXParser;

public class GameControl implements Initializable {
    private Game thisGame;
    private static int bestScore = 0;
    private  Timeline  stopWatchTimeline;

    @FXML // fx:id="sceneGame"
    private SubScene sceneGame; // Value injected by FXMLLoader

    @FXML // fx:id="TimeField"
    private Text TimeField; // Value injected by FXMLLoader

    @FXML // fx:id="ScoreField"
    private Text ScoreField; // Value injected by FXMLLoader

    @FXML
    private Text Score;


    /*
    * End the game
    * */
    private void End() throws IOException {
        stopWatchTimeline.stop();

        // init new best score
        bestScore = Math.max(bestScore,thisGame.getPlayerScore());

        // init page
        Stage stage = (Stage) sceneGame.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(StartControl.class.getResource("end_template.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        StartControl startControl = fxmlLoader.getController();

        // set new score in text fields
        startControl.getScore().setText("Score : " + thisGame.getPlayerScore());
        startControl.getBestScore().setText("Best score : " + bestScore);

        stage.setScene(scene);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        final int SECOND_OF_GAME = 30;


        // create player score and time fields
        AtomicInteger second = new AtomicInteger(SECOND_OF_GAME);
        TimeField.setText("Time: "+ second + " sec.");
        stopWatchTimeline = new Timeline(new KeyFrame(Duration.seconds(1), (ActionEvent event) -> {
            second.getAndDecrement();
            TimeField.setText("Time: "+ second + " sec.");
        }));
        stopWatchTimeline.setCycleCount(SECOND_OF_GAME);
        stopWatchTimeline.play();
        stopWatchTimeline.setOnFinished(actionEvent -> {
            try {
                End();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        // add new game
        thisGame = new Game(sceneGame.getWidth(),sceneGame.getHeight());
        thisGame.RunGame(sceneGame);
        thisGame.setTextScore(ScoreField);
    }
}