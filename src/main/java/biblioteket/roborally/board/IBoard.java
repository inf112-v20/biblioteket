package biblioteket.roborally.board;

import biblioteket.roborally.actors.IRobot;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

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
     * Checks if a given direction from a location contains an immovable object
     * Immovable objects consist of walls and lasers
     *
     * @param x position
     * @param y position
     * @param direction       cardinal direction from current position to check
     * @return true if position in given direction contains an immovable object
     */
    boolean containsImmovableObject(int x, int y, Direction direction);

    /**
     * @param currentPosition
     * @param direction from current position
     * @return true if position in given direction contains an immovable object
     */
    boolean containsImmovableObject(DirVector currentPosition, Direction direction);

    boolean containsImmovableObject(int x, int y);

    boolean containsImmovableObject(DirVector currentPosition);

    /**
     * @param x position
     * @param y position
     * @param direction
     * @return first IPosition in a given cardinal direction with first collision or null
     * if no collision in that direction
     */
    DirVector firstCollisionInDirection(int x, int y, Direction direction);

    /**
     * @param currentPosition
     * @param direction
     * @return first IPosition in a given cardinal direction with first collision or null
     * if no collision in that direction
     */
    DirVector firstCollisionInDirection(DirVector currentPosition, Direction direction);

    /**
     * Checks if the position robot is moving to contains immovable object or a wall is blocking the way,
     * or if move puts robot out of bounds
     * @param from position robot is moving from
     * @param direction robot is moving
     * @return true is move is legal
     */
    boolean canMove(DirVector from, Direction direction);

    /**
     * @param x position
     * @param y position
     * @param direction robot is moving
     * @return true if move is legal
     */
    boolean canMove(int x, int y, Direction direction);

    DirVector interact(IRobot robot);
}
