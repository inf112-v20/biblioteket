package biblioteket.roborally;

import biblioteket.roborally.board.DirVector;
import biblioteket.roborally.board.Direction;

public interface IElement {
    /**
     * @return the current position of the element.
     */
    DirVector getPosition();

    /**
     * @param pos the position on the board
     */
    void setPosition(DirVector pos);

    /**
     * @param x position on the grid
     * @param y position on the grid
     */
    void setPosition(int x, int y);

    /**
     * Some items in the game does not allow a robot to pass through or stand
     * on the same tile as them, therefore we need a way to check whether an
     * immovable objects meets the unstoppable robot.
     *
     * @return true if immovable, false otherwise.
     */
    boolean immovable();

    /**
     * @return the current direction the element is facing.
     */
    Direction getDirection();

    /**
     * @param direction the direction the element should face.
     */
    void setDirection(Direction direction);
}
