package biblioteket.roborally.board;

import biblioteket.roborally.actors.IRobot;
import biblioteket.roborally.elements.IElement;
import biblioteket.roborally.elements.InteractingElement;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class Board implements IBoard {
    private final TiledMap map;

    private final TiledMapTileLayer groundLayer;
    private final TiledMapTileLayer playerLayer;
    private final TiledMapTileLayer flagLayer;
    private final TiledMapTileLayer laserLayer;
    private final TiledMapTileLayer wallLayer;

    private final int width;
    private final int height;
    private final int tileWidth;
    private final int tileHeight;

    public Board(String board) {
        this.map = new TmxMapLoader().load(board);

        MapProperties properties = map.getProperties();
        this.tileWidth = properties.get("tilewidth", Integer.class);
        this.tileHeight = properties.get("tileheight", Integer.class);
        this.width = properties.get("width", Integer.class);
        this.height = properties.get("height", Integer.class);

        this.groundLayer = (TiledMapTileLayer) map.getLayers().get("Ground Layer");
        this.playerLayer = (TiledMapTileLayer) map.getLayers().get("Player Layer");
        this.flagLayer = (TiledMapTileLayer) map.getLayers().get("Flag Layer");
        this.laserLayer = (TiledMapTileLayer) map.getLayers().get("Laser Layer");
        this.wallLayer = (TiledMapTileLayer) map.getLayers().get("Wall Layer");
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
     * @return a layer
     */
    public TiledMapTileLayer getGroundLayer() {
        return this.groundLayer;
    }

    /**
     * Returns the layer that the player objects are.
     * @return a layer
     */
    public TiledMapTileLayer getPlayerLayer() {
        return this.playerLayer;
    }

    /**
     * Returns the layer that the flags are.
     * @return a layer
     */
    public TiledMapTileLayer getFlagLayer() {
        return this.flagLayer;
    }

    /**
     * Returns the layer that the lasers are.
     * @return a layer
     */
    public TiledMapTileLayer getLaserLayer() {
        return this.laserLayer;
    }

    /**
     * Returns the layer that the walls are.
     * @return a layer
     */
    public TiledMapTileLayer getWallLayer() {
        return this.wallLayer;
    }

    @Override
    public TiledMapTileLayer getLayer(String layerName) {
        return (TiledMapTileLayer) this.map.getLayers().get(layerName);
    }

    IElement getInteractingElement(DirVector from, Direction direction) {
        try {
            int fromId = this.getGroundLayer().getCell(from.getX(), from.getY()).getTile().getId();
            if (Element.isInteractive(fromId)) {
                return Element.factory(fromId);
            }
        } catch (Exception ignored) {}

        try {
            int fromId = this.getFlagLayer().getCell(from.getX(), from.getY()).getTile().getId();
            if (Element.isInteractive(fromId)) {
                return Element.factory(fromId);
            }
        } catch (Exception ignored) {}

        return null;
    }

    /**
     * @param x position
     * @param y position
     * @return true if index is out of bounds, false otherwise
     */
    private boolean outOfBounds(int x, int y) {
        return x < 0 || x >= getWidth() || y < 0 || y >= getHeight();
    }

    private boolean outOfBounds(DirVector dir) {
        return dir.getX() < 0 || dir.getX() >= getWidth() || dir.getY() < 0 || dir.getY() >= getHeight();
    }

    @Override
    public boolean containsImmovableObject(int x, int y, Direction direction) {
        return false;
    }

    @Override
    public boolean containsImmovableObject(DirVector currentPosition, Direction direction) {
        return false;
    }

    @Override
    public boolean containsImmovableObject(int x, int y) {
        return false;
    }

    @Override
    public boolean containsImmovableObject(DirVector currentPosition) {
        return false;
    }

    @Override
    public DirVector firstCollisionInDirection(int x, int y, Direction direction) {
        return null;
    }

    @Override
    public DirVector firstCollisionInDirection(DirVector currentPosition, Direction direction) {
        return null;
    }

    @Override
    public boolean canMove(DirVector from, Direction direction) {
        DirVector to = positionInDirection(from, direction);
        if (outOfBounds(to)) return false;
        return !moveBlocked(from, to, direction);
    }

    private boolean moveBlocked(DirVector from, DirVector to, Direction direction) {
        try {
            int fromId = this.getWallLayer().getCell(from.getX(), from.getY()).getTile().getId();
            if (Element.isWall(fromId)) {
                if (Element.factory(fromId).blockingExit(direction)) return true;
            }
        } catch (Exception ignored) {}

        try{
            int toId = this.getWallLayer().getCell(to.getX(), to.getY()).getTile().getId();
            if (Element.isWall(toId)) {
                return Element.factory(toId).blockingEntry(direction);
            }
        } catch (Exception ignored) {}
        return false;
    }

    private DirVector positionInDirection(DirVector from, Direction direction) {
        return from.dirVectorInDirection(direction);
    }

    @Override
    public boolean canMove(int x, int y, Direction direction) {
        return canMove(new DirVector(x, y, direction), direction);
    }

    @Override
    public DirVector interact(IRobot robot) {
        IElement element = getInteractingElement(robot.getPosition(), robot.getDirection());
        if (element instanceof InteractingElement) {
            ((InteractingElement) element).interact(robot);
            return robot.getPosition();
        }
        return null;
    }
}
