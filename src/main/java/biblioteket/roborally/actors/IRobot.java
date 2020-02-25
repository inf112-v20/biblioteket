package biblioteket.roborally.actors;

import biblioteket.roborally.Direction;
import biblioteket.roborally.IPosition;

public interface IRobot {

    /**
     * Set the player who is the owner of the robot.
     * @param player
     */
    void setPlayer(IPlayer player);

    /**
     * Get the player who is the owner of the robot.
     * @return the player who is the owner of the robot.
     */
    IPlayer getPlayer();


    /**
     * Return number of damage tokens a robot has.
     * @return number of accumulated damage tokens
     */
    int getNumberOfDamageTokens();

    /**
     * Removes as many damage tokens from robot as specified.
     * @param damageTokens
     */
    void removeDamageTokens(int damageTokens);

    /**
     * Add as many damage token to robot as specified.
     * @param damageTokens
     */
    void addDamageTokens(int damageTokens);

    /**
     * Remove all the damage tokens the robot has accumulated.
     */
    void removeAllDamageTokens();

    /**
     * Returns true if robot has accumulated more than 10 damage tokens.
     * @return true if robot is destroyed.
     */
    boolean isDestroyed();

    /**
     * Set the robots archive marker, which is where it will be revived.
     * @param location
     */
    void setArchiveMarker(IPosition location);

    /**
     * Retrieves the location of the robots archive marker.
     * Which is th place it will be revived.
     * @return location of archive marker.
     */
    IPosition getArchiveMarker();

    /**
     * Set the position of the robot, given as location.
     * @param location
     */
    void setPosition(IPosition location);

    /**
     * Get the position of the robot, retrieved as a location.
     * @return
     */
    IPosition getPosition();

    /**
     * Set the direction the robot faces.
     * @param direction
     */
    void setDirection(Direction direction);

    /**
     * Returns the direction the robot faces.
     * @return the direction the robot faces.
     */
    Direction getDirection();

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
     * @param direction
     */
    void pushRobotInDirection(BoardDirections direction);

    /**
     * Checks if the robot can move in given direction.
     * The robot will not be allowed to move into a wall,
     * but will be allowed to move of the board.
     * @param direction
     * @return true if robot can move in given direction.
     */
    boolean canMoveInDirection(Direction direction);

}