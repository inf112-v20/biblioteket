package biblioteket.roborally.elements;

import biblioteket.roborally.grid.Direction;
import biblioteket.roborally.grid.IPosition;

// Usikker på beste måte å håntere walls fordi de oppfører seg litt annerledes enn andre objects
public class WallElement implements IElement {

    private Direction xDirection = null;
    private Direction yDirection = null;

    public WallElement(Direction xDirection, Direction yDirection) {
        this.xDirection = xDirection;
        this.yDirection = yDirection;

    }

    public boolean blockingEntry(Direction direction ){
        Direction from = direction.oppositeDirection();
        return from == this.xDirection || from == this.yDirection;
    }

    public boolean blockingExit(IPosition<IElement> from, Direction direction){
        return direction == this.xDirection || direction == this.yDirection;
    }

    // Temporary
    public Direction getxDirection() {
        return xDirection;
    }

    // Temporary
    public Direction getyDirection() {
        return yDirection;
    }

}
