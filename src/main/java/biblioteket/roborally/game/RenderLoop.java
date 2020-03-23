package biblioteket.roborally.game;

import biblioteket.roborally.actors.IPlayer;
import biblioteket.roborally.board.Board;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import java.util.List;

public class RenderLoop {
    private final OrthogonalTiledMapRenderer tiledMapRenderer;
    private final Board board;
    private final List<IPlayer> players;

    public RenderLoop(OrthogonalTiledMapRenderer tiledMapRenderer, Board board, List<IPlayer> players) {
        this.tiledMapRenderer = tiledMapRenderer;
        this.board = board;
        this.players = players;

    }

    public void render(){
        for (IPlayer player : players) {
            board.getPlayerLayer().setCell(player.getRobot().getPosition().getX(),player.getRobot().getPosition().getY(), player.getPlayerCell());
        }

        tiledMapRenderer.render();
        tiledMapRenderer.getBatch().begin();
        tiledMapRenderer.renderTileLayer(board.getPlayerLayer());
        tiledMapRenderer.getBatch().end();

        for (IPlayer player : players) {
            board.getPlayerLayer().setCell(player.getRobot().getPosition().getX(), player.getRobot().getPosition().getY(), null);
        }
    }
}
