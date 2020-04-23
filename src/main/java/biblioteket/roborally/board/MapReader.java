package biblioteket.roborally.board;

import biblioteket.roborally.elements.ArchiveMarkerElement;
import biblioteket.roborally.elements.walls.LaserWallElement;
import biblioteket.roborally.elements.walls.WallElement;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.ArrayList;
import java.util.List;

class MapReader {
    private List<ArchiveMarkerElement> archiveMarkers;
    private List<LaserWallElement> laserWalls;
    private int numFlags = 0;

    public MapReader(IBoard board) {
        archiveMarkers = new ArrayList<>();
        laserWalls = new ArrayList<>();

        readMap(board);
    }

    public int getNumberOfFlags() {
        return numFlags;
    }

    public ArchiveMarkerElement getArchiveMarker(int i) {
        for (ArchiveMarkerElement archiveMarker : archiveMarkers) {
            if (archiveMarker.getArchiveNum() == i) return archiveMarker;
        }
        return null;
    }

    public List<LaserWallElement> getLaserWalls() {
        return laserWalls;
    }

    /**
     * Iterates through each cell of ground and flag layer
     * Counts number of flags and registers all archive markers and laser walls
     */
    private void readMap(IBoard board) {
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                checkForArchiveMarker(x, y, board);
                countFlag(x, y, board);
                checkForLaserWall(x, y, board);
            }
        }
    }


    /**
     * If there is an archive marker in this position, add it to the archiveMarkers list
     *
     * @param x position
     * @param y position
     */
    private void checkForArchiveMarker(int x, int y, IBoard board) {
        TiledMapTileLayer groundLayer = board.getLayer("Ground Layer");
        if (groundLayer.getCell(x, y) != null) {
            int id = groundLayer.getCell(x, y).getTile().getId();
            ArchiveMarkerElement archiveMarker = Element.getArchiveMarker(id, x, y);
            if (archiveMarker != null)
                this.archiveMarkers.add(archiveMarker);
        }
    }

    /**
     * If there is a flag in this position, increase the flag count
     *
     * @param x position
     * @param y position
     */
    private void countFlag(int x, int y, IBoard board) {
        TiledMapTileLayer flagLayer = board.getLayer("Flag Layer");
        if (flagLayer.getCell(x, y) != null) {
            int id = flagLayer.getCell(x, y).getTile().getId();
            if (Element.isFlag(id)) numFlags++;
        }
    }

    /**
     * If there is a laserwall in this position, initialize it, set its position and add it
     * to the laserwalls list
     *
     * @param x position
     * @param y position
     */
    private void checkForLaserWall(int x, int y, IBoard board) {
        TiledMapTileLayer wallLayer = board.getLayer("Wall Layer");
        if (wallLayer.getCell(x, y) != null) {
            int id = wallLayer.getCell(x, y).getTile().getId();
            WallElement wall = Element.getWallElement(id);
            if (wall instanceof LaserWallElement) {
                LaserWallElement laserWall = (LaserWallElement) wall;
                laserWall.setPosition(x, y);
                laserWalls.add(laserWall);
            }

        }
    }

}
