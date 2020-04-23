package biblioteket.roborally.actors;

import biblioteket.roborally.board.IBoard;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class Player extends Actor implements IPlayer {
    public Player(IBoard board, TiledMapTileLayer.Cell playerCell, InterfaceRenderer interfaceRenderer, RobotRenderer robotRenderer) {
        super(board, playerCell, interfaceRenderer, robotRenderer);
    }
}
