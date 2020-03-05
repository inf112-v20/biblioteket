package biblioteket.roborally.elements;

import biblioteket.roborally.grid.Direction;
import biblioteket.roborally.grid.IPosition;

import java.util.UUID;

public interface IElement {
    /**
     * @return the current position of the element.
     */
    IPosition getPos();

    /**
     * @param pos the position on the board
     */
    void setPos(IPosition pos);

    /**
     * @param x position on the grid
     * @param y position on the grid
     */
    void setPos(int x, int y);

    /**
     * Some items in the game does not allow a robot to pass through or stand
     * on the same tile as them, therefore we need a way to check whether an
     * immovable objects meets the unstoppable robot.
     *
     * @return true if immovable, false otherwise.
     */
    boolean immovable();

    /**
     * An element has a uniquely generated ID so that one can easily distinguish
     * between items that are the same class but not actually equal.
     *
     * @return the elements unique ID
     */
    UUID getID();

    /**
     * @return the current direction the element is facing.
     */
    Direction getDirection();

    /**
     * @param direction the direction the element should face.
     */
    void setDirection(Direction direction);
}
