package biblioteket.roborally;

import java.util.Arrays;
import java.util.List;

public enum BoardDirections {
    NORTH, EAST, SOUTH, WEST;

    /**
     * The four cardinal directions: {@link #NORTH}, {@link #SOUTH}, {@link #EAST},
     * {@link #WEST}.
     */
    public static final List<BoardDirections> FOUR_DIRECTIONS = Arrays.asList(EAST, NORTH, WEST, SOUTH);

    /**
     * Will return the direction 90 degrees to the left.<p>
     * {@link #NORTH} -> {@link #WEST} <p>
     * {@link #SOUTH} -> {@link #EAST} <p>
     * {@link #EAST} -> {@link #NORTH} <p>
     * {@link #WEST} -> {@link #SOUTH} <p>
     * @return direction 90 degrees to left.
     */
    public BoardDirections direction90DegreesToTheLeft() {
        switch (this) {
            case NORTH:
                return BoardDirections.WEST;
            case SOUTH:
                return BoardDirections.EAST;
            case EAST:
                return BoardDirections.NORTH;
            case WEST:
                return BoardDirections.SOUTH;
            default:
                throw new IllegalStateException("Should never happen: " + this + " has no direction 90 degrees to the left." +
                        " Assert that no new direction value has been added to the enum, but not to the methods.");
        }
    }

    /**
     * Will return the direction 90 degrees to the right.<p>
     * {@link #NORTH} -> {@link #EAST} <p>
     * {@link #SOUTH} -> {@link #WEST} <p>
     * {@link #EAST} -> {@link #SOUTH} <p>
     * {@link #WEST} -> {@link #NORTH} <p>
     * @return direction 90 degrees to right.
     */
    public BoardDirections direction90DegreesToTheRight() {
        switch (this) {
            case NORTH:
                return BoardDirections.EAST;
            case SOUTH:
                return BoardDirections.WEST;
            case EAST:
                return BoardDirections.SOUTH;
            case WEST:
                return BoardDirections.NORTH;
            default:
                throw new IllegalStateException("Should never happen: " + this + " has no direction 90 degrees to the right." +
                        " Assert that no new direction value has been added to the enum, but not to the methods.");
        }
    }

    /**
     * Will return the opposite direction 180 degrees away.<p>
     * {@link #NORTH} -> {@link #SOUTH} <p>
     * {@link #SOUTH} -> {@link #NORTH} <p>
     * {@link #EAST} -> {@link #WEST} <p>
     * {@link #WEST} -> {@link #EAST} <p>
     * @return opposite direction.
     */
    public BoardDirections oppositeDirection() {
        switch (this) {
            case NORTH:
                return BoardDirections.SOUTH;
            case SOUTH:
                return BoardDirections.NORTH;
            case EAST:
                return BoardDirections.WEST;
            case WEST:
                return BoardDirections.EAST;
            default:
                throw new IllegalStateException("Should never happen: " + this + " has no opposite." +
                        " Assert that no new direction value has been added to the enum, but not to the methods.");
        }
    }

}