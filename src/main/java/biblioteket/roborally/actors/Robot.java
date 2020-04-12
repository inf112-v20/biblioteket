package biblioteket.roborally.actors;

import biblioteket.roborally.board.Board;
import biblioteket.roborally.board.DirVector;
import biblioteket.roborally.board.Direction;
import biblioteket.roborally.board.IBoard;
import biblioteket.roborally.elements.ArchiveMarkerElement;
import biblioteket.roborally.elements.interacting.HoleElement;

public class Robot implements IRobot {
    private final ArchiveMarkerElement archiveMarker;
    private DirVector location;
    private IPlayer player;
    private int damageTokens = 0;

    public Robot(ArchiveMarkerElement archiveMarker) {
        this.archiveMarker = archiveMarker;
        this.location = new DirVector(archiveMarker.getX(), archiveMarker.getY(), Direction.NORTH);
    }

    @Override
    public IPlayer getPlayer() {
        return this.player;
    }

    @Override
    public void setPlayer(IPlayer player) {
        this.player = player;
    }

    @Override
    public int getNumberOfDamageTokens() {
        return this.damageTokens;
    }

    @Override
    public void removeDamageTokens(int damageTokens) {
        this.damageTokens -= damageTokens;
    }

    @Override
    public void addDamageTokens(int damageTokens) {
        this.damageTokens += damageTokens;
    }

    @Override
    public boolean isDestroyed() {
        return this.damageTokens > 9;
    }

    @Override
    public ArchiveMarkerElement getArchiveMarker() {
        return this.archiveMarker;
    }

    @Override
    public void setArchiveMarker(DirVector location) {
        archiveMarker.setX(location.getX());
        archiveMarker.setY(location.getY());
    }

    @Override
    public void turnLeft() {
        this.location.left();
    }

    @Override
    public void turnRight() {
        this.location.right();
    }

    @Override
    public void moveForward(IBoard board) {
        moveRobot(getDirection(), board);
    }

    @Override
    public void moveBackward(IBoard board) {
        Direction startDirection = getDirection();
        moveRobot(getDirection().opposite(), board);
        setDirection(startDirection);
    }

    @Override
    public void pushRobotInDirection(Direction direction) {
        this.location.setDirection(direction);
        this.location.forward(1);
    }

    @Override
    public DirVector getPosition() {
        return this.location;
    }

    @Override
    public void setPosition(DirVector location) {
        this.location = location;
    }

    @Override
    public void setPosition(int x, int y) {
        this.location = new DirVector(x, y, this.location.getDirection());
    }

    @Override
    public Direction getDirection() {
        return this.location.getDirection();
    }

    @Override
    public void setDirection(Direction direction) {
        this.location.setDirection(direction);
    }

    @Override
    public boolean moveForward(Board board) {
        if (board.canMove(getPosition(), getDirection())) {
            this.location = this.location.dirVectorInDirection(getDirection());
            return true;
        }
        return false;
    }

    @Override
    public boolean move(Direction direction, IBoard board) {
        if (board.canMove(getPosition(), direction)) {
            this.location = this.location.dirVectorInDirection(direction);
            Board boardInteract = (Board) board; // Will change when all methods are added to IBoard
            if (boardInteract.getInteractingElement(this.location) instanceof HoleElement) {
                System.out.println("Move: robot at " + getPosition().getX() + "," + getPosition().getY() + " fell into pit");
                player.removeOneLife();
                moveToArchiveMarker();
            }
            if (board.outOfBounds(this.location)) {
                System.out.println("Move: robot at " + getPosition().getX() + "," + getPosition().getY() + " moved out of bounds");
                player.removeOneLife();
                moveToArchiveMarker();
            }
            return true;
        }
        return false;
    }

    @Override
    public void moveRobot(Direction direction, IBoard board) {
        DirVector locationInDirection = this.location.dirVectorInDirection(direction);
        if (board.canMove(this.location, direction)) { //Check if blocked by immovable object. Should not include robots or out of bounds.
            if (board.outOfBounds(locationInDirection)) { //Check if robot moves off board
                player.removeOneLife();
                if (player.hasLivesLeft()) {
                    moveToArchiveMarker();
                }
            } else {// Moves the robot in direction
                setPosition(locationInDirection);
            }
        }
    }

    @Override
    public void moveToArchiveMarker() {
        setPosition(archiveMarker.getX(), archiveMarker.getY());
    }
}
