package biblioteket.roborally.Grid;

import biblioteket.roborally.BoardDirections;

import java.util.ArrayList;
import java.util.List;

public class Grid<T> implements IGrid<T> {
    private int height;
    private int width;
    private List<IPosition<T>> grid;     // Each position holds all objects in that position

    public Grid(int width, int height){
        this.height = height;
        this.width = width;

        grid = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int index = getIndex(x,y);
                grid.add(new Position<>(x,y));
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
        if(checkIndexOutOfBounds(x,y))
            throw new IndexOutOfBoundsException();
        int index = getIndex(x,y);
        grid.get(index).put(element);
    }

    @Override
    public IPosition getPosition(int x, int y) {
        if(checkIndexOutOfBounds(x,y))
            throw new IndexOutOfBoundsException();
        int index = getIndex(x,y);
        return grid.get(index);

    }

    @Override
    public List<IPosition<T>> allNeighbours(int x, int y) {
        List<IPosition<T>> allNeighbours = new ArrayList<>();
        return null;
    }

    @Override
    public List<IPosition<T>> allNeighbours(IPosition<T> position) {
        return allNeighbours(position.getX(), position.getY());
    }

    @Override
    public List<IPosition<T>> cardinalNeighbours(int x, int y) {
        return null;
    }

    @Override
    public List<IPosition<T>> cardinalNeighbours(IPosition<T> position) {
        return cardinalNeighbours(position.getX(), position.getY());
    }

    @Override
    public boolean containsImmovableObject(IPosition<T> currentPosition, BoardDirections direction) {
        return false;
    }

    /**
     * @param x position
     * @param y position
     * @return grid index for the given x,y coordinates
     */
    private int getIndex(int x, int y){
        return x + (width * y);
    }

    /**
     * @param x position
     * @param y position
     * @return true if index is out of bounds, false otherwise
     */
    private boolean checkIndexOutOfBounds(int x, int y){
        return x < 0 || x >= getWidth() || y < 0 || y >= getHeight();
    }

}
