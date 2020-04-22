package biblioteket.roborally.game;

import biblioteket.roborally.actors.*;
import biblioteket.roborally.board.Board;
import biblioteket.roborally.elements.ArchiveMarkerElement;
import biblioteket.roborally.userinterface.InterfaceRenderer;
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
    private static final OrthographicCamera camera = new OrthographicCamera();
    private final List<IPlayer> players;
    private final Board board;
    private final RobotRenderer robotRenderer;
    private final OrthogonalTiledMapRenderer tiledMapRenderer;
    private String map;
    private final RoboRally game;

    public GameScreen(final RoboRally game) {
        this.game = game;
        map = MapSelect.getMap();
        this.board = new Board(map);
        this.robotRenderer = new RobotRenderer(board.getPlayerLayer());

        camera.setToOrtho(false, board.getWidth() * 2, board.getHeight());
        camera.update();

        tiledMapRenderer = new OrthogonalTiledMapRenderer(board.getMap(), (float) 1 / board.getTileWidth());
        tiledMapRenderer.setView(camera);


        Texture playerTexture = new Texture("assets/player.png");
        TextureRegion[][] playerTextureSplit = TextureRegion.split(playerTexture, board.getTileWidth(), board.getTileHeight());

        players = new ArrayList<>();

        for (int i = 1; i <= 1; i++) {
            TiledMapTileLayer.Cell playerCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(playerTextureSplit[0][0]));
            Player player = new Player(board, playerCell, new InterfaceRenderer(), robotRenderer);
            players.add(player);
            ArchiveMarkerElement archiveMarker = board.getArchiveMarker(i);
            IRobot robot = new Robot(archiveMarker);
            player.setRobot(robot);
            board.getPlayerLayer().setCell(player.getRobot().getPosition().getX(), player.getRobot().getPosition().getY(), playerCell);
        }

        GameLoop gameLoop = new GameLoop(board, players);
        gameLoop.startGame();

    }
    public static OrthographicCamera getCamera() {
        return camera;
    }

    @Override
    public void show() {
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clears main menu screen

        for (IPlayer player : players) {
            InterfaceRenderer interfaceRenderer = player.getInterfaceRenderer();
            interfaceRenderer.renderInterface(board);
        }

        // Render robot movement
        if (robotRenderer.isRequestingRendering()) {
            robotRenderer.render();
        }

        tiledMapRenderer.render();
        tiledMapRenderer.getBatch().begin();
        tiledMapRenderer.renderTileLayer(board.getPlayerLayer());
        tiledMapRenderer.getBatch().end();
        camera.update();


    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        camera.update();
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
