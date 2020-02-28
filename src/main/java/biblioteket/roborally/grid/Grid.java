package biblioteket.roborally.grid;

import biblioteket.roborally.Direction;

import java.util.ArrayList;
import java.util.List;

public class Grid<T> implements IGrid<T> {
    private final int height;
    private final int width;
    private List<IPosition<T>> grid;     // Each position holds all objects in that position

    public Grid(int width, int height) {
        this.height = height;
        this.width = width;

        grid = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int index = getIndex(x, y);
                grid.add(new Position<>(x, y));
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
    public boolean placeElement(int x, int y, T element) {
        if (checkIndexOutOfBounds(x, y))
            return false;
        int index = getIndex(x, y);
        grid.get(index).put(element);
        return true;
    }

    @Override
    public boolean placeElement(IPosition<T> position, T element) {
        return placeElement(position.getX(), position.getY(), element);
    }

    @Override
    public IPosition<T> getPosition(int x, int y) {
        if (checkIndexOutOfBounds(x, y))
            return null;
        int index = getIndex(x, y);
        return grid.get(index);

    }

    @Override
    public List<IPosition<T>> allNeighbours(int x, int y) {
        List<IPosition<T>> allNeighbours = new ArrayList<>();

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (!(i == x && j == y)) {
                    IPosition<T> pos = getPosition(i, j);
                    if (!(pos == null)) allNeighbours.add(pos);
                }
            }
        }
        return allNeighbours;
    }

    @Override
    public List<IPosition<T>> allNeighbours(IPosition<T> position) {
        return allNeighbours(position.getX(), position.getY());
    }

    @Override
    public List<IPosition<T>> cardinalNeighbours(int x, int y) {
        ArrayList<IPosition<T>> cardinalNeighbours = new ArrayList<>();

        IPosition<T> north = getPosition(x, y - 1);
        if (!(north == null)) cardinalNeighbours.add(north);
        IPosition<T> south = getPosition(x, y + 1);
        if (!(south == null)) cardinalNeighbours.add(south);
        IPosition<T> east = getPosition(x - 1, y);
        if (!(east == null)) cardinalNeighbours.add(east);
        IPosition<T> west = getPosition(x + 1, y);
        if (!(west == null)) cardinalNeighbours.add(west);

        return cardinalNeighbours;
    }

    @Override
    public List<IPosition<T>> cardinalNeighbours(IPosition<T> position) {
        return cardinalNeighbours(position.getX(), position.getY());
    }

    @Override
    public IPosition<T> positionInDirection(int x, int y, Direction direction) {
        if (direction == Direction.NORTH) {
            return getPosition(x, y - 1);
        } else if (direction == Direction.SOUTH) {
            return getPosition(x, y + 1);
        } else if (direction == Direction.EAST) {
            return getPosition(x + 1, y);
        } else if (direction == Direction.WEST) {
            return getPosition(x - 1, y);
        } else {
            return null;
        }
    }

    @Override
    public IPosition<T> positionInDirection(IPosition<T> currentPosition, Direction direction) {
        return positionInDirection(currentPosition.getX(), currentPosition.getY(), direction);
    }

    /**
     * @param x position
     * @param y position
     * @return grid index for the given x,y coordinates
     */
    private int getIndex(int x, int y) {
        return x + (width * y);
    }

    /**
     * @param x position
     * @param y position
     * @return true if index is out of bounds, false otherwise
     */
    private boolean checkIndexOutOfBounds(int x, int y) {
        return x < 0 || x >= getWidth() || y < 0 || y >= getHeight();
    }

}
