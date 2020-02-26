package biblioteket.roborally.grid;

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
     * will return false if position is out of boudnds
     *
     * @param x       position
     * @param y       position
     * @param element to be placed
     * @return true if element was successfully places, false otherwise
     */
    boolean placeElement(int x, int y, T element);

    /**
     * @param position
     * @param element
     * @return true if element was successfully places, false otherwise
     */
    boolean placeElement(IPosition<T> position, T element);

    /**
     * @param x position
     * @param y position
     * @return the IPosition in a given x,y location if it is withing bounds, null otherwise
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
     * @param x position
     * @param y position
     * @param direction to find new position
     * @return IPosition in the given direction from the given position, or null if out of bounds
     */
    IPosition<T> positionInDirection(int x, int y, Direction direction);

    /**
     * @param currentPosition
     * @param direction
     * @return IPosition in the given direction from the given position, or null if out of boudns
     */
    IPosition<T> positionInDirection(IPosition<T> currentPosition, Direction direction);

}
