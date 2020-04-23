package biblioteket.roborally.actors;

import biblioteket.roborally.board.Direction;
import biblioteket.roborally.programcards.ICard;
import biblioteket.roborally.programcards.ICardDeck;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.List;

public interface IPlayer {

    /**
     * Tries to move robot in the direction robot is currently facing
     *
     * @param delay milliseconds delay after move is rendered before next move is rendered
     * @param steps amount of times robot should try to move
     */
    void moveRobot(int steps, int delay);

    /**
     * Tries to move robot in direction
     *
     * @param direction to move robot
     * @param delay milliseconds delay after move is rendered before next move is rendered
     * @param debug
     * @return
     */
    boolean moveRobot(Direction direction, int delay, boolean debug);

    /**
     * Tries to move robot in the opposite direction of where it is currently facing
     *
     * @param delay milliseconds delay after move is rendered before next move is rendered
     */
    void backUpRobot(int delay);

    /**
     * Rotates the robot to the right or left, updates the cell to display rotation
     *
     * @param right true if robot should rotate right, false if robot should rotate left
     * @param delay milliseconds delay after move is rendered before next move is rendered
     */
    void rotateRobot(boolean right, int delay);

    /**
     * Get the number of lives the player has left.
     *
     * @return The number of lives the player has left
     */
    int getLives();

    /**
     * If player has no more lives the player is considered permanent dead.
     * The robot should not be revived.
     *
     * @return true if player has 0 lives.
     */
    boolean isPermanentDead();

    /**
     * @return true if player has more than 0 lives.
     */
    boolean hasLivesLeft();

    /**
     * Remove a life from the player.
     */
    void removeOneLife();

    /**
     * @return the players robot.
     */
    IRobot getRobot();

    /**
     * Sets the name of the player to be displayed in the players interface
     *
     * @param name
     */
    void setName(String name);

    String getName();

    /**
     * Set the players robot.
     *
     * @param robot robot that player will control.
     */
    void setRobot(IRobot robot);

    /**
     * Return number of flags the player has visited.
     *
     * @return number of flags the player has visited.
     */
    int getNumberOfVisitedFlags();

    /**
     * Adds to how many flags the player has visited
     */
    void addToFlagsVisited();

    /**
     * @return players interface renderer
     */
    InterfaceRenderer getInterfaceRenderer();

    /**
     * Player draws new cards, updates interface renderer and sets
     * canMove flag to true
     *
     * @param cardDeck
     */
    void newTurn(ICardDeck cardDeck);

    /**
     * Adds a card to the program register
     *
     * @param card     card to be added
     * @param cardDeck The cardDeck used in the game.
     */
    void addCardToProgramRegister(ICard card, ICardDeck cardDeck);


    /**
     * returns a list of the players program register and clears the program register
     *
     * @param cardDeck The cardDeck used in the game
     * @return a list of the players program register
     */
    List<ICard> getProgramRegister();

    /**
     * @return true if the player is done registering cards for this turn
     */
    boolean fullProgramRegister();

    TiledMapTileLayer.Cell getPlayerCell();

    void handleRobotDestruction(int delay);
}