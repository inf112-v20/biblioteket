// package biblioteket.roborally.grid;

// import biblioteket.roborally.board.Direction;
// import biblioteket.roborally.elements.IElement;

// public interface IGameBoard {

    // /**
    //  * Checks if a given direction from a location contains an immovable object
    //  * Immovable objects consist of walls and lasers
    //  *
    //  * @param x position
    //  * @param y position
    //  * @param direction       cardinal direction from current position to check
    //  * @return true if position in given direction contains an immovable object
    //  */
    // boolean containsImmovableObject(int x, int y, Direction direction);

    // /**
    //  * @param currentPosition
    //  * @param direction from current position
    //  * @return true if position in given direction contains an immovable object
    //  */
    // boolean containsImmovableObject(IPosition<IElement> currentPosition, Direction direction);

    // boolean containsImmovableObject(int x, int y);

    // boolean containsImmovableObject(IPosition<IElement> currentPosition);

    // /**
    //  * @param x position
    //  * @param y position
    //  * @param direction
    //  * @return first IPosition in a given cardinal direction with first collision or null
    //  * if no collision in that direction
    //  */
    // IPosition<IElement> firstCollisionInDirection(int x, int y, Direction direction);

    // /**
    //  * @param currentPosition
    //  * @param direction
    //  * @return first IPosition in a given cardinal direction with first collision or null
    //  * if no collision in that direction
    //  */
    // IPosition<IElement> firstCollisionInDirection(IPosition<IElement> currentPosition, Direction direction);

    // /**
    //  * Checks if the position robot is moving to contains immovable object or a wall is blocking the way,
    //  * or if move puts robot out of bounds
    //  * @param from position robot is moving from
    //  * @param direction robot is moving
    //  * @return true is move is legal
    //  */
    // boolean canMove(IPosition<IElement> from, Direction direction);

//     /**
//      * @param x position
//      * @param y position
//      * @param direction robot is moving
//      * @return true if move is legal
//      */
//     boolean canMove(int x, int y, Direction direction);
// }
