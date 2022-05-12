package project.com.whacamole.GameEngine;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.File;

public abstract class   Object {
    private final Rectangle recTexture;
    Object(double X0, double Y0,double width, double height,String UrlTexture){
        String pathImage = new File(UrlTexture).toURI().toString();

        // create and fill Mole texture for game
        recTexture = new Rectangle(X0,Y0,width,height);
        recTexture.setFill(new ImagePattern(
                new Image(pathImage)
        ));
    }
    boolean isLife(){
        return false;
    }
    abstract boolean setClick();
    abstract void launchObject();
    public Rectangle getRecTexture() {
        return recTexture;
    }
    public boolean isCanClicked(){return true;}
    public boolean isClick(){return false;}
}
