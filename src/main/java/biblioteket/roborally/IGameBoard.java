package biblioteket.roborally;

import biblioteket.roborally.grid.IPosition;

public interface IGameBoard {

    /**
     * Checks if a given direction from a location contains an immovable object
     * Immovable objects consist of walls and lasers
     *
     * @param currentPosition
     * @param direction       cardinal direction from current position to check
     * @return true if robot can move in given direction.
     */
    boolean containsImmovableObject(IPosition<IElement> currentPosition, Direction direction);


    IPosition<IElement> firstCollisionInDirection(IPosition<IElement> currentPosition, Direction direction);

}
