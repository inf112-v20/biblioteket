package biblioteket.roborally.actors;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

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
     * @return
     */
    IRobot getRobot();

    /**
     * Set the players robot.
     *
     * @param robot
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
}