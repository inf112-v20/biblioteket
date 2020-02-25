package biblioteket.roborally.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
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

    private TiledMapTileLayer playerLayer;
    private TiledMapTileLayer holeLayer;
    private TiledMapTileLayer flagLayer;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private MapProperties properties;

    private TiledMapTileLayer.Cell playerCell;
    private TiledMapTileLayer.Cell playerDiedCell;
    private TiledMapTileLayer.Cell playerWonCell;

    private Vector2 playerPosition;

    public GameScreen(final RoboRally gam) {
        this.game = gam;

        TiledMap tiledMap = new TmxMapLoader().load("assets/board.tmx");

        properties = tiledMap.getProperties();
        int tileWidth = properties.get("tilewidth", Integer.class);
        int tileHeight = properties.get("tileheight", Integer.class);
        int mapWidth = properties.get("width", Integer.class);
        int mapHeight = properties.get("height", Integer.class);

        playerLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Player");
        holeLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Hole");
        flagLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Flag");

        Texture playerTexture = new Texture("assets/player.png");
        TextureRegion[][] playerTextureSplit = TextureRegion.split(playerTexture, tileWidth, tileHeight);

        playerCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(playerTextureSplit[0][0]));
        playerDiedCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(playerTextureSplit[0][1]));
        playerWonCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(playerTextureSplit[0][2]));

        playerPosition = new Vector2(0, 0);

        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, mapWidth, mapHeight);
        camera.update();

        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, (float) 1 / tileWidth);
        tiledMapRenderer.setView(camera);

        // For ease of use and iterating we define the input processor inline
        // in the code here, in the future this will be moved to a separate
        // InputMultiplexer.
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyUp(int keycode) {

                playerLayer.setCell((int) playerPosition.x, (int) playerPosition.y, null);

                float playerPosX = playerPosition.x;
                float playerPosY = playerPosition.y;
                int width = properties.get("width", Integer.class);
                int height = properties.get("height", Integer.class);

                switch (keycode) {
                    case Input.Keys.A:
                        if (playerPosX - 1.0 < 0 || playerPosY - 1.0 >= width) return false;
                        else {
                            playerPosition.set(new Vector2(playerPosX - 1, playerPosY));
                            return true;
                        }
                    case Input.Keys.D:
                        if (playerPosX + 1 < 0 || playerPosX + 1 >= width) return false;
                        else {
                            playerPosition.set(new Vector2(playerPosX + 1, playerPosY));
                            return true;
                        }
                    case Input.Keys.W:
                        if (playerPosY + 1 < 0 || playerPosY + 1 >= height) return false;
                        else {
                            playerPosition.set(new Vector2(playerPosX, playerPosY + 1));
                            return true;
                        }
                    case Input.Keys.S:
                        if (playerPosY - 1 < 0 || playerPosY - 1 >= height) return false;
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
        playerLayer.setCell((int) playerPosition.x, (int) playerPosition.y, playerCell);

        if (holeLayer.getCell((int) playerPosition.x, (int) playerPosition.y) != null)
            playerLayer.setCell((int) playerPosition.x, (int) playerPosition.y, playerDiedCell);
        else if (flagLayer.getCell((int) playerPosition.x, (int) playerPosition.y) != null)
            playerLayer.setCell((int) playerPosition.x, (int) playerPosition.y, playerWonCell);
        else
            playerLayer.setCell((int) playerPosition.x, (int) playerPosition.y, playerCell);

        tiledMapRenderer.render();
        tiledMapRenderer.getBatch().begin();
        tiledMapRenderer.renderTileLayer(playerLayer);
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
