package biblioteket.roborally.elements.walls;

import biblioteket.roborally.board.Direction;
import biblioteket.roborally.elements.IElement;

public class WallElement implements IElement {
    // x,y direction the wall is facing (East/West, North/South)
    // One direction can be null if wall is only facing one side
    protected final Direction xDirection;
    protected final Direction yDirection;

    public WallElement(Direction xDirection, Direction yDirection) {
        this.xDirection = xDirection;
        this.yDirection = yDirection;
    }

    @Override
    public boolean blocking(Direction direction, boolean exit) {
        if (exit) {
            return direction == this.xDirection || direction == this.yDirection;
        }
        Direction from = direction.opposite();
        return from == this.xDirection || from == this.yDirection;
    }

    @Override
    public String toString() {
        return "Wall " + xDirection + ", " + yDirection;
    }
}
