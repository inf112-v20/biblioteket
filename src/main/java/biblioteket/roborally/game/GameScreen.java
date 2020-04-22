package biblioteket.roborally.game;

import biblioteket.roborally.actors.*;
import biblioteket.roborally.board.Board;
import biblioteket.roborally.board.IBoard;
import biblioteket.roborally.elements.ArchiveMarkerElement;
import biblioteket.roborally.actors.InterfaceRenderer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

import java.util.ArrayList;
import java.util.List;

/**
 * The viewport for the game, allows us to implement the UI separately from
 * the game logic itself. Currently only renders a very simple board with a
 * flag and hole that they player can move around on.
 */
public class GameScreen implements Screen {
    private final IBoard board;
    private final RobotRenderer robotRenderer;
    private final OrthographicCamera camera;

    private final List<IPlayer> players;
    private final GameLoop gameLoop;

    private final OrthogonalTiledMapRenderer tiledMapRenderer;

    private final Texture playerTexture;

    public GameScreen(final RoboRally gam) {
        this.players = new ArrayList<>();
        this.board = new Board("assets/DizzyDash.tmx", players);
        gameLoop = new GameLoop(board, players);
        this.robotRenderer = new RobotRenderer(board.getPlayerLayer(), players, gameLoop);
        this.camera = new OrthographicCamera();

        camera.setToOrtho(false, board.getWidth() + 14, board.getHeight() + 1);
        camera.update();

        tiledMapRenderer = new OrthogonalTiledMapRenderer(board.getMap(), (float) 1 / board.getTileWidth());
        tiledMapRenderer.setView(camera);


        playerTexture = new Texture("assets/player.png");
        TextureRegion[][] playerTextureSplit = TextureRegion.split(playerTexture, board.getTileWidth(), board.getTileHeight());


        for (int i = 1; i <= 2; i++) {
            TiledMapTileLayer.Cell playerCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(playerTextureSplit[0][0]));
            Player player = new Player(board, playerCell, new InterfaceRenderer(), robotRenderer);
            players.add(player);
            ArchiveMarkerElement archiveMarker = board.getArchiveMarker(i);
            IRobot robot = new Robot(archiveMarker);
            player.setRobot(robot);
            player.setName("Player " + i);
            board.getPlayerLayer().setCell(player.getRobot().getPosition().getX(), player.getRobot().getPosition().getY(), playerCell);
        }

        gameLoop.newTurn();
        gameLoop.startGame();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clears main menu screen

        // Render interface of current player
        gameLoop.getCurrentPlayer().getInterfaceRenderer().renderInterface(board);
        // Render robot movement
        if (robotRenderer.isRequestingRendering()) {
            robotRenderer.renderStep();
        }
        tiledMapRenderer.render();
        camera.update();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
