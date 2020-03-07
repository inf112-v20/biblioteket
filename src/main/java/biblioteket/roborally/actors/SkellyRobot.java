package biblioteket.roborally.actors;

import biblioteket.roborally.elements.IElement;
import biblioteket.roborally.grid.Direction;
import biblioteket.roborally.grid.GameBoard;
import biblioteket.roborally.grid.IPosition;
import biblioteket.roborally.grid.Position;

/**
 * Temporary because didnt want to mess around with the robots
 * positions are a bit messy since robots exists a place between gameboard and map, which uses different x,y coordinates
 * When interacting with a robot from game, need to consider y in game is mapHeight - 1 - y in grid
 */
public class SkellyRobot implements IRobot<Object>{

    private final GameBoard gameBoard;
    private final IPosition<IElement> spawnPoint;
    private int x;
    private int y;
    private Direction direction;

    public SkellyRobot(int x, int y, IPosition<IElement> spawnPoint, GameBoard gameBoard){
        this.gameBoard = gameBoard;
        this.spawnPoint = spawnPoint;
        this.x = x;
        this.y = y;
        this.direction = Direction.NORTH;
    }

    public IPosition<IElement> getPosition(){
        return gameBoard.getPosition(x,y);
    }

    public boolean moveForward(){
        if(gameBoard.canMove(getPosition(), getDirection())){
            Position<IElement> positionInDirection = (Position<IElement>) gameBoard.positionInDirection(getPosition(), getDirection());
            this.x = positionInDirection.getX();
            this.y = positionInDirection.getY();
            return true;
        }
        return false;
    }

    public boolean move(Direction direction){
        if(gameBoard.canMove(getPosition(), direction)){
            Position<IElement> positionInDirection = (Position<IElement>) gameBoard.positionInDirection(getPosition(), direction);
            this.x = positionInDirection.getX();
            this.y = positionInDirection.getY();
            return true;
        }
        return false;
    }

    public void rotateLeft(){
        Direction leftRotation = getDirection().direction90DegreesToTheLeft();
        setDirection(leftRotation);
    }

    public void rotateRight(){
        Direction rightRotation = getDirection().direction90DegreesToTheLeft();
        setDirection(rightRotation);
    }

    private Direction getDirection() {
        return direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private void setX(int x){
        this.x = x;
    }

    private void setY(int y) {
        this.y = y;
    }

    private void setDirection(Direction direction) {
        this.direction = direction;
    }
}
