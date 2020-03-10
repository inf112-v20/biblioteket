package biblioteket.roborally.actors;

import biblioteket.roborally.Direction;
import biblioteket.roborally.GameBoard;
import biblioteket.roborally.IElement;
import biblioteket.roborally.IGameBoard;
import biblioteket.roborally.grid.IGrid;
import biblioteket.roborally.grid.IPosition;

import java.util.List;
import java.util.UUID;

public class Robot<T> implements IRobot<T> {
    private IPosition<T> position;
    private IPosition<T> archiveMarker;
    private Direction direction;
    private GameBoard board; //Can't use IGameboard since it does not have getGrid method

    private IPlayer player;

    private boolean destroyed = false;
    private int damageTokens = 0;

    public Robot(IPosition<T> position, IPosition<T> archiveMarker, Direction direction, GameBoard board) {
        this.position = position;
        this.archiveMarker = archiveMarker;
        this.direction = direction;
        this.board = board;
        this.position.put((T) this);
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

    //TODO should be removed.
    @Override
    public void setPosition(IPosition<T> location) {
        this.position.remove((T) this);
        this.position = location;
        this.position.put((T) this);
    }

    @Override
    public IPosition getPos() {
        return position;
    }

    //TODO needs test
    @Override
    public void setPos(IPosition pos) {
        this.position.remove((T) this);
        this.position = pos;
        this.position.put((T) this);
    }

    @Override
    public void setPos(int x, int y) {
        IGrid grid = board.getGrid();
        this.position.remove((T) this);
        this.position = grid.getPosition(x, y);
        this.position.put((T) this);
    }

    @Override
    public boolean immovable() {
        return false;
    }

    @Override
    public UUID getID() {
        return null;
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
        move(direction);
    }

    // TODO
    @Override
    public void moveBackward() {
        move(direction.oppositeDirection());
    }

    // TODO needs more
    @Override
    public void move(Direction direction) {
        IGrid<IElement> grid = board.getGrid();
        IPosition<IElement> positionInDirection = grid.positionInDirection((IPosition<IElement>) this.position, direction);
        if (positionInDirection == null) { //Moves of board
            player.removeOneLife();
            if (player.hasLivesLeft()) {
                removeAllDamageTokens();
                setPos(archiveMarker); // TODO need to handle if archive marker position is taken.
            }
        } else if ((positionInDirection.containsRobot())) {
            List<IElement> elementsInPosition = positionInDirection.getContents(); //This should be handled in position.
            IRobot robotInPosition = null;
            for (IElement element : elementsInPosition) {
                if (element instanceof IRobot<?>)
                    robotInPosition = (IRobot) element;
            }
            if (!robotInPosition.canMoveInDirection(direction)) {
                return;
            } else {
                setPosition((IPosition<T>) positionInDirection);
                robotInPosition.move(direction);
            }
        } else {
            setPosition((IPosition<T>) positionInDirection);
        }

    }

    //TODO
    @Override
    public void pushRobotInDirection(Direction direction) {

    }

    //TODO needs test
    @Override
    public boolean canMoveInDirection(Direction direction) {
        return !board.containsImmovableObject((IPosition<IElement>) position, direction);
    }

}