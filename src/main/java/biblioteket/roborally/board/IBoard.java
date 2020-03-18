package biblioteket.roborally.board;

import biblioteket.roborally.actors.IPlayer;
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
     * Checks if you move out of bounds of the board.
     *
     * @param dir direction to move
     * @return true if out of board, false otherwise
     */
    boolean outOfBounds(DirVector dir);

    /**
     * Checks if the position robot is moving to contains immovable object or a wall is blocking the way,
     * or if move puts robot out of bounds
     *
     * @param direction robot is moving
     * @return true is move is legal
     */
    boolean canMove(IRobot robot, Direction direction);

    /**
     * Interactions between a robot and the environment, i.e. between
     * {@link biblioteket.roborally.elements.InteractingElement} and a {@link biblioteket.roborally.actors.Robot}.
     *
     * @param player with current robot moving on the board.
     * @return new position of robot after having been interacted with.
     */
    DirVector interact(IPlayer player);
}
