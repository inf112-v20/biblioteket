package biblioteket.roborally.Grid;

import biblioteket.roborally.Direction;

import java.util.List;

public interface IGrid<T> {

    /**
     * @return the width of the board
     */
    int getWidth();

    /**
     * @return the height of the board
     */
    int getHeight();

    /**
     * Places an element in an IPosition in a given x,y location
     *
     * @param x       position
     * @param y       position
     * @param element to be placed
     */
    void placeElement(int x, int y, T element);

    /**
     * @param x position
     * @param y position
     * @return the IPosition in a given x,y location
     */
    IPosition<T> getPosition(int x, int y);

    /**
     * Iterate over neighbours in up to eight directions around location. <p>
     * Will yield less than eight neighbours if the current location is
     * at the edge of board area.
     *
     * @param x position
     * @param y position
     * @return List of locations
     */
    List<IPosition<T>> allNeighbours(int x, int y);

    /**
     * @param position IPosition
     * @return list of locations
     */
    List<IPosition<T>> allNeighbours(IPosition<T> position);

    /**
     * Iterate over north/south/east/west neighbours. <p>
     * Will yield less than eight neighbours if the current location is
     * at the edge of board area.
     *
     * @param x position
     * @param y position
     * @return List of locations
     */
    List<IPosition<T>> cardinalNeighbours(int x, int y);

    /**
     * @param position IPosition
     * @return List of locations
     */
    List<IPosition<T>> cardinalNeighbours(IPosition<T> position);

    /**
     * Checks if a given direction from a location contains an immovable object
     * Immovable objects consist of walls and lasers
     *
     * @param currentPosition
     * @param direction       cardinal direction from current position to check
     * @return true if robot can move in given direction.
     */
    boolean containsImmovableObject(IPosition<T> currentPosition, Direction direction);
}
