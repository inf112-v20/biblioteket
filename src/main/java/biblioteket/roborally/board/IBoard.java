package biblioteket.roborally.board;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public interface IBoard {
    /**
     * @return the current map
     */
    TiledMap getMap();

    /**
     * Returns the width of the map.
     * @return int
     */
    int getWidth();

    /**
     * Returns the height of the map.
     * @return int
     */
    int getHeight();

    /**
     * Returns the width of each individual tile.
     * @return int
     */
    int getTileWidth();

    /**
     * Returns the width of each individual tile.
     * @return int
     */
    int getTileHeight();

    /**
     * Returns a layer matching the input names, otherwise returns null.
     * @return layer
     */
    TiledMapTileLayer getLayer(String layerName);
}
