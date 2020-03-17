package biblioteket.roborally.actors;

import biblioteket.roborally.board.Board;
import biblioteket.roborally.board.DirVector;
import biblioteket.roborally.board.Direction;

public class Robot implements IRobot {
    private DirVector location;
    private DirVector archiveMarker;

    private IPlayer player;

    private boolean destroyed = false;
    private int damageTokens = 0;

    public Robot(DirVector location) {
        this.location = location;
        this.archiveMarker = new DirVector(location.getX(), location.getY(), Direction.NORTH);
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
    public DirVector getArchiveMarker() {
        return this.archiveMarker;
    }

    @Override
    public void setArchiveMarker(DirVector location) {
        this.archiveMarker = location;
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
    public void moveForward() {
        this.location.forward(1);
    }

    @Override
    public void moveBackward() {
        this.location.backward(1);
    }

    @Override
    public void pushRobotInDirection(Direction direction) {
        this.location.setDirection(direction);
        this.location.forward(1);
    }

    @Override
    public boolean canMoveInDirection(Direction direction) {
        return false;
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
        if (board.canMove(this, getDirection())) {
            this.location = this.location.dirVectorInDirection(getDirection());
            return true;
        }
        return false;
    }

    @Override
    public boolean move(Direction direction, Board board) {
        if (board.canMove(this, direction)) {
            this.location = this.location.dirVectorInDirection(direction);
            if (board.outOfBounds(this.location)) {
                this.addDamageTokens(1);
                this.location = this.getArchiveMarker();
            }
            return true;
        }
        return false;
    }
}
