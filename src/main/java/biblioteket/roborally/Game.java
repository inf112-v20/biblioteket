package biblioteket.roborally;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;

public class Game extends InputAdapter implements ApplicationListener {
    private TiledMapTileLayer playerLayer;
    private TiledMapTileLayer holeLayer;
    private TiledMapTileLayer flagLayer;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private MapProperties properties;

    private Cell playerCell;
    private Cell playerDiedCell;
    private Cell playerWonCell;
    private Vector2 playerPosition;


    @Override
    public void create() {

        TiledMap tiledMap = new TmxMapLoader().load("assets/board.tmx");

        properties = tiledMap.getProperties();
        int tileWidth = properties.get("tilewidth", Integer.class);
        int tileHeight = properties.get("tileheight", Integer.class);
        int mapWidth = properties.get("width", Integer.class);
        int mapHeight = properties.get("height", Integer.class);

        TiledMapTileLayer boardLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Board");
        playerLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Player");
        holeLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Hole");
        flagLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Flag");

        Texture playerTexture = new Texture("assets/player.png");
        TextureRegion[][] playerTextureSplit = TextureRegion.split(playerTexture, tileWidth, tileHeight);

        playerCell = new Cell().setTile(new StaticTiledMapTile(playerTextureSplit[0][0]));
        playerDiedCell = new Cell().setTile(new StaticTiledMapTile(playerTextureSplit[0][1]));
        playerWonCell = new Cell().setTile(new StaticTiledMapTile(playerTextureSplit[0][2]));

        playerPosition = new Vector2(0, 0);

        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, mapWidth, mapHeight);
        camera.update();

        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, (float) 1 / tileWidth);
        tiledMapRenderer.setView(camera);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void dispose() {

    }

    @Override
    public void render() {
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
    public boolean keyUp(int keyCode) {
        playerLayer.setCell((int) playerPosition.x, (int) playerPosition.y, null);

        int playerPosX = (int) playerPosition.x;
        int playerPosY = (int) playerPosition.y;
        int width = properties.get("width", Integer.class);
        int height = properties.get("height", Integer.class);

        switch (keyCode) {
            case Input.Keys.A:
                if (playerPosX - 1 < 0 || playerPosX - 1 >= width) return false;
                else {
                    playerPosition.set(playerPosX - 1, playerPosY);
                    return true;
                }
            case Input.Keys.D:
                if (playerPosX + 1 < 0 || playerPosX + 1 >= width) return false;
                else {
                    playerPosition.set(playerPosX + 1, playerPosY);
                    return true;
                }
            case Input.Keys.W:
                if (playerPosY + 1 < 0 || playerPosY + 1 >= height) return false;
                else {
                    playerPosition.set(playerPosX, playerPosY + 1);
                    return true;
                }
            case Input.Keys.S:
                if (playerPosY - 1 < 0 || playerPosY - 1 >= height) return false;
                else {
                    playerPosition.set(playerPosX, playerPosY - 1);
                    return true;
                }
            default:
                return false;
        }
    }
}
