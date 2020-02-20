package biblioteket.roborally;

import java.util.Iterator;

public class Grid<T> implements IGrid<T> {
    private int height;
    private int width;
    private IPosition[][] grid;     // Each position holds all objects in that position

    public Grid(int height, int width){
        this.height = height;
        this.width = width;

        grid = new IPosition[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = null;  // new Position/Location
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
    public void placeElement(int x, int y, T element) {
        grid[y][x].put(element);
    }

    @Override
    public void placeElement(IPosition position, T element) {
        placeElement(position.getX(), position.getY(), element);
    }

    @Override
    public IPosition getPosition(int x, int y) {
        return null;
    }

    @Override
    public Iterator<IPosition> iterator() {
        return null;
    }
}
