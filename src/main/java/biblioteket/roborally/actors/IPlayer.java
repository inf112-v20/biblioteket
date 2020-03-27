package biblioteket.roborally.actors;

import biblioteket.roborally.programcards.ICard;
import biblioteket.roborally.programcards.ICardDeck;
import biblioteket.roborally.userinterface.InterfaceRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.List;

public interface IPlayer {

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
     * A cell representation of the current player. This is for example an image
     * that is used to identify each player at a glance on the board.
     *
     * @return players cell.
     */
    TiledMapTileLayer.Cell getPlayerCell();

    /**
     * @return players interface renderer
     */
    InterfaceRenderer getInterfaceRenderer();

    /**
     * Sets number of lives and flags in the interface renderer
     */
    void updateInterfaceRenderer();


    /**
     * Draw a number of cards according to how many damage tokens robot has
     *
     * @param cardDeck to draw cards from
     */
    void drawCards(ICardDeck cardDeck);

    /**
     * @param card added
     */
    void addCardToProgramRegister(ICard card);

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

}