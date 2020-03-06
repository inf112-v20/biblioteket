package biblioteket.roborally.elements;

import biblioteket.roborally.grid.Direction;
import biblioteket.roborally.grid.IPosition;

import java.util.UUID;

public interface IElement {

    //TODO
    // Fjerne immovable, kun vegger er immovable
    /**
     * Some items in the game does not allow a robot to pass through or stand
     * on the same tile as them, therefore we need a way to check whether an
     * immovable objects meets the unstoppable robot.
     *
     * @return true if immovable, false otherwise.
     */
    default boolean immovable(){ return false; }

    default boolean blockingEntry(Direction direction ){
        return false;
    }

    default boolean blockingExit(Direction direction){
        return false;
    }

}
