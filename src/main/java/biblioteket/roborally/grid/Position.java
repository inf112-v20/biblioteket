package biblioteket.roborally.grid;

import biblioteket.roborally.Direction;
import biblioteket.roborally.actors.IRobot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Position<T> implements IPosition<T> {
    private final int x, y;         // The x and y location of the position on the grid.
    private ArrayList<T> contents;  // The contents of the position
    private Wall wall;

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
        for (T t : getContents()) {
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

    @Override
    public String toString(){
        return "Position at x,y coordinates " + getX() + "," + getY();
    }

    @Override
    public boolean setWall(Direction y, Direction x) {
        if(containsWall()) return false;

        this.wall = new Wall(x,y);
        return true;
    }

    @Override
    public boolean wallBlockingExit(Direction to) {
        if (!(containsWall())) return false;

        return to == wall.xDirection || to == wall.yDirection;
    }

    @Override
    public boolean wallBlockingEntry(Direction to) {
        if (!(containsWall())) return false;

        Direction from = to.oppositeDirection();
        return from == wall.xDirection || from == wall.yDirection;
    }

    private boolean containsWall(){
        return !(this.wall == null);
    }

    /**
     * Defines the walls in given position
     * A position can have walls in an x and y direction (East/West, North/South)
     * If position only has walls in one direction, let the other direction be null.
     */
    private class Wall {
        private final Direction xDirection, yDirection;


        public Wall(Direction xDirection, Direction yDirection){
            this.xDirection = xDirection;
            this.yDirection = yDirection;
        }

        public Direction getxDirection() {
            return xDirection;
        }

        public Direction getyDirection() {
            return yDirection;
        }
    }


}
