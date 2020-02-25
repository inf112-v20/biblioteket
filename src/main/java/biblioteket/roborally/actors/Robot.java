package biblioteket.roborally.actors;

import biblioteket.roborally.Direction;
import biblioteket.roborally.IPosition;

public class Robot implements IRobot {
    private IPosition position;
    private IPosition archiveMarker;
    private Direction direction;

    private IPlayer player;

    private boolean destroyed = false;
    private int damageTokens = 0;

    public Robot(IPosition position, IPosition archiveMarker, Direction direction) {
        this.position = position;
        this.archiveMarker = archiveMarker;
        this.direction = direction;
    }

    @Override
    public IPlayer getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(IPlayer player) {
        this.player = player;
    }

    @Override
    public int getNumberOfDamageTokens() {
        return damageTokens;
    }

    @Override
    public void removeDamageTokens(int damageTokens) {

        this.damageTokens = this.damageTokens - damageTokens;
    }

    @Override
    public void addDamageTokens(int damageTokens) {

        this.damageTokens = this.damageTokens + damageTokens;
    }

    @Override
    public void removeAllDamageTokens() {

        this.damageTokens = 0;
    }

    @Override
    public boolean isDestroyed() {

        return damageTokens > 9;
    }

    @Override
    public IPosition getArchiveMarker() {
        return archiveMarker;
    }

    @Override
    public void setArchiveMarker(IPosition location) {
        this.archiveMarker = location;
    }

    @Override
    public IPosition getPosition() {
        return position;
    }

    @Override
    public void setPosition(IPosition location) { this.position = location; }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void setDirection(Direction direction) { this.direction = direction; }

    @Override
    public void turnLeft() {

        this.direction = this.direction.direction90DegreesToTheLeft();
    }

    @Override
    public void turnRight() {

        this.direction = this.direction.direction90DegreesToTheRight();
    }

    @Override
    public void moveForward() {
        this.setPosition(position.locationInDirection(direction));
    }

    @Override
    public void moveBackward() {
        this.setPosition(position.locationInDirection(direction.oppositeDirection()));
    }

    //TODO
    @Override
    public void pushRobotInDirection(BoardDirections direction) {

    }

    //TODO
    @Override
    public boolean canMoveInDirection(Direction direction) {
        return false;
    }

}