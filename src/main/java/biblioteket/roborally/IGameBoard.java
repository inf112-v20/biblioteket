package biblioteket.roborally;

import biblioteket.roborally.grid.IPosition;

public interface IGameBoard {

    /**
     * Checks if a given direction from a location contains an immovable object
     * Immovable objects consist of walls and lasers
     *
     * @param x position
     * @param y position
     * @param direction       cardinal direction from current position to check
     * @return true if position in given direction contains an immovable object
     */
    boolean containsImmovableObject(int x, int y, Direction direction);

    /**
     * @param currentPosition
     * @param direction from current position
     * @return true if position in given direction contains an immovable object
     */
    boolean containsImmovableObject(IPosition<IElement> currentPosition, Direction direction);


    /**
     * @param x position
     * @param y position
     * @param direction
     * @return first IPosition in a given cardinal direction with first collision or null
     * if no collision in that direction
     */
    IPosition<IElement> firstCollisionInDirection(int x, int y, Direction direction);

    /**
     * @param currentPosition
     * @param direction
     * @return first IPosition in a given cardinal direction with first collision or null
     * if no collision in that direction
     */
    IPosition<IElement> firstCollisionInDirection(IPosition<IElement> currentPosition, Direction direction);

}
