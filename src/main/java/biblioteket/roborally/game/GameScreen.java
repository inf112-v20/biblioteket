package biblioteket.roborally.game;

import biblioteket.roborally.board.Board;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;

/**
 * The viewport for the game, allows us to implement the UI separately from
 * the game logic itself. Currently only renders a very simple board with a
 * flag and hole that they player can move around on.
 */
public class GameScreen implements Screen {
    private final RoboRally game;
    private final Board board;

    private OrthogonalTiledMapRenderer tiledMapRenderer;

    private TiledMapTileLayer.Cell playerCell;
    private TiledMapTileLayer.Cell playerDiedCell;
    private TiledMapTileLayer.Cell playerWonCell;

    private Vector2 playerPosition;

    public GameScreen(final RoboRally gam) {
        this.game = gam;
        this.board = new Board("assets/risky_exchange.tmx");

        Texture playerTexture = new Texture("assets/player.png");
        TextureRegion[][] playerTextureSplit = TextureRegion.split(playerTexture, board.getTileWidth(), board.getTileHeight());

        playerCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(playerTextureSplit[0][0]));
        playerDiedCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(playerTextureSplit[0][1]));
        playerWonCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(playerTextureSplit[0][2]));

        playerPosition = new Vector2(0, 0);

        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, board.getWidth(), board.getHeight());
        camera.update();

        tiledMapRenderer = new OrthogonalTiledMapRenderer(board.getMap(), (float) 1 / board.getTileWidth());
        tiledMapRenderer.setView(camera);

        // For ease of use and iterating we define the input processor inline
        // in the code here, in the future this will be moved to a separate
        // InputMultiplexer.
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyUp(int keycode) {

                board.getPlayerLayer().setCell((int) playerPosition.x, (int) playerPosition.y, null);

                float playerPosX = playerPosition.x;
                float playerPosY = playerPosition.y;

                switch (keycode) {
                    case Input.Keys.A:
                        if (playerPosX - 1.0 < 0 || playerPosY - 1.0 >= board.getWidth()) return false;
                        else {
                            playerPosition.set(new Vector2(playerPosX - 1, playerPosY));
                            return true;
                        }
                    case Input.Keys.D:
                        if (playerPosX + 1 < 0 || playerPosX + 1 >= board.getWidth()) return false;
                        else {
                            playerPosition.set(new Vector2(playerPosX + 1, playerPosY));
                            return true;
                        }
                    case Input.Keys.W:
                        if (playerPosY + 1 < 0 || playerPosY + 1 >= board.getHeight()) return false;
                        else {
                            playerPosition.set(new Vector2(playerPosX, playerPosY + 1));
                            return true;
                        }
                    case Input.Keys.S:
                        if (playerPosY - 1 < 0 || playerPosY - 1 >= board.getHeight()) return false;
                        else {
                            playerPosition.set(new Vector2(playerPosX, playerPosY - 1));
                            return true;
                        }
                    default:
                        return false;
                }
            }
        });
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        board.getPlayerLayer().setCell((int) playerPosition.x, (int) playerPosition.y, playerCell);

        if (board.getGroundLayer().getCell((int) playerPosition.x, (int) playerPosition.y) != null)
            board.getPlayerLayer().setCell((int) playerPosition.x, (int) playerPosition.y, playerDiedCell);
        else if (board.getFlagLayer().getCell((int) playerPosition.x, (int) playerPosition.y) != null)
            board.getPlayerLayer().setCell((int) playerPosition.x, (int) playerPosition.y, playerWonCell);
        else
            board.getPlayerLayer().setCell((int) playerPosition.x, (int) playerPosition.y, playerCell);

        tiledMapRenderer.render();
        tiledMapRenderer.getBatch().begin();
        tiledMapRenderer.renderTileLayer(board.getPlayerLayer());
        tiledMapRenderer.getBatch().end();
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
