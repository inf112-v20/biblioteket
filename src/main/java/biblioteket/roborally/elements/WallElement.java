package biblioteket.roborally.elements;

import biblioteket.roborally.board.Direction;

public class WallElement implements IElement {
    // x,y direction the wall is facing (East/West, North/South)
    // One direction can be null if wall is only facing one side
    private final Direction xDirection;
    private final Direction yDirection;

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

    // /**
    //  * @param direction actor wants to exit position of wall
    //  * @return true if wall is blocking the exit in that direction
    //  */
    // @Override
    // public boolean blockingExit(Direction direction){
    //     return direction == this.xDirection || direction == this.yDirection;
    // }
    //
    // /**
    //  * @param direction the actor wants to enter the position of the wall
    //  * @return true if wall is blocking entry in that direction
    //  */
    // @Override
    // public boolean blockingEntry(Direction direction){
    //     Direction from = direction.opposite();
    //     return from == this.xDirection || from == this.yDirection;
    // }

    @Override
    public String toString() {
        return "Wall " + xDirection + ", " + yDirection;
    }
}
