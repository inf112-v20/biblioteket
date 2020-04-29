package biblioteket.roborally.game;

import biblioteket.roborally.actors.*;
import biblioteket.roborally.board.Board;
import biblioteket.roborally.elements.ArchiveMarkerElement;
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
    private final Board board;
    private final RobotRenderer robotRenderer;
    private final GameLoop gameLoop;
    private final OrthogonalTiledMapRenderer tiledMapRenderer;

    public GameScreen() {
        List<IActor> players = new ArrayList<>();
        String map = MapSelect.getMap();
        this.board = new Board(map, players);
        gameLoop = new GameLoop(board, players);
        this.robotRenderer = new RobotRenderer(board.getPlayerLayer(), players, gameLoop);

        camera.setToOrtho(false, (float) board.getWidth() * 2, (float) board.getHeight());
        camera.update();

        tiledMapRenderer = new OrthogonalTiledMapRenderer(board.getMap(), (float) 1 / board.getTileWidth());
        tiledMapRenderer.setView(camera);


        Texture playerTexture = new Texture("assets/player.png");
        TextureRegion[][] playerTextureSplit = TextureRegion.split(playerTexture, board.getTileWidth(), board.getTileHeight());

        for (int i = 1; i <= 2; i++) {
            TiledMapTileLayer.Cell playerCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(playerTextureSplit[0][0]));
            IActor player = new Player(board, playerCell, new InterfaceRenderer(), robotRenderer);
            players.add(player);
            ArchiveMarkerElement archiveMarker = board.getArchiveMarker(i);
            IRobot robot = new Robot(archiveMarker);
            player.setRobot(robot);
            player.setName("Player " + i);
            board.getPlayerLayer().setCell(player.getRobot().getPosition().getX(), player.getRobot().getPosition().getY(), playerCell);
        }

        for (int i = players.size() + 1; i <= 3; i++) {
            TiledMapTileLayer.Cell playerCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(playerTextureSplit[0][0]));
            IActor player = new EasyAI(board, playerCell, new InterfaceRenderer(), robotRenderer);
            players.add(player);
            ArchiveMarkerElement archiveMarker = board.getArchiveMarker(i);
            IRobot robot = new Robot(archiveMarker);
            player.setRobot(robot);
            player.setName("EasyAI " + i);
            board.getPlayerLayer().setCell(player.getRobot().getPosition().getX(), player.getRobot().getPosition().getY(), playerCell);
        }

        gameLoop.newTurn();
        gameLoop.startGame();

    }

    public static OrthographicCamera getCamera() {
        return camera;
    }

    @Override
    public void show() {
        // Not used, but method must be overwritten
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
        camera.setToOrtho(false, width, height);
        camera.update();
    }


    @Override
    public void pause() {
        // Not used, but method must be overwritten
    }

    @Override
    public void resume() {
        // Not used, but method must be overwritten
    }

    @Override
    public void hide() {
        // Not used, but method must be overwritten
    }

    @Override
    public void dispose() {
        // Not used, but method must be overwritten
    }
}
