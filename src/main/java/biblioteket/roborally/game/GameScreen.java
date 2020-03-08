package biblioteket.roborally.game;

import biblioteket.roborally.actors.SkellyRobot;
import biblioteket.roborally.elements.IElement;
import biblioteket.roborally.grid.Direction;
import biblioteket.roborally.grid.GameBoard;
import biblioteket.roborally.grid.Grid;
import biblioteket.roborally.grid.IPosition;
import biblioteket.roborally.mapreader.MapReader;
import com.badlogic.gdx.*;
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
    private final GameBoard gameBoard;

    private TiledMapTileLayer playerLayer;
    private TiledMapTileLayer holeLayer;
    private TiledMapTileLayer flagLayer;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private MapProperties properties;

    private TiledMapTileLayer.Cell playerCell;
    private TiledMapTileLayer.Cell playerDiedCell;
    private TiledMapTileLayer.Cell playerWonCell;

    private Vector2 playerPosition;
    private SkellyRobot robot;

    public GameScreen(final RoboRally gam) {
        this.game = gam;
//        TiledMap tiledMap = new TmxMapLoader().load("assets/dockingbay.tmx");
        TiledMap tiledMap = new TmxMapLoader().load("assets/board_12x12.tmx");

        this.gameBoard = MapReader.readMap(tiledMap);

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
        robot = new SkellyRobot(0,mapHeight-1,gameBoard.getPosition(0,mapHeight-1), gameBoard);

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

                switch (keycode) {
                    case Input.Keys.A:
                        if(gameBoard.canMove(robot.getX(),robot.getY(), Direction.WEST)) {
                            playerPosition.set(new Vector2(playerPosX - 1, playerPosY));
                            robot.move(Direction.WEST);
                            return true;
                        }
                        else {
                            return false;
                        }
                    case Input.Keys.D:
                        if(gameBoard.canMove(robot.getX(),robot.getY(), Direction.EAST)) {
                            playerPosition.set(new Vector2(playerPosX + 1, playerPosY));
                            robot.move(Direction.EAST);
                            return true;
                        } else {
                            return false;
                        }
                    case Input.Keys.W:
                        if(gameBoard.canMove(robot.getX(), robot.getY(), Direction.NORTH)) {
                            playerPosition.set(new Vector2(playerPosX, playerPosY + 1));
                            robot.move(Direction.NORTH);
                            return true;
                        } else{
                            return false;
                        }
                    case Input.Keys.S:
                        if(gameBoard.canMove(robot.getX(),robot.getY(), Direction.SOUTH)) {
                            playerPosition.set(new Vector2(playerPosX, playerPosY - 1));
                            robot.move(Direction.SOUTH);
                            return true;
                        } else {
                            return false;
                        }
                    case Input.Keys.SPACE:
                        boolean interacted = gameBoard.interact(robot);
                        if(interacted){
                            playerPosition.set(new Vector2((float)robot.getX(),(float)(mapHeight - 1 - robot.getY())));
                        } return interacted;
                    default:
                        playerPosition.set(new Vector2(playerPosX, playerPosY));
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
