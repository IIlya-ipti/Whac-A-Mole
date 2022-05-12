package project.com.whacamole.GameEngine;

import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.scene.Node;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;


public class Mole extends Object{
    private boolean life = false;
    private boolean isClick = true;

    Mole(double X0, double Y0, double width, double height, String UrlTexture) {
        super(X0, Y0, width, height, UrlTexture);

    }

    /*
    * Move mole and his rectangle clip (down or up)
    * */
    private void move(double setPosY){
        double xCenter = getRecTexture().getX() + getRecTexture().getTranslateX() + getRecTexture().getWidth()/2;
        double yCenter = getRecTexture().getY() + getRecTexture().getTranslateY() + getRecTexture().getHeight()/2;

        double immutableXCenter = getRecTexture().getX() + getRecTexture().getWidth()/2;
        double immutableYCenter = getRecTexture().getY() + getRecTexture().getHeight()/2 ;

        Node Clip = getRecTexture().getClip();

        double xClipCenter = Clip.getBoundsInLocal().getMinX() + Clip.getTranslateX() + getRecTexture().getWidth()/2;
        double yClipCenter = Clip.getBoundsInLocal().getMinY() + Clip.getTranslateY() + getRecTexture().getHeight()/2;

        double dy = immutableYCenter + setPosY - yCenter;

        Path path1 = new Path();
        path1.getElements().add(new MoveTo(xCenter,yCenter));
        path1.getElements().add(new LineTo(xCenter, yCenter + dy));

        Path path2 = new Path();
        path2.getElements().add(new MoveTo(xClipCenter,yClipCenter));
        path2.getElements().add(new LineTo(xClipCenter, yClipCenter -  dy));

        PathTransition pathTransition1 = new PathTransition();
        PathTransition pathTransition2 = new PathTransition();

        pathTransition1.setNode(getRecTexture());
        pathTransition1.setPath(path1);
        pathTransition1.setDuration(Duration.seconds(0.3));

        pathTransition2.setNode(Clip);
        pathTransition2.setPath(path2);
        pathTransition2.setDuration(Duration.seconds(0.3));

        ParallelTransition parallelTransition = new ParallelTransition(pathTransition1,pathTransition2);
        parallelTransition.play();
    }

    @Override
    public boolean isClick() {
        return isClick;
    }

    @Override
    public boolean isLife() {
        return life;
    }

    @Override
    public boolean isCanClicked(){
        if(isClick) {
            isClick = false;
            return Math.abs(getRecTexture().getTranslateY()) > 60;
        }
        return false;
    }

    @Override
    public boolean setClick() {
        if(life) {
            move(0);
            life = !life;
            return true;
        }
        return false;
    }

    @Override
    public void launchObject() {
        if(!this.life) {
            move(-getRecTexture().getHeight());
        }
        isClick = true;
        life = true;
    }

}
