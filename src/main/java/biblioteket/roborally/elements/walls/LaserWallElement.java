package biblioteket.roborally.elements.walls;

import biblioteket.roborally.actors.IPlayer;
import biblioteket.roborally.board.DirVector;
import biblioteket.roborally.board.Direction;
import biblioteket.roborally.board.IBoard;

import java.util.List;

public class LaserWallElement extends WallElement {
    private DirVector position;

    public LaserWallElement(Direction xDirection, Direction yDirection) {
        super(xDirection, yDirection);
    }

    /**
     * @return the direction of the laser
     */
    private Direction getLaserDirection() {
        return xDirection == null ? yDirection.opposite() : xDirection.opposite();
    }

    public void setPosition(int x, int y) {
        position = new DirVector(x, y, getLaserDirection());
    }

    /**
     * Fire lasers
     * Laser moves until it hits a robot, a wall or moves off the board
     */
    public void interact(IBoard board, List<IPlayer> players) {
        // If a position is not set for the laserwall, it can not call the interact method
        if (position == null) {
            throw new NullPointerException();
        }

        DirVector vector = position.copy();
        Laser laser = new Laser();
        laser.fireLaser(board, players, vector);
    }

    @Override
    public String toString() {
        return "Laserwall " + xDirection + ", " + yDirection;
    }

}
