package biblioteket.roborally;

import java.util.Collection;

public interface ILocation {

    /**
     * Sets the x of the position to be the given int.
     * @param xPosition
     */
    void setX(int xPosition);

    /**
     * Returns the x of the position.
     * @return x
     */
    int getX();

    /**
     * Checks if given x is within board boundaries.
     * @param x
     * @return true if x is within board boundaries.
     */
    boolean xIsWithinBoard(int x);

    /**
     * Sets the y of the position to be the given int.
     * @param yPosition
     */
    void setY(int yPosition);

    /**
     * Returns the y of the position.
     * @return y
     */
    int getY();

    /**
     * Checks if given y is within board boundaries.
     * @param y
     * @return true if y is within board boundaries.
     */
    boolean yIsWithinBoard(int y);

    /**
     * Iterate over neighbours in up to eight directions around location. <p>
     * Will yield less than eight neighbours if the current location is
     * at the edge of board area.
     *
     * @return Collection of locations
     */
    Collection<ILocation> allNeighbours();

    /**
     * Iterate over north/south/east/west neighbours. <p>
     * Will yield less than eight neighbours if the current location is
     * at the edge of board area.
     *
     * @return Collection of locations
     */
    Collection<ILocation> cardinalNeighbours();

    /**
     * Checks if the robot can move in given direction.
     * The robot will not be allowed to move into a wall,
     * but will be allowed to move of the board.
     * @param direction
     * @return true if robot can move in given direction.
     */
    boolean canMoveInDirection(BoardDirections direction);

    /**
     * Checks if movement in given direction will result in a location of the board.
     * @param direction
     * @return true if movement in given direction will result in location of the board.
     */
    boolean willMoveOfBoard(BoardDirections direction);

    /**
     * Checks if given location is of the board.
     * @param location
     * @return true if location is of the board.
     */
    boolean isOfBoard(ILocation location);

    /**
     * Checks if location is of the board.
     * @return true if location is of the board.
     */
    boolean isOfBoard();

    /**
     * Checks if given location is within the board boundaries.
     * @param location
     * @return true if location is is withing board boundaries.
     */
    boolean isWithinBoard(ILocation location);

    /**
     * Checks if given location is within the board boundaries.
     * @return true if location is is withing board boundaries.
     */
    boolean isWithinBoard();

    /**
     * Return the location one step in given direction.
     * @param direction
     * @return next location in given direction.
     */
    ILocation locationInDirection(BoardDirections direction);

    /**
     * Returns all location between this location and given location,
     * in order.
     * Including start and given location.
     * @param location
     * @return Collection of locations
     */
    Collection<ILocation> locationsInLineToGivenLocation(ILocation location);

    /**
     * Returns all the location in given direction,
     * from this location to the board boundary.
     * @param direction
     * @return Collection of locations
     */
    Collection<ILocation> locationsInDirection(BoardDirections direction);

    /**
     * Returns all the locations in given direction,
     * from this location to the first obstacle is encountered.
     * Includes this location but NOT the location of the obstacle.
     * An obstacle is anything that would stop a laser.
     * Such as a robot, wall or board boundary.
     * TODO: Won't return location of obstacle as I am not sure what should be returned if out of board boundary.
     * @param direction
     * @return Collection of locations
     */
    Collection<ILocation> locationsInDirectionUntilObstacle(BoardDirections direction);

    /**
     * Returns the location of first obstacle in given direction.
     * An obstacle is anything that would stop a laser.
     * Such as a robot, wall or board boundary.
     * TODO: Should perhaps return something special if obstacle is out of board boundary.
     * @param direction
     * @return Location of first obstacle in given direction.
     */
    ILocation locationInDirectionObstacleIsEncountered(BoardDirections direction);

    /**
     * @param object Another object
     * @return true if object is also an ILocation, and the x and y coordinates are
     *         equal
     */
    @Override
    boolean equals(Object object);

}