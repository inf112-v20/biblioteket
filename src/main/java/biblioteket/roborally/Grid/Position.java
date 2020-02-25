package biblioteket.roborally.Grid;

import biblioteket.roborally.Elements.IElement;
import biblioteket.roborally.Elements.IRobot;

import java.util.ArrayList;
import java.util.List;

public class Position<T> implements IPosition<T> {
    private final int x, y;         // The x and y location of the position on the grid.
    private ArrayList<T> contents;  // The contents of the position

    public Position(int x, int y){
        this.x = x;
        this.y = y;
        contents = new ArrayList<>();
    }

    //Burde sjekke contains?
    @Override
    public T remove(T element) {
        contents.remove(element);
        return element; // If you need the element to remove the element then why would it return the element lmao
    }


    // Should this check for multiple robots in same position?
    @Override
    public boolean put(T element) {
        contents.add(element);
        return true;
    }

    @Override
    public List<T> getContents() {
        return this.contents;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }
}
