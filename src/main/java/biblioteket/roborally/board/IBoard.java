package biblioteket.roborally.board;

import biblioteket.roborally.actors.IActor;
import biblioteket.roborally.elements.ArchiveMarkerElement;
import biblioteket.roborally.elements.interacting.InteractingElement;
import biblioteket.roborally.elements.walls.LaserWallElement;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.List;

public interface IBoard {
    /**
     * @return the current map
     */
    TiledMap getMap();

    /**
     * Returns the width of the map.
     *
     * @return int
     */
    int getWidth();

    /**
     * Returns the height of the map.
     *
     * @return int
     */
    int getHeight();

    /**
     * Returns the width of each individual tile.
     *
     * @return int
     */
    int getTileWidth();

    /**
     * Returns the width of each individual tile.
     *
     * @return int
     */
    int getTileHeight();

    /**
     * Returns a layer matching the input names, otherwise returns null.
     *
     * @return layer
     */
    TiledMapTileLayer getLayer(String layerName);

    /**
     * @return the player layer of the tiled map
     */
    TiledMapTileLayer getPlayerLayer();

    /**
     * @return the laser layer of the map
     */
    TiledMapTileLayer getLaserLayer();

    /**
     * Gets an {@link InteractingElement} from the location of a robot, this can
     * be something like a conveyor belt, a rotator or something similar.
     *
     * @param location location to check for elements
     * @return {@link InteractingElement}
     */
    InteractingElement getInteractingElement(DirVector location);

    /**
     * Checks if you move out of bounds of the board.
     *
     * @param dir direction to move
     * @return true if out of board, false otherwise
     */
    boolean outOfBounds(DirVector dir);

    /**
     * @param position current position
     * @return true if there is a HoleElement in position
     */
    boolean isHole(DirVector position);

    /**
     * Checks if position contains a robot and tries to push the robot
     *
     * @param position  position to check
     * @param direction direction to push
     * @return false if position contains a robot that cannot be pushed, true otherwise
     */
    boolean pushRobot(DirVector position, Direction direction);

    /**
     * Checks if the position robot is moving to contains immovable object or a wall is blocking the way,
     * or if move puts robot out of bounds
     *
     * @param position  of robot
     * @param direction robot is moving
     * @return true is move is legal
     */
    boolean canMove(DirVector position, Direction direction);

    /**
     * Interactions between a robot and the environment, i.e. between
     * {@link InteractingElement} and a {@link biblioteket.roborally.actors.Robot}.
     *
     * @param player with current robot moving on the board.
     */
    void interact(IActor player);

    /**
     * If players robot is standing on a flag that can be picked up, registers flag to player
     *
     * @param player with robot registering flag
     */
    void registerFlag(IActor player);

    /**
     * Finds a players archive marker.
     *
     * @return an archive marker for a players ID
     */
    ArchiveMarkerElement getArchiveMarker(int i);

    /**
     * @return the number of flags on the map
     */
    int getNumberOfFlags();


    /**
     * @return a list of all laser walls
     */
    List<LaserWallElement> getLaserWalls();
}
