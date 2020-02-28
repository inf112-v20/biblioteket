package biblioteket.roborally;

import java.util.Arrays;
import java.util.List;


public enum Direction {
    NORTH, EAST, SOUTH, WEST;

    /**
     * The four cardinal directions: {@link #NORTH}, {@link #SOUTH}, {@link #EAST},
     * {@link #WEST}.
     */
    public static final List<Direction> FOUR_DIRECTIONS = Arrays.asList(EAST, NORTH, WEST, SOUTH);

    /**
     * Will return the direction 90 degrees to the left.<p>
     * {@link #NORTH} -> {@link #WEST} <p>
     * {@link #SOUTH} -> {@link #EAST} <p>
     * {@link #EAST} -> {@link #NORTH} <p>
     * {@link #WEST} -> {@link #SOUTH} <p>
     *
     * @return direction 90 degrees to left.
     */

    public Direction direction90DegreesToTheLeft() {
        switch (this) {
            case NORTH:
                return Direction.WEST;
            case SOUTH:
                return Direction.EAST;
            case EAST:
                return Direction.NORTH;
            case WEST:
                return Direction.SOUTH;
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
     *
     * @return direction 90 degrees to right.
     */

    public Direction direction90DegreesToTheRight() {
        switch (this) {
            case NORTH:
                return Direction.EAST;
            case SOUTH:
                return Direction.WEST;
            case EAST:
                return Direction.SOUTH;
            case WEST:
                return Direction.NORTH;
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
     *
     * @return opposite direction.
     */

    public Direction oppositeDirection() {
        switch (this) {
            case NORTH:
                return Direction.SOUTH;
            case SOUTH:
                return Direction.NORTH;
            case EAST:
                return Direction.WEST;
            case WEST:
                return Direction.EAST;
            default:
                throw new IllegalStateException("Should never happen: " + this + " has no opposite." +
                        " Assert that no new direction value has been added to the enum, but not to the methods.");
        }
    }

}
