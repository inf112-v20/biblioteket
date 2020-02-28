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
     * @return true if added successfully, false otherwise
     */
    boolean put(T element); // false hvis feks det allerede er en robot på denne location

    /**
     * @return a list containing all contents of the current position
     */
    List<T> getContents();

    /**
     * @return true if Position contains robot, false otherwise
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

}
