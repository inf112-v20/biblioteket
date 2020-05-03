package biblioteket.roborally.actors;

import biblioteket.roborally.board.Direction;
import biblioteket.roborally.programcards.ICard;
import biblioteket.roborally.programcards.ICardDeck;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.List;

public interface IActor {
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
     * @param delay     milliseconds delay after move is rendered before next move is rendered
     * @param debug     whether to print debug information
     * @param pushed
     * @return true if robot moved, false otherwise
     */
    boolean moveRobot(Direction direction, int delay, boolean debug, boolean pushed);

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
     * Set the players robot.
     *
     * @param robot robot that player will control.
     */
    void setRobot(IRobot robot);

    /**
     * Gets the players name.
     *
     * @return the name of the actor
     */
    String getName();

    /**
     * Sets the name of the player to be displayed in the players interface
     *
     * @param name name of player
     */
    void setName(String name);

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
     * Update the interface for the current player, e.g. his new card deck
     * and selected cards etc.
     */
    void updateInterfaceRenderer();

    /**
     * Draw a new deck of cards from a random selection of all possible
     * cards. Chooses a valid amount of cards to draw.
     *
     * @param cardDeck a deck of cards
     */
    void drawCards(ICardDeck cardDeck);

    /**
     * Player draws new cards, updates interface renderer and sets
     * canMove flag to true
     *
     * @param cardDeck the new card deck for the player
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
     * @return a list of the players program register
     */
    List<ICard> getProgramRegister();

    /**
     * @return true if the player is done registering cards for this turn
     */
    boolean fullProgramRegister();

    /**
     * Get the player sprite (or cell as it is called libgdx).
     *
     * @return players sprite
     */
    TiledMapTileLayer.Cell getPlayerCell();

    /**
     * Fire a laser from robots current position
     */
    void fireLaser(List<IActor> players);

    /**
     * Handle destruction of a players robot, i.e. removing life points, moving
     * the robot to a new spawn point and giving it a damage token.
     *
     * @param delay animation delay
     */
    void handleRobotDestruction(int delay);

    /**
     * Announce intent to power down next turn
     */
    void announcePowerDown();

    /**
     * @return true if players robot is powered down next turn
     */
    boolean hasAnnouncedPowerDown();

    /**
     * @return true if player is powered down this turn
     */
    boolean isPoweredDown();
}