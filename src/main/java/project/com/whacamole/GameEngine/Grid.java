package project.com.whacamole.GameEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grid {
    private final List<Object> GridObjects;
    private final int width;
    private final int height;
    private final int lenXGrid; // width of grid block
    private final int lenYGrid; // height of grid block

    Grid(int CountH, int CountW, int lenXGrid,int lenYGrid){
        this.GridObjects =  new ArrayList<>(Collections.nCopies(CountH * CountW, null));
        this.width = CountW;
        this.height = CountH;
        this.lenXGrid = lenXGrid;
        this.lenYGrid = lenYGrid;

    }
    public void setGridObject(int x,int y,Object object){
        if(y >= height) throw new RuntimeException("y is biggest max_y: " + height);
        if(x >= width) throw new RuntimeException("x is biggest max_x: " + width);

        GridObjects.set(width * y + x,object);
    }
    public Object getGridObject(int x, int y){
        if(y >= height) throw new RuntimeException("y is biggest max_y: " + height);
        if(x >= width) throw new RuntimeException("x is biggest max_x: " + width);

        return GridObjects.get(width * y + x);
    }

    public Object getGridObject(int index){
        if(index >= width * height) throw new RuntimeException("index is biggest max_val: " + width*height);
        return GridObjects.get(index);
    }

    public Object getGridObjectCord(int CordX, int CordY){
        return GridObjects.get(width * (CordY / lenYGrid) + CordX / lenXGrid );
    }
}
