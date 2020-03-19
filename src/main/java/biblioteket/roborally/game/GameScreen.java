package biblioteket.roborally.game;

import biblioteket.roborally.actors.Player;
import biblioteket.roborally.actors.Robot;
import biblioteket.roborally.board.Board;
import biblioteket.roborally.board.DirVector;
import biblioteket.roborally.board.Direction;
import biblioteket.roborally.board.Element;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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

    private List<Player> players;
    private Player currentPlayer;

    private Texture background;
    private Texture cards;
    private Texture logo;
    private SpriteBatch batch;

    private OrthogonalTiledMapRenderer tiledMapRenderer;

    public GameScreen(final RoboRally gam) {
        this.game = gam;
        this.board = new Board("assets/risky_exchange.tmx");

        batch = new SpriteBatch();
        background = new Texture("assets/background2.jpg");
        cards = new Texture("assets/cards.png");
        logo = new Texture("assets/roborally.jpg");

        Texture playerTexture = new Texture("assets/player.png");
        TextureRegion[][] playerTextureSplit = TextureRegion.split(playerTexture, board.getTileWidth(), board.getTileHeight());

        this.players = new ArrayList<>();

        for (int i = 0; i < 1; i++) {
            Player player = new Player(new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(playerTextureSplit[0][0])));
            currentPlayer = player;
            players.add(player);
            for (int y = 0; y < board.getHeight(); y++) {
                for (int x = 0; x < board.getWidth(); x++) {
                    if (Element.valueOf(board.getGroundLayer().getCell(x, y).getTile().getId()) == Element.SPAWN_1) {
                        Robot robot = new Robot(new DirVector(x, y, Direction.NORTH));
                        player.setRobot(robot);
                        board.getPlayerLayer().setCell(player.getRobot().getPosition().getX(), player.getRobot().getPosition().getY(), null);
                    }
                }
            }
        }

        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, board.getWidth() + 14, board.getHeight());
        camera.update();

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
                        DirVector newPosition = board.interact(currentPlayer.getRobot());
                        return newPosition != null;
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
        board.getPlayerLayer().setCell(currentPlayer.getRobot().getPosition().getX(), currentPlayer.getRobot().getPosition().getY(), currentPlayer.getPlayerCell());

        //Left of board x = 350, top y = 550, width = 290
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clears main menu screen
        batch.begin();
        batch.draw(background, 0, 0, 700, 700);
        batch.draw(cards, 350, 0, 100, 90);
        batch.draw(cards, 400, 0, 100, 90);
        batch.draw(cards, 450, 0, 100, 90);
        batch.draw(cards, 500, 0, 100, 90);
        batch.draw(cards, 550, 0, 100, 90);
        //batch.draw(logo, 350, 300,290,90);
        batch.end();
        tiledMapRenderer.render();
        tiledMapRenderer.getBatch().begin();
        tiledMapRenderer.renderTileLayer(board.getPlayerLayer());
        tiledMapRenderer.getBatch().end();

        board.getPlayerLayer().setCell(currentPlayer.getRobot().getPosition().getX(), currentPlayer.getRobot().getPosition().getY(), null);
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
