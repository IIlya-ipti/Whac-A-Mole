package project.com.whacamole.GameEngine;

import javafx.animation.PauseTransition;
import javafx.scene.Group;
import javafx.scene.SubScene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.util.Duration;

interface GameConst{
    int countW = 3; // count of objects OSX
    int countH = 3; // count of objects OSY
}

interface ScrObject{
    String molePath = "src/main/resources/project/com/whacamole/Mole.png";
    String floorPath = "src/main/resources/project/com/whacamole/floor.png";
}

public class Game {
    private final Grid gridObjects; // hash table for game
    private final Group groupOfObjects;  // group for javafx
    private int playerScore = 0;
    private Text textScore = null; // text field of score
    private int randIndex;


    public void setTextScore(Text userScore){
        this.textScore = userScore;
    }
    public Game(double windowX, double windowY){
        // width of objects
        int widthObject = (int) windowX / GameConst.countW;
        // height of objects
        int heightObject = (int) windowY / GameConst.countH;



        groupOfObjects = new Group();
        gridObjects = new Grid(GameConst.countH,GameConst.countW, widthObject, heightObject);


        // init all objects
        Mole mole;
        Floor floor;
        for(int i = 0;i < GameConst.countW;i++){
            for(int j = 0 ; j < GameConst.countH;j++) {
                floor = new Floor(
                        widthObject * i,
                        heightObject * j,
                        widthObject,
                        heightObject,
                        ScrObject.floorPath
                );

                // this rectangle for Clip
                Rectangle rc = new Rectangle(
                        widthObject * i,
                        heightObject * j,
                        widthObject,
                        heightObject);

                mole = new Mole(
                        widthObject * i,
                        heightObject * j + heightObject,
                        widthObject,
                        heightObject,
                        ScrObject.molePath
                );

                mole.getRecTexture().setClip(rc);
                gridObjects.setGridObject(i,j,mole);
                groupOfObjects.getChildren().add(mole.getRecTexture());
                groupOfObjects.getChildren().add(floor.getRecTexture());
            }
        }
        randLaunch();
    }

    public int getPlayerScore(){
        return playerScore;
    }

    /*
    * method for start this game
    * */
    public void RunGame(SubScene scene){
        scene.setRoot(groupOfObjects);
        scene.setOnMousePressed(keyEvent -> {
            Object obj = gridObjects.getGridObjectCord((int)keyEvent.getX(),(int)keyEvent.getY());
            if(obj.isCanClicked()){
                playerScore += 1;
                if(textScore != null){
                    textScore.setText("Score: " + playerScore);
                }
            }
        });
    }


    /*
    * Launch random Mole of this game
    * */
    private void randLaunch(){
        int randValue = (int)(Math.random() * GameConst.countH * GameConst.countW);
        while(randValue == randIndex){
            randValue = (int)(Math.random() * GameConst.countH * GameConst.countW - 1);
        }
        randIndex = randValue;
        gridObjects.getGridObject(randValue).launchObject();

        PauseTransition delay = new PauseTransition(Duration.seconds(0.6));
        delay.setOnFinished(
                event -> {
                Object object = gridObjects.getGridObject(randIndex);
                if(object.setClick()) {
                    randLaunch();
                }
            }
        );
        delay.play();
    }
}
