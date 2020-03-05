package biblioteket.roborally;

import biblioteket.roborally.board.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DirectionTest {
    @Test
    void turnLeftFromWestTest() {
        Direction dir = Direction.WEST;
        assertEquals(dir, Direction.NORTH.left());
    }

    @Test
    void turnLeftFromEastTest() {
        Direction dir = Direction.EAST;
        assertEquals(dir, Direction.SOUTH.left());
    }

    @Test
    void turnLeftFromNorthTest() {
        Direction dir = Direction.NORTH;
        assertEquals(dir, Direction.EAST.left());
    }

    @Test
    void turnLeftFromSouthTest() {
        Direction dir = Direction.SOUTH;
        assertEquals(dir, Direction.WEST.left());
    }

    @Test
    void turnRightFromEastTest() {
        Direction dir = Direction.EAST;
        assertEquals(dir, Direction.NORTH.right());
    }

    @Test
    void turnRightFromWestTest() {
        Direction dir = Direction.WEST;
        assertEquals(dir, Direction.SOUTH.right());
    }

    @Test
    void turnRightFromSouthTest() {
        Direction dir = Direction.SOUTH;
        assertEquals(dir, Direction.EAST.right());
    }

    @Test
    void turnRightFromNorthTest() {
        Direction dir = Direction.NORTH;
        assertEquals(dir, Direction.WEST.right());
    }

    @Test
    void turnOppositeDirectionFromSouthTest() {
        Direction dir = Direction.SOUTH;
        assertEquals(dir, Direction.NORTH.opposite());
    }

    @Test
    void turnOppositeDirectionFromNorthTest() {
        Direction dir = Direction.NORTH;
        assertEquals(dir, Direction.SOUTH.opposite());
    }

    @Test
    void turnOppositeDirectionFromWestTest() {
        Direction dir = Direction.WEST;
        assertEquals(dir, Direction.EAST.opposite());
    }

    @Test
    void turnOppositeDirectionFromEastTest() {
        Direction dir = Direction.EAST;
        assertEquals(dir, Direction.WEST.opposite());
    }
}