package biblioteket.roborally.elements;

import biblioteket.roborally.grid.Direction;

public class WallElement implements IElement {

    // x,y direction the wall is facing (East/West, North/South)
    // One direction can be null if wall is only facing one side
    private final Direction xDirection;
    private final Direction yDirection;

    public WallElement(Direction xDirection, Direction yDirection) {
        this.xDirection = xDirection;
        this.yDirection = yDirection;
    }

    /**
     * @param direction actor wants to exit position of wall
     * @return true if wall is blocking the exit in that direction
     */
    public boolean blockingExit(Direction direction){
        return direction == this.xDirection || direction == this.yDirection;
    }

    /**
     * @param direction the actor wants to enter the position of the wall
     * @return true if wall is blocking entry in that direction
     */
    public boolean blockingEntry(Direction direction){
        Direction from = direction.oppositeDirection();
        return from == this.xDirection || from == this.yDirection;
    }

    @Override
    public String toString(){
        return "Wall " + xDirection +", " + yDirection;
    }


}
