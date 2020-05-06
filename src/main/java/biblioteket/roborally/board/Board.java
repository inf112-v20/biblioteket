package biblioteket.roborally.board;

import biblioteket.roborally.actors.IActor;
import biblioteket.roborally.actors.IRobot;
import biblioteket.roborally.elements.ArchiveMarkerElement;
import biblioteket.roborally.elements.interacting.FlagElement;
import biblioteket.roborally.elements.interacting.HoleElement;
import biblioteket.roborally.elements.interacting.InteractingElement;
import biblioteket.roborally.elements.walls.LaserWallElement;
import biblioteket.roborally.elements.walls.WallElement;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import java.util.List;
import java.util.Objects;

public class Board implements IBoard {
    private final TiledMap map;
    private final TiledMapTileLayer groundLayer;
    private final TiledMapTileLayer playerLayer;
    private final TiledMapTileLayer flagLayer;
    private final TiledMapTileLayer wallLayer;
    private final int width;
    private final int height;
    private final int tileWidth;
    private final int tileHeight;
    private final List<IActor> players;
    private final MapReader mapReader;

    public Board(String board, List<IActor> players) {
        this.map = new TmxMapLoader().load(board);

        MapProperties properties = map.getProperties();
        this.tileWidth = properties.get("tilewidth", Integer.class);
        this.tileHeight = properties.get("tileheight", Integer.class);
        this.width = properties.get("width", Integer.class);
        this.height = properties.get("height", Integer.class);

        this.groundLayer = (TiledMapTileLayer) map.getLayers().get("Ground Layer");
        this.playerLayer = (TiledMapTileLayer) map.getLayers().get("Player Layer");
        this.flagLayer = (TiledMapTileLayer) map.getLayers().get("Flag Layer");
        this.wallLayer = (TiledMapTileLayer) map.getLayers().get("Wall Layer");

        this.players = players;
        mapReader = new MapReader(this);
    }

    @Override
    public TiledMap getMap() {
        return this.map;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public int getTileWidth() {
        return this.tileWidth;
    }

    @Override
    public int getTileHeight() {
        return this.tileHeight;
    }

    /**
     * Return the layer containing all the ground tiles, i.e. just floor, holes, spawn points etc.
     *
     * @return a layer
     */
    public TiledMapTileLayer getGroundLayer() {
        return this.groundLayer;
    }

    /**
     * Returns the layer that the player objects are.
     *
     * @return a layer
     */
    @Override
    public TiledMapTileLayer getPlayerLayer() {
        return this.playerLayer;
    }

    /**
     * Returns the layer that the flags are.
     *
     * @return a layer
     */
    public TiledMapTileLayer getFlagLayer() {
        return this.flagLayer;
    }

    /**
     * Returns the layer that the walls are.
     *
     * @return a layer
     */
    private TiledMapTileLayer getWallLayer() {
        return this.wallLayer;
    }

    @Override
    public TiledMapTileLayer getLayer(String layerName) {
        return (TiledMapTileLayer) this.map.getLayers().get(layerName);
    }

    @Override
    public InteractingElement getInteractingElement(DirVector location) {
        try {
            int fromId = this.getGroundLayer().getCell(location.getX(), location.getY()).getTile().getId();
            return Element.getInteractiveElement(fromId);
        } catch (Exception ignored) {
            // Ignored because getCell() can return null if the layer contains nothing in
            // the given (x, y)-coordinates, we don't care about this as we just want to
            // see if there are elements here.
        }
        return null;
    }

    /**
     * Gets a {@link FlagElement} from the location of a robot
     * Used instead of getInteractingElement because flags are on a separate layer
     *
     * @param location location to check for flags
     * @return {@link FlagElement}
     */
    private FlagElement getFlagElement(DirVector location) {
        try {
            int fromId = this.getFlagLayer().getCell(location.getX(), location.getY()).getTile().getId();
            return (FlagElement) Element.getInteractiveElement(fromId);
        } catch (Exception ignored) {
            // Ignored because getCell() can return null if the layer contains nothing in
        }
        return null;
    }

    @Override
    public boolean outOfBounds(DirVector dir) {
        return dir.getX() < 0 || dir.getX() >= getWidth() || dir.getY() < 0 || dir.getY() >= getHeight();
    }

    @Override
    public boolean isHole(DirVector position) {
        InteractingElement element = getInteractingElement(position);
        return element instanceof HoleElement;
    }

    @Override
    public boolean pushRobot(DirVector position, Direction direction) {
        DirVector positionInDirection = positionInDirection(position, direction);
        for (IActor player : players) {
            if (!player.isPermanentDead() && player.getRobot().getPosition().compareVector(positionInDirection))
                return player.moveRobot(direction, 500, false, true);
        }
        return true;
    }

    @Override
    public boolean canMove(DirVector position, Direction direction) {
        DirVector to = positionInDirection(position, direction);
        return !moveBlocked(position, to, direction);
    }

    /**
     * Checks if a robot is blocked when it attempts to move between two cells
     * in a given direction.
     *
     * @param from      location robot is moving from
     * @param to        location robot is moving to
     * @param direction direction robot is facing
     * @return true if move is blocked, false otherwise
     */
    private boolean moveBlocked(DirVector from, DirVector to, Direction direction) {
        // Check if cell player moving from is blocked
        try {
            int fromId = this.getWallLayer().getCell(from.getX(), from.getY()).getTile().getId();
            WallElement wall = Element.getWallElement(fromId);
            if (Objects.requireNonNull(wall).blocking(direction, true)) return true;
        } catch (Exception ignored) {
            // Ignored because getCell() can return null if the layer contains nothing in
            // the given (x, y)-coordinates, we don't care about this as we just want to
            // see if there are elements here.
        }

        // Check if cell player is moving to is blocked
        try {
            int toId = this.getWallLayer().getCell(to.getX(), to.getY()).getTile().getId();
            WallElement wall = Element.getWallElement(toId);
            if (Objects.requireNonNull(wall).blocking(direction, false)) return true;
        } catch (Exception ignored) {
            // See above.
        }
        return false;
    }

    /**
     * Returns a new {@link DirVector} in a given direction.
     *
     * @param from      location to move from
     * @param direction direction to move
     * @return new location in direction
     */
    private DirVector positionInDirection(DirVector from, Direction direction) {
        return from.dirVectorInDirection(direction);
    }


    @Override
    public void interact(IActor player) {
        InteractingElement element = getInteractingElement(player.getRobot().getPosition());
        if (element != null) {
            element.interact(player);
        }
    }

    @Override
    public void registerFlag(IActor player) {
        IRobot robot = player.getRobot();
        FlagElement flag = getFlagElement(robot.getPosition());
        if (flag != null) {
            flag.interact(player);
        }
    }

    @Override
    public int getNumberOfFlags() {
        return mapReader.getNumberOfFlags();
    }


    @Override
    public ArchiveMarkerElement getArchiveMarker(int i) {
        return mapReader.getArchiveMarker(i);
    }

    @Override
    public List<LaserWallElement> getLaserWalls() {
        return mapReader.getLaserWalls();
    }


}
