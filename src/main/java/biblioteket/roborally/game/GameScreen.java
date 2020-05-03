package biblioteket.roborally.game;

import biblioteket.roborally.actors.*;
import biblioteket.roborally.board.Board;
import biblioteket.roborally.elements.ArchiveMarkerElement;
import com.badlogic.gdx.Gdx;
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
public class GameScreen extends StandardScreen {
    private final OrthographicCamera camera;
    private final RobotRenderer robotRenderer;
    private final GameLoop gameLoop;
    private final OrthogonalTiledMapRenderer tiledMapRenderer;
    private final RoboRally game;

    public GameScreen(final RoboRally game) {
        super(game);
        this.game = game;
        List<IActor> players = new ArrayList<>();
        Board board = new Board(MapSelect.getMap(), players);
        gameLoop = new GameLoop(board, players);
        this.robotRenderer = new RobotRenderer(board.getPlayerLayer(), players, gameLoop);

        camera = getCamera();
        camera.setToOrtho(false, (float) board.getWidth() * 2, (float) board.getHeight());
        camera.update();

        tiledMapRenderer = new OrthogonalTiledMapRenderer(board.getMap(), (float) 1 / board.getTileWidth());
        tiledMapRenderer.setView(camera);


        Texture playerTexture = new Texture("assets/player.png");
        TextureRegion[][] playerTextureSplit = TextureRegion.split(playerTexture, board.getTileWidth(), board.getTileHeight());

        for (int i = 1; i <= game.getPlayers(); i++) {
            TiledMapTileLayer.Cell playerCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(playerTextureSplit[0][0]));
            IActor player = new Player(board, playerCell, new InterfaceRenderer(), robotRenderer);
            players.add(player);
            ArchiveMarkerElement archiveMarker = board.getArchiveMarker(i);
            IRobot robot = new Robot(archiveMarker);
            player.setRobot(robot);
            player.setName("Player " + i);
            board.getPlayerLayer().setCell(player.getRobot().getPosition().getX(), player.getRobot().getPosition().getY(), playerCell);
        }

        for (int i = players.size() + 1; i <= game.getAI(); i++) {
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

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clears main menu screen

        // Render interface of current player
        gameLoop.renderCurrentInterface();

        // Render robot movement
        if (robotRenderer.isRequestingRendering()) {
            robotRenderer.renderStep();
        }
        tiledMapRenderer.render();
        camera.update();

        if (gameLoop.checkWinCondition()) {
            game.setScreen(new EndGameScreen(game, gameLoop.getLivingPlayers().get(0).getName()));
        }
    }
}
