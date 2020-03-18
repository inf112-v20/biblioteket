package biblioteket.roborally.elements;

import biblioteket.roborally.board.Direction;

public interface IElement {
    /**
     * A method to check whether an element blocks the robot from moving.
     *
     * @param direction direction to check in
     * @param exit      whether the robot is exiting the current tile
     * @return true if robot can move, false otherwise
     */
    default boolean blocking(Direction direction, boolean exit){
        return false;
    }
}
