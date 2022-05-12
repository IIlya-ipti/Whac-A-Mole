package project.com.whacamole.GameEngine;


public class Floor extends Object{

    Floor(double X0, double Y0, double width, double height, String UrlTexture) {
        super(X0, Y0, width, height, UrlTexture);
    }

    @Override
    public boolean setClick() {
        return false;
    }

    @Override
    public void launchObject() {

    }
}
