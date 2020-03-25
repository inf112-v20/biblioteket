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
    public final Board board;
    private final GameLoop gameLoop;
    OrthographicCamera camera;
    private List<IPlayer> players;
    private Player currentPlayer;
    private Texture background;
    private Texture cards;
    //private Texture playerOverview;
    private Texture flag;
    private Texture hp;
    private SpriteBatch batch;
    private BitmapFont font;
    public static String map;


    private OrthogonalTiledMapRenderer tiledMapRenderer;

    public GameScreen(final RoboRally gam) {

        this.game = gam;
        this.board = new Board(map);
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, board.getWidth() *2, board.getHeight());
        camera.update();

        batch = new SpriteBatch();
        background = new Texture("assets/background2.jpg");
        cards = new Texture("assets/cards.png");
        //playerOverview = new Texture("assets/playerOverview.jpg");
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

        float leftOfBoardX = camera.viewportWidth/(2) - camera.viewportWidth/654*11;
        float healthFlagSize = 40;


        //Left of board x = 350, top y = 550, width = 290
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clears main menu screen
        batch.begin();
        batch.draw(background, board.getWidth(), 0, camera.viewportWidth, camera.viewportHeight);
        batch.draw(flag, leftOfBoardX, camera.viewportHeight-(camera.viewportHeight/16)*140/100 , healthFlagSize, healthFlagSize);
        batch.draw(hp, leftOfBoardX + healthFlagSize/4*3, camera.viewportHeight-(camera.viewportHeight/16)*140/100, 40, 40);
        font.draw(batch, "Player 1", camera.viewportWidth/2, camera.viewportHeight-(camera.viewportHeight/16)*1/10);
        font.draw(batch, Integer.toString(currentPlayer.getNumberOfVisitedFlags()), camera.viewportWidth/(200/100), camera.viewportHeight-(camera.viewportHeight/16));
        font.draw(batch, Integer.toString(currentPlayer.getLives()), camera.viewportWidth/(299/100), camera.viewportHeight-(camera.viewportHeight/16));

        for(int i = 0; i < 5; i++) {
            batch.draw(cards, (leftOfBoardX + leftOfBoardX/5/4) + leftOfBoardX/5*i, 0, 100, 90);
        }
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

        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, width + 14, height);
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
