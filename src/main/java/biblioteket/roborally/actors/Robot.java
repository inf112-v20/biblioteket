package biblioteket.roborally.actors;

import biblioteket.roborally.board.Board;
import biblioteket.roborally.board.DirVector;
import biblioteket.roborally.board.Direction;
import biblioteket.roborally.board.IBoard;
import biblioteket.roborally.elements.ArchiveMarkerElement;

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
    public void pushRobotInDirection(Direction direction) {
//        this.location.setDirection(direction);
        this.location.forward(direction);
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
    public void moveToArchiveMarker() {
        setPosition(archiveMarker.getX(), archiveMarker.getY());
    }
}
