package biblioteket.roborally.actors;

import biblioteket.roborally.board.Board;
import biblioteket.roborally.board.DirVector;
import biblioteket.roborally.board.Direction;
import biblioteket.roborally.board.IBoard;
import biblioteket.roborally.elements.ArchiveMarkerElement;

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
     * @param player current player
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
     * @param damageTokens number of damages tokens to remove
     */
    void removeDamageTokens(int damageTokens);

    /**
     * Add as many damage token to robot as specified.
     *
     * @param damageTokens number of damage tokens to add
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
    ArchiveMarkerElement getArchiveMarker();

    /**
     * Set the robots archive marker, which is where it will be revived.
     *
     * @param location new archive marker location
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
     *
     * @param board current board
     */
    void moveForward(IBoard board);

    /**
     * Changes the position of the robot.
     * Moves the robot one step back away from the direction it faces.
     *
     * @param board current borad
     */
    void moveBackward(IBoard board);

    /**
     * Changes position of robot.
     * Pushes the robot in given direction.
     * Cannot be pushed into a wall,
     * but can be pushed of the board.
     *
     * @param direction direction to push robot
     */
    void pushRobotInDirection(Direction direction);

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

    /**
     * Tries to move robot in given direction.
     * If it moves of board or fall into a pit player should lose life.
     * It should try to push robots it clashes with.
     *
     * @param direction direction to move robot in
     * @param board     current game board
     */
    void moveRobot(Direction direction, IBoard board);

     /**
     * Each robot that was destroyed this turn
     * reenters play in the space containing its
     * Archive marker. The player chooses which
     * direction the robot faces.
     * <p>
     * If two or more robots would reenter play
     * on the same space, they’re placed back on
     * the board in the order they were destroyed.
     * The first robot that was destroyed gets the archive
     * space, facing any direction that player chooses.
     * The player whose robot was destroyed next then
     * chooses an empty adjacent space (looking
     * orthogonally OR diagonally) and puts the robot on
     * that space. That robot can face any direction that
     * player chooses, except that there can’t be another
     * robot in its line of sight 3 spaces away or closer.
     * Ignore all board elements except for pits when
     * placing your robot in an adjacent space.
     * You can’t start a turn with your robot in a pit.
     * They suffer enough as it is.
     */
    void moveToArchiveMarker();
}