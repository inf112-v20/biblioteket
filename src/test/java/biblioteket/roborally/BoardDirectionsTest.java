package biblioteket.roborally;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardDirectionsTest {

    @Test
    void direction90DegreesToTheLeftOfNORTH() {
        BoardDirections dirToLeft = BoardDirections.WEST;
        assertEquals(dirToLeft, BoardDirections.NORTH.direction90DegreesToTheLeft());
    }

    @Test
    void direction90DegreesToTheLeftOfSOUTH() {
        BoardDirections dirToLeft = BoardDirections.EAST;
        assertEquals(dirToLeft, BoardDirections.SOUTH.direction90DegreesToTheLeft());
    }

    @Test
    void direction90DegreesToTheLeftOfEAST() {
        BoardDirections dirToLeft = BoardDirections.NORTH;
        assertEquals(dirToLeft, BoardDirections.EAST.direction90DegreesToTheLeft());
    }
    @Test
    void direction90DegreesToTheLeftOfWEST() {
        BoardDirections dirToLeft = BoardDirections.SOUTH;
        assertEquals(dirToLeft, BoardDirections.WEST.direction90DegreesToTheLeft());
    }

    @Test
    void direction90DegreesToTheRightOfNORTH() {
        BoardDirections dirToRight = BoardDirections.EAST;
        assertEquals(dirToRight, BoardDirections.NORTH.direction90DegreesToTheRight());
    }

    @Test
    void direction90DegreesToTheRightOfSOUTH() {
        BoardDirections dirToRight = BoardDirections.WEST;
        assertEquals(dirToRight, BoardDirections.SOUTH.direction90DegreesToTheRight());
    }

    @Test
    void direction90DegreesToTheRightOfEAST() {
        BoardDirections dirToRight = BoardDirections.SOUTH;
        assertEquals(dirToRight, BoardDirections.EAST.direction90DegreesToTheRight());
    }
    @Test
    void direction90DegreesToTheRightOfWEST() {
        BoardDirections dirToRight = BoardDirections.NORTH;
        assertEquals(dirToRight, BoardDirections.WEST.direction90DegreesToTheRight());
    }

    @Test
    void oppositeDirectionOfNORTH() {
        BoardDirections oppositeDirection = BoardDirections.SOUTH;
        assertEquals(oppositeDirection, BoardDirections.NORTH.oppositeDirection());
    }

    @Test
    void oppositeDirectionOfSOUTH() {
        BoardDirections oppositeDirection = BoardDirections.NORTH;
        assertEquals(oppositeDirection, BoardDirections.SOUTH.oppositeDirection());
    }

    @Test
    void oppositeDirectionOfEAST() {
        BoardDirections oppositeDirection = BoardDirections.WEST;
        assertEquals(oppositeDirection, BoardDirections.EAST.oppositeDirection());
    }

    @Test
    void oppositeDirectionOfWEST() {
        BoardDirections oppositeDirection = BoardDirections.EAST;
        assertEquals(oppositeDirection, BoardDirections.WEST.oppositeDirection());
    }
}