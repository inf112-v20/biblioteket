package biblioteket.roborally.mapreader;

import biblioteket.roborally.elements.ElementCreator;
import biblioteket.roborally.elements.IElement;
import biblioteket.roborally.grid.Direction;
import biblioteket.roborally.grid.GameBoard;
//import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
//import com.badlogic.gdx.scenes.scene2d.ui.Cell;
//
//import java.util.Iterator;

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
        addWalls();
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
            this.gameBoard.placeElement(x,y,element);
            System.out.println("Added " + element + " at " + x + "," + y);
        }
    }

    private void addWalls(){
        int wallIndex = map.getLayers().getIndex("Wall");
        if(wallLayer != null){
            for (int x = 0; x < tileHeight; x++) {
                for (int y = tileWidth-1; y >= 0; y--) {
                    TiledMapTileLayer.Cell cell = wallLayer.getCell(x,y);
                    if (cell != null){
                        int ID = cell.getTile().getId();
                        setWall(x,y,ID);
                    }
                }
            }
        }

    }


    private void setWall(int x, int y, int ID){
        Direction yDirection = null;
        Direction xDirection = null;

        // This should be properly abstracted out, maybe in enum
        if(ID == 31){
            yDirection = Direction.NORTH;
        } else if (ID == 23){
            xDirection = Direction.EAST;
        } else if (ID == 29){
            yDirection = Direction.SOUTH;
        } else if (ID == 30){
            xDirection = Direction.WEST;
        } else if (ID == 8){
            xDirection = Direction.EAST;
            yDirection = Direction.SOUTH;
        } else if (ID == 16){
            xDirection = Direction.EAST;
            yDirection = Direction.NORTH;
        } else if (ID == 24){
            xDirection = Direction.WEST;
            yDirection = Direction.NORTH;
        } else if (ID == 32){
            xDirection = Direction.WEST;
            yDirection = Direction.SOUTH;
        } else{
            System.out.println("Invalid ID: " + ID);
        }

//        System.out.println("Wall " + xDirection + ", " + yDirection + " placed at " + gameBoard.getPosition(x,mapHeight-1-y));
        this.gameBoard.setWall(x, mapHeight-1-y, xDirection, yDirection);
    }



}
