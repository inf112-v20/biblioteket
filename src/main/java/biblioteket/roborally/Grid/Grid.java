package biblioteket.roborally.Grid;

import biblioteket.roborally.Elements.IElement;
import biblioteket.roborally.ILocation;

import java.util.Iterator;

public class Grid implements IGrid {
    private int height;
    private int width;
    private IPosition<IElement>[] grid;     // Each position holds all objects in that position

    public Grid(int width, int height){
        this.height = height;
        this.width = width;

        grid = new IPosition[height*width];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int index = x + (width * y);
                grid[index] = new Position<IElement>(x,y);
            }
        }
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public void placeElement(int x, int y, IElement element) {
        if(x < 0 || x >= getWidth())
            throw new IndexOutOfBoundsException();
        if(y < 0 || y >= getHeight())
            throw new IndexOutOfBoundsException();

        int index = x + (getWidth() * y);
        grid[index].put(element);
    }

    @Override
    public IPosition getPosition(int x, int y) {
        if(x < 0 || x >= getWidth())
            throw new IndexOutOfBoundsException();
        if(y < 0 || y >= getHeight())
            throw new IndexOutOfBoundsException();

        int index = x + (width * y);
        return grid[index];

    }

    @Override
    // No idea
    public Iterator<ILocation> iterator() {
        return null;
    }
}
