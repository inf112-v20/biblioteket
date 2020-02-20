package biblioteket.roborally;

import java.util.Collection;

public class Location implements ILocation {
    //TODO: should remove these and use actual board measurements
    static final int BOARD_WIDTH = 5;
    static final int BOARD_HEIGHT = 5;
    static final int MIN_BOARD_HEIGHT = 0;
    static final int MIN_BOARD_WIDTH = 0;

    private int x;
    private int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void setX(int xPosition) { this.x = xPosition;}

    @Override
    public int getX() { return x; }

    @Override
    public boolean xIsWithinBoard(int x) {
        if( x > BOARD_WIDTH || x < 0)
            return false;
        return true;
    }

    @Override
    public void setY(int yPosition) { this.y = yPosition;}

    @Override
    public int getY() { return y; }

    @Override
    public boolean yIsWithinBoard(int y) {
        if( y > BOARD_WIDTH || y < 0)
            return false;
        return true;
    }

    //TODO
    @Override
    public Collection<ILocation> allNeighbours() {
        return null;
    }

    //TODO
    @Override
    public Collection<ILocation> cardinalNeighbours() {
        return null;
    }

    //TODO
    @Override
    public boolean canMoveInDirection(BoardDirections direction) {
        return false;
    }

    //TODO
    @Override
    public boolean willMoveOfBoard(BoardDirections direction) {
        return false;
    }

    @Override
    public boolean isOfBoard(ILocation location) {
        if( location.getX() > BOARD_WIDTH || location.getX() < MIN_BOARD_WIDTH || location.getY() > BOARD_HEIGHT || location.getY() < MIN_BOARD_HEIGHT)
            return true;
        return false;
    }

    @Override
    public boolean isOfBoard() {
        if( this.x > BOARD_WIDTH || this.x < MIN_BOARD_WIDTH || this.y > BOARD_HEIGHT || this.y < MIN_BOARD_HEIGHT)
            return true;
        return false;
    }

    @Override
    public boolean isWithinBoard(ILocation location) {
        if( location.getX() > BOARD_WIDTH || location.getX() < MIN_BOARD_WIDTH || location.getY() > BOARD_HEIGHT || location.getY() < MIN_BOARD_HEIGHT)
            return false;
        return true;    }

    @Override
    public boolean isWithinBoard() {
        if( this.x > BOARD_WIDTH || this.x < MIN_BOARD_WIDTH || this.y > BOARD_HEIGHT || this.y < MIN_BOARD_HEIGHT)
            return true;
        return false;
    }

    //TODO: This must be linked to map, remember to handle boundaries!
    @Override
    public ILocation locationInDirection(BoardDirections direction) {
        Location locationInDirection;
        switch (direction) {
            case NORTH:
                //grid.getLocation(this.x, this.y + 1)
                locationInDirection = new Location(this.x, this.y + 1);
                return locationInDirection;
            case SOUTH:
                locationInDirection = new Location(this.x, this.y - 1);
                return locationInDirection;
            case EAST:
                locationInDirection = new Location(this.x - 1, this.y);
                return locationInDirection;
            case WEST:
                locationInDirection = new Location(this.x + 1, this.y);
                return locationInDirection;
            default:
                throw new IllegalStateException("Something is not being handled, can be that location has been" +
                        " linked to grid and cannot find location when it is outside of boundaries.");
        }
    }

    //TODO
    @Override
    public Collection<ILocation> locationsInLineToGivenLocation(ILocation location) {
        return null;
    }

    //TODO
    @Override
    public Collection<ILocation> locationsInDirection(BoardDirections direction) {
        return null;
    }

    //TODO
    @Override
    public Collection<ILocation> locationsInDirectionUntilObstacle(BoardDirections direction) {
        return null;
    }

    //TODO
    @Override
    public ILocation locationInDirectionObstacleIsEncountered(BoardDirections direction) {
        return null;
    }

    //TODO: Should probably check board measurements
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;

        if (obj.getClass() != this.getClass())
            return false;

        Location that = (Location) obj;
        if (this.x != that.x ) return false;
        if (this.y != that.y) return false;

        return true;
    }
}