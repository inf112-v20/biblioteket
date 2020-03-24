package biblioteket.roborally.actors;

import biblioteket.roborally.board.Board;
import biblioteket.roborally.board.DirVector;
import biblioteket.roborally.board.Direction;
import biblioteket.roborally.board.IBoard;
import biblioteket.roborally.elements.ArchiveMarkerElement;

public class Robot implements IRobot {
    private DirVector location;
    private final ArchiveMarkerElement archiveMarker;

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
    public boolean move(Direction direction, Board board) {
        if (board.canMove(getPosition(), direction)) {
            this.location = this.location.dirVectorInDirection(direction);
            if (board.outOfBounds(this.location)) {
                this.addDamageTokens(1);
                moveToArchiveMarker();
            }
            return true;
        }
        return false;
    }

    // Need to add support for pit and pushing other robots.
    // Do something about direction, robots respawn in the direction which the archive marker has stored.
    // Not sure what do do about damage tokens.
    // Not sure what do do if player is dead.
    // Need working placeRobotOnArchiveMarker
    @Override
    public void moveRobot(Direction direction, IBoard board) {
        DirVector locationInDirection = this.location.dirVectorInDirection(direction);
        if (board.canMove(this.location, direction)) { //Check if blocked by immovable object. Should not include robots or out of bounds.
            if (board.outOfBounds(locationInDirection)) { //Check if robot moves off board
                player.removeOneLife();
                if (player.hasLivesLeft()) {
                    //do something about damage tokens and direction
                    moveToArchiveMarker();
                    //add else if first for pit then for pushing other robots.
                } else {//Not sure to handle what to do when player is dead.
                    this.location = null;
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
