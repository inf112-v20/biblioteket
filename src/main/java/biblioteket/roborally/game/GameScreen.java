package biblioteket.roborally.game;

import biblioteket.roborally.actors.*;
import biblioteket.roborally.board.Board;
import biblioteket.roborally.board.IBoard;
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
import java.util.Random;

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
        IBoard board = new Board(MapSelect.getMap(), players);
        gameLoop = new GameLoop(board, players);
        this.robotRenderer = new RobotRenderer(board.getPlayerLayer(), players, gameLoop);

        camera = getCamera();
        camera.setToOrtho(false, (float) board.getWidth() * 2, (float) board.getHeight());
        camera.update();

        tiledMapRenderer = new OrthogonalTiledMapRenderer(board.getMap(), (float) 1 / board.getTileWidth());
        tiledMapRenderer.setView(camera);

        List<Texture> playerTextures = playerTextures();

        for (int i = 0; i < game.getPlayers(); i++) {
            TextureRegion[][] texture = splitTexture(pickRandomTexture(playerTextures), board);
            TiledMapTileLayer.Cell playerCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(texture[0][0]));
            IActor player = new Player(board, playerCell, new InterfaceRenderer(), robotRenderer);
            players.add(player);
            ArchiveMarkerElement archiveMarker = board.getArchiveMarker(i + 1);
            IRobot robot = new Robot(archiveMarker);
            player.setRobot(robot);
            player.setName("Player " + (i + 1));
            board.getPlayerLayer().setCell(player.getRobot().getPosition().getX(), player.getRobot().getPosition().getY(), playerCell);
        }

        for (int i = players.size(); i < game.getAI() + game.getPlayers(); i++) {
            TextureRegion[][] texture = splitTexture(pickRandomTexture(playerTextures), board);
            TiledMapTileLayer.Cell playerCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(texture[0][0]));
            IActor player = new EasyAI(board, playerCell, new InterfaceRenderer(), robotRenderer);
            players.add(player);
            ArchiveMarkerElement archiveMarker = board.getArchiveMarker(i + 1);
            IRobot robot = new Robot(archiveMarker);
            player.setRobot(robot);
            player.setName("EasyAI " + (i + 1));
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

    /**
     * Picks a random element from a list, removes it and returns it. Used so
     * that each player/AI has a unique skin for their robot.
     *
     * @param textures A list of textures pick from
     * @return a new texture
     */
    private Texture pickRandomTexture(List<Texture> textures) {
        int element = new Random().nextInt(textures.size());
        return textures.remove(element);
    }

    /**
     * Splits a texture so that we can use the individual slices of the texture.
     *
     * @param texture Texture to split
     * @param board   Current game board
     * @return The split texture
     */
    private TextureRegion[][] splitTexture(Texture texture, IBoard board) {
        return TextureRegion.split(texture, board.getTileWidth(), board.getTileHeight());
    }

    /**
     * @return a list of all available player textures
     */
    private List<Texture> playerTextures() {
        Assets assets = getAssets();
        List<Texture> textures = new ArrayList<>();
        textures.add(assets.getManager().get(Assets.BARREL_BOT, Texture.class));
        textures.add(assets.getManager().get(Assets.BOX_BOT, Texture.class));
        textures.add(assets.getManager().get(Assets.HAMMER_BOT, Texture.class));
        textures.add(assets.getManager().get(Assets.PIN_BOT, Texture.class));
        textures.add(assets.getManager().get(Assets.SAFE_BOT, Texture.class));
        textures.add(assets.getManager().get(Assets.SAUCER_BOT, Texture.class));
        textures.add(assets.getManager().get(Assets.SPIN_BOT, Texture.class));
        textures.add(assets.getManager().get(Assets.OWL_BOT, Texture.class));

        return textures;
    }
}
