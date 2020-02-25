package biblioteket.roborally.actors;

import biblioteket.roborally.Direction;
import biblioteket.roborally.Grid.IPosition;

public class Robot<T> implements IRobot<T> {
    private IPosition<T> position;
    private IPosition<T> archiveMarker;
    private Direction direction;

    private IPlayer player;

    private boolean destroyed = false;
    private int damageTokens = 0;

    public Robot(IPosition<T> position, IPosition<T> archiveMarker, Direction direction) {
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
    public IPosition<T> getArchiveMarker() {
        return archiveMarker;
    }

    @Override
    public void setArchiveMarker(IPosition<T> location) {
        this.archiveMarker = location;
    }

    @Override
    public IPosition<T> getPosition() {
        return position;
    }

    @Override
    public void setPosition(IPosition<T> location) {
        this.position = location;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void turnLeft() {

        this.direction = this.direction.direction90DegreesToTheLeft();
    }

    @Override
    public void turnRight() {

        this.direction = this.direction.direction90DegreesToTheRight();
    }

    // TODO
    @Override
    public void moveForward() {
        // this.setPosition(position.locationInDirection(direction));
    }

    // TODO
    @Override
    public void moveBackward() {
        // this.setPosition(position.locationInDirection(direction.oppositeDirection()));
    }

    //TODO
    @Override
    public void pushRobotInDirection(Direction direction) {

    }

    //TODO
    @Override
    public boolean canMoveInDirection(Direction direction) {
        return false;
    }

}