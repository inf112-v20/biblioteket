package biblioteket.roborally.mapreader;

import biblioteket.roborally.elements.ElementCreator;
import biblioteket.roborally.elements.IElement;
import biblioteket.roborally.grid.Direction;
import biblioteket.roborally.grid.GameBoard;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;


/**
 * Takes a TiledMap and reads the walls to a GameBoard
 */
public class MapReader {
    private TiledMap map;
    private GameBoard gameBoard;

    private MapProperties properties;

    int tileWidth;
    int tileHeight;
    int mapWidth;
    int mapHeight;

    private TiledMapTileLayer wallLayer;

    public MapReader(TiledMap map){
        this.map = map;

        properties = map.getProperties();
        tileWidth = properties.get("tilewidth", Integer.class);
        tileHeight = properties.get("tileheight", Integer.class);
        mapWidth = properties.get("width", Integer.class);
        mapHeight = properties.get("height", Integer.class);

        this.gameBoard = new GameBoard(mapWidth, mapHeight);

        wallLayer = (TiledMapTileLayer) map.getLayers().get("Wall");
//        addWalls();
        readMap(map);
    }

    public GameBoard getGameBoard(){
        return this.gameBoard;
    }

    private void readMap(TiledMap map){
        MapLayers layers = map.getLayers();
        int numLayers = layers.size();

        for (int x = 0; x < tileHeight; x++) {
            for (int y = tileWidth - 1; y >= 0; y--) {
                for (int i = 0; i < numLayers; i++) {
                    TiledMapTileLayer layer = (TiledMapTileLayer)layers.get(i);

                    if(layer != null && layer.getCell(x,y) != null){
                        int ID = layer.getCell(x,y).getTile().getId();
                        putElement(x,y,ID);
                    }
                }
            }
        }
    }

    private void putElement(int x, int y, int id) {
        IElement element = ElementCreator.getElement(id);
        if(element != null){
            this.gameBoard.placeElement(x,mapHeight-1-y,element);
            System.out.println("Added " + element + " at " + x + "," + y);
        }
    }

}
