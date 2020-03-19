package biblioteket.roborally.elements;

import biblioteket.roborally.actors.IPlayer;
import biblioteket.roborally.board.Board;
import biblioteket.roborally.board.DirVector;
import biblioteket.roborally.board.Direction;

import java.util.List;

public class LaserWallElement extends WallElement {
    private DirVector position;

    public LaserWallElement(Direction xDirection, Direction yDirection) {
        super(xDirection, yDirection);
    }

    /**
     * @return the direction of the laser
     */
    private Direction getLaserDirection(){
        return xDirection == null ? yDirection.opposite() : xDirection.opposite();
    }

    public void setPosition(int x, int y){
        DirVector position = new DirVector(x,y,getLaserDirection());
        this.position = position;
    }



    /**
     * Fire lasers
     * Laser moves until it hits a robot, a wall or moves off the board
     */
    public void interact(Board board, List<IPlayer> players){
        DirVector vector = new DirVector(position.getX(), position.getY(), position.getDirection());

        Laser laser = new Laser();
        laser.fireLaser(board,players, vector);
    }

    @Override
    public String toString() {
        return "Laserwall " + xDirection + ", " + yDirection;
    }

}
