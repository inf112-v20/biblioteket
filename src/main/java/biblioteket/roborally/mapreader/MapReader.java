package biblioteket.roborally.mapreader;

import biblioteket.roborally.elements.ElementCreator;
import biblioteket.roborally.elements.IElement;
import biblioteket.roborally.grid.GameBoard;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;


/**
 * Reads elements from a TiledMap to a GameBoard
 */
public class MapReader {

    public static GameBoard readMap(TiledMap map){
        MapProperties properties = map.getProperties();
        int mapWidth = properties.get("width", Integer.class);
        int mapHeight = properties.get("height", Integer.class);

        GameBoard gameBoard = new GameBoard(mapWidth, mapHeight);

        MapLayers layers = map.getLayers();
        for (int layer = 0; layer < layers.size(); layer++) {
            for (int x = 0; x < mapHeight; x++) {
                for (int y = 0; y < mapWidth; y++) {
                    TiledMapTileLayer mapLayer = (TiledMapTileLayer)layers.get(layer);

                    if(mapLayer != null && mapLayer.getCell(x,y) != null){
                        int ID = mapLayer.getCell(x,y).getTile().getId();
                        putElement(x,mapHeight - 1 - y, ID, gameBoard);
                    }
                }
            }
        }
        return gameBoard;
    }

    private static void putElement(int x, int y, int ID, GameBoard gameBoard) {
        IElement element = ElementCreator.getElement(ID);
        if(element != null){    // Ignore elements not defined in ElementCreator
            gameBoard.placeElement(x,y,element);
            System.out.println("Added " + element + " at " + x + "," + y);
        }
    }

}
