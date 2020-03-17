package biblioteket.roborally.actors;

import biblioteket.roborally.board.Board;
import biblioteket.roborally.board.DirVector;
import biblioteket.roborally.board.Direction;

public interface IRobot {

    /**
     * Get the player who is the owner of the robot.
     *
     * @return the player who is the owner of the robot.
     */
    IPlayer getPlayer();

    /**
     * Set the player who is the owner of the robot.
     *
     * @param player
     */
    void setPlayer(IPlayer player);

    /**
     * Return number of damage tokens a robot has.
     *
     * @return number of accumulated damage tokens
     */
    int getNumberOfDamageTokens();

    /**
     * Removes as many damage tokens from robot as specified.
     *
     * @param damageTokens
     */
    void removeDamageTokens(int damageTokens);

    /**
     * Add as many damage token to robot as specified.
     *
     * @param damageTokens
     */
    void addDamageTokens(int damageTokens);

    /**
     * Returns true if robot has accumulated more than 10 damage tokens.
     *
     * @return true if robot is destroyed.
     */
    boolean isDestroyed();

    /**
     * Retrieves the location of the robots archive marker.
     * Which is th place it will be revived.
     *
     * @return location of archive marker.
     */
    DirVector getArchiveMarker();

    /**
     * Set the robots archive marker, which is where it will be revived.
     *
     * @param location
     */
    void setArchiveMarker(DirVector location);

    /**
     * Changes the direction the robot faces.
     * Turns the robot 90 degrees to the left.
     */
    void turnLeft();

    /**
     * Changes the direction the robot faces.
     * Turns the robot 90 degrees to the right.
     */
    void turnRight();

    /**
     * Changes the position of the robot.
     * Moves the robot one step in the direction it faces.
     */
    void moveForward();

    /**
     * Changes the position of the robot.
     * Moves the robot one step back away from the direction it faces.
     */
    void moveBackward();

    /**
     * Changes position of robot.
     * Pushes the robot in given direction.
     * Cannot be pushed into a wall,
     * but can be pushed of the board.
     *
     * @param direction
     */
    void pushRobotInDirection(Direction direction);

    /**
     * Checks if the robot can move in given direction.
     * The robot will not be allowed to move into a wall,
     * but will be allowed to move of the board.
     *
     * @param direction
     * @return true if robot can move in given direction.
     */
    boolean canMoveInDirection(Direction direction);

    /**
     * Gets the robots current position.
     *
     * @return current position of robot
     */
    DirVector getPosition();

    /**
     * Updates the robots current position.
     *
     * @param location new position of robot
     */
    void setPosition(DirVector location);

    /**
     * Updates the robots current position using a (x,y) coordinate pair.
     *
     * @param x x position on board
     * @param y y position on board
     */
    void setPosition(int x, int y);

    /**
     * Gets the robots direction.
     *
     * @return robot direction
     */
    Direction getDirection();

    /**
     * Changes the robots current direction.
     *
     * @param direction direction to face.
     */
    void setDirection(Direction direction);

    /**
     * Moves the robot forward a single cell.
     *
     * @param board current game board
     * @return true if moved, false otherwise
     */
    boolean moveForward(Board board);

    /**
     * Moves the robot in a given direction, if it falls of the map we add a
     * single damage token and move it back to its archive marker.
     *
     * @param direction direction to move in
     * @param board     current game board
     * @return true if moved, false otherwise
     */
    boolean move(Direction direction, Board board);
}