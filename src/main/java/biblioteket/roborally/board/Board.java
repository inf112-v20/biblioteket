package biblioteket.roborally.board;

import biblioteket.roborally.actors.IPlayer;
import biblioteket.roborally.actors.IRobot;
import biblioteket.roborally.elements.*;
import biblioteket.roborally.elements.InteractingElements.FlagElement;
import biblioteket.roborally.elements.InteractingElements.InteractingElement;
import biblioteket.roborally.elements.Walls.LaserWallElement;
import biblioteket.roborally.elements.Walls.WallElement;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Board implements IBoard {
    private final TiledMap map;

    private int numFlags;
    private ArrayList<ArchiveMarkerElement> archiveMarkers;
    private ArrayList<LaserWallElement> laserWalls;

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

        numFlags = 0;
        archiveMarkers = new ArrayList<>();
        laserWalls = new ArrayList<>();
        readMap();
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
     * Returns the layer that the lasers are.
     *
     * @return a layer
     */
    public TiledMapTileLayer getLaserLayer() {
        return this.laserLayer;
    }

    /**
     * Returns the layer that the walls are.
     *
     * @return a layer
     */
    public TiledMapTileLayer getWallLayer() {
        return this.wallLayer;
    }

    @Override
    public TiledMapTileLayer getLayer(String layerName) {
        return (TiledMapTileLayer) this.map.getLayers().get(layerName);
    }

    /**
     * Gets an {@link InteractingElement} from the location of a robot, this can
     * be something like a conveyor belt, a rotator or something similar.
     *
     * @param location location to check for elements
     * @return {@link InteractingElement}
     */
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
     *
     * @param location location to check for flags
     * @return {@link FlagElement}
     */
    private FlagElement getFlagElement(DirVector location){
        try {
            int fromId = this.getFlagLayer().getCell(location.getX(), location.getY()).getTile().getId();
            return (FlagElement) Element.getInteractiveElement(fromId);
        } catch (Exception ignored) {}
        return null;
    }

    @Override
    public boolean outOfBounds(DirVector dir) {
        return dir.getX() < 0 || dir.getX() >= getWidth() || dir.getY() < 0 || dir.getY() >= getHeight();
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
        try {
            int fromId = this.getWallLayer().getCell(from.getX(), from.getY()).getTile().getId();
            WallElement wall = Element.getWallElement(fromId);
            if (Objects.requireNonNull(wall).blocking(direction, true)) return true;
        } catch (Exception ignored) {
            // Ignored because getCell() can return null if the layer contains nothing in
            // the given (x, y)-coordinates, we don't care about this as we just want to
            // see if there are elements here.
        }

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
    public DirVector interact(IPlayer player) {
        IRobot robot = player.getRobot();
        InteractingElement element = getInteractingElement(robot.getPosition());
        if (element != null) {
            element.interact(player);
            if (outOfBounds(robot.getPosition())) {
                robot.addDamageTokens(1);
                robot.setPosition(robot.getArchiveMarker());
            }
            return robot.getPosition();
        }
        return null;
    }

    @Override
    public boolean registerFlag(IPlayer player){
        IRobot robot = player.getRobot();
        FlagElement flag = getFlagElement(robot.getPosition());
        if(flag != null){
            flag.interact(player);
        }
        return false;
    }

    /**
     * @return number of flags on map
     */
    public int getNumFlags(){
        return numFlags;
    }


    /**
     * @return an arraylist containing all archive markers
     */
    public ArchiveMarkerElement getArchiveMarker(int i){
        for(ArchiveMarkerElement archiveMarker : archiveMarkers){
            if(archiveMarker.getArchiveNum() == i) return archiveMarker;
        }
        return null;
    }

    public List<LaserWallElement> getLaserWalls(){
        return laserWalls;
    }

    /**
     * Iterates through each cell of ground and flag layer
     * Counts number of flags and registers all archive markers and laser walls
     */
    private void readMap() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                // Check for archive marker
                if (groundLayer.getCell(x,y) != null) {
                    int id = groundLayer.getCell(x, y).getTile().getId();
                    ArchiveMarkerElement archiveMarker = Element.getArchiveMarker(id,x,y);
                    if(archiveMarker != null)
                        this.archiveMarkers.add(archiveMarker);
                }

                // Check for flag
                if (flagLayer.getCell(x,y) != null){
                    int id = flagLayer.getCell(x,y).getTile().getId();
                    if(Element.isFlag(id)) numFlags++;
                }

                // Check for laser
                if(wallLayer.getCell(x,y) != null){
                    int id = wallLayer.getCell(x,y).getTile().getId();
                    WallElement wall = Element.getWallElement(id);
                    if(wall instanceof LaserWallElement){
                        LaserWallElement laserWall = (LaserWallElement) wall;
                        laserWall.setPosition(x,y);
                        laserWalls.add(laserWall);
                    }

                }
            }
        }
    }

}
