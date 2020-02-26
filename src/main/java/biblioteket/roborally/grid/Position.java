package biblioteket.roborally.grid;

import biblioteket.roborally.actors.IRobot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Position<T> implements IPosition<T> {
    private final int x, y;         // The x and y location of the position on the grid.
    private ArrayList<T> contents;  // The contents of the position

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        contents = new ArrayList<>();
    }

    @Override
    public boolean remove(T element) {
        if (contents.contains(element)) {
            contents.remove(element);
            return true;
        }
        return false;
    }

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
    public boolean containsRobot() {
        for (T t : getContents()){
            if (t instanceof IRobot<?>)
                return true;
        }
        return false;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position<?> position = (Position<?>) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}
