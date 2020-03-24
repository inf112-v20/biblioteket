package biblioteket.roborally.game;

import biblioteket.roborally.actors.IPlayer;
import biblioteket.roborally.actors.IRobot;
import biblioteket.roborally.actors.Player;
import biblioteket.roborally.actors.Robot;
import biblioteket.roborally.board.Board;
import biblioteket.roborally.board.DirVector;
import biblioteket.roborally.board.Direction;
import biblioteket.roborally.elements.ArchiveMarkerElement;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    private final RoboRally game;
    private final Board board;
    private final GameLoop gameLoop;
    OrthographicCamera camera;
    private List<IPlayer> players;
    private Player currentPlayer;
    private Texture background;
    private Texture cards;
    private Texture playerOverview;
    private Texture flag;
    private Texture hp;
    private SpriteBatch batch;
    private BitmapFont font;


    private OrthogonalTiledMapRenderer tiledMapRenderer;

    public GameScreen(final RoboRally gam) {
        this.game = gam;
        this.board = new Board("assets/risky_exchange.tmx");
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, board.getWidth() + 14, board.getHeight() + 1);
        camera.update();

        batch = new SpriteBatch();
        background = new Texture("assets/background2.jpg");
        cards = new Texture("assets/cards.png");
        playerOverview = new Texture("assets/playerOverview.jpg");
        hp = new Texture("assets/hp.png");
        flag = new Texture("assets/flag.png");
        font = new BitmapFont();


        Texture playerTexture = new Texture("assets/player.png");
        TextureRegion[][] playerTextureSplit = TextureRegion.split(playerTexture, board.getTileWidth(), board.getTileHeight());

        this.players = new ArrayList<>();

        for (int i = 1; i <= 1; i++) {
            Player player = new Player(new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(playerTextureSplit[0][0])));
            currentPlayer = player;
            players.add(player);
            ArchiveMarkerElement archiveMarker = board.getArchiveMarker(i + 1);   // Archive markers start at 1
            IRobot robot = new Robot(archiveMarker);
            player.setRobot(robot);
            board.getPlayerLayer().setCell(player.getRobot().getPosition().getX(), player.getRobot().getPosition().getY(), null);
        }

        this.gameLoop = new GameLoop(board, players);

        tiledMapRenderer = new OrthogonalTiledMapRenderer(board.getMap(), (float) 1 / board.getTileWidth());
        tiledMapRenderer.setView(camera);

        // For ease of use and iterating we define the input processor inline
        // in the code here, in the future this will be moved to a separate
        // InputMultiplexer.
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyUp(int keycode) {
                switch (keycode) {
                    case Input.Keys.A:
                        return currentPlayer.getRobot().move(Direction.WEST, board);
                    case Input.Keys.D:
                        return currentPlayer.getRobot().move(Direction.EAST, board);
                    case Input.Keys.W:
                        return currentPlayer.getRobot().move(Direction.NORTH, board);
                    case Input.Keys.S:
                        return currentPlayer.getRobot().move(Direction.SOUTH, board);
                    case Input.Keys.SPACE:
                        DirVector newPosition = board.interact(currentPlayer);
                        return newPosition != null;
                    case Input.Keys.P:
                        return board.registerFlag(currentPlayer);
                    case Input.Keys.ENTER:
                        gameLoop.doTurn();
                        return false;
                    default:
                        return true;
                }
            }
        });
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        for (IPlayer player : players) {
            board.getPlayerLayer().setCell(player.getRobot().getPosition().getX(), player.getRobot().getPosition().getY(), player.getPlayerCell());
        }

        //Left of board x = 350, top y = 550, width = 290
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clears main menu screen
        batch.begin();
        batch.draw(playerOverview, 0, Gdx.graphics.getHeight() - 90, Gdx.graphics.getWidth(), 90);
        batch.draw(background, board.getTileWidth(), 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(flag, 290, Gdx.graphics.getHeight() - 180, 40, 40);
        batch.draw(hp, 330, Gdx.graphics.getHeight() - 180, 40, 40);
        font.draw(batch, "Player 1", 300, Gdx.graphics.getHeight() - 120);
        font.draw(batch, "1", 310, Gdx.graphics.getHeight() - 165);
        font.draw(batch, "3", 360, Gdx.graphics.getHeight() - 165);

        batch.draw(cards, 350, 0, 100, 90);
        batch.draw(cards, 400, 0, 100, 90);
        batch.draw(cards, 450, 0, 100, 90);
        batch.draw(cards, 500, 0, 100, 90);
        batch.draw(cards, 550, 0, 100, 90);
        batch.end();
        tiledMapRenderer.render();
        tiledMapRenderer.getBatch().begin();
        tiledMapRenderer.renderTileLayer(board.getPlayerLayer());
        tiledMapRenderer.getBatch().end();

        for (IPlayer player : players) {
            board.getPlayerLayer().setCell(player.getRobot().getPosition().getX(), player.getRobot().getPosition().getY(), null);
        }
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
