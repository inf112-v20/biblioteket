package biblioteket.roborally.grid;

import java.util.List;

/**
 * A Position is represents a single spot in a grid, holds everything that goes
 * in that part of the grid.
 * The x and y positions of the grid can not change, but the contents of the position
 * can change.
 *
 * @param <T> What a position can contain
 */
public interface IPosition<T> {


    /**
     * Removes and returns a certain element from the current grid
     *
     * @param element to be removed
     * @return true if the element was successfully removed, false otherwise
     * @throws Exception if no such element exists in current position
     */
    boolean remove(T element);

    /**
     * Adds a certain element to the position
     *
     * @param element to be added
     * @return true if added successfully
     */
    boolean put(T element); // false hvis feks det allerede er en robot på denne location

    /**
     * @return a list containing all contents of the current position
     */
    List<T> getContents();

    /**
     * @return true if Position contains robot
     */
    boolean containsRobot();

    /**
     * @return the x location of the position
     */
    int getX();

    /**
     * @return the y location of the position
     */
    int getY();

    /**
     * Add a new wall to position
     * If the wall is only in one direction, let the unused direction be null
     * @param y direction of wall (North/South)
     * @param x direction of wall (East/West)
     * @return false if position already contains a wall, true if setting wall was successfull
     */
    boolean setWall(Direction y, Direction x);

    /**
     * Checks if there is a wall blocking exit from current position in given direction
     * @param to the direction robot is moving from the position
     * @return true if robot can not move from this position in the given direction
     */
    boolean wallBlockingExit(Direction to);

    /**
     * Checks if there is a wall blocking entry to this position in a given direction
     * @param to the direction robot is moving to the position
     * @return true if robot can not move to this position in the given direction
     */
    boolean wallBlockingEntry(Direction to);

}
