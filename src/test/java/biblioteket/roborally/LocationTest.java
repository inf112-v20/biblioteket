package biblioteket.roborally;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LocationTest {
    /**
    ILocation location;
    int x = 0;
    int y = 0;
    int maxBoardWidth;
    int maxBoardHeight;
    int minBoardWidth;
    int minBoardHeight;

    @BeforeEach
    void setUp() {
        location = new Location(x,y);
        maxBoardWidth = 5;
        maxBoardHeight = 5;
        minBoardHeight = 0;
        minBoardWidth = 0;
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void setXPosition() { }

    @Test
    void getXPosition() { }

    @Test
    void setYPosition() { }

    @Test
    void getYPosition() { }

    @Test
    void xIsWithinBoardTRUEWhenXIs0() {
        assertTrue(location.xIsWithinBoard(0));
    }

    @Test
    void xIsWithinBoardFALSEWhenXisLessThanMinWidth() {
        assertFalse(location.xIsWithinBoard(minBoardWidth - 1));
    }

    @Test
    void xIsWithinBoardTRUEWhenXisMoreThanMinWidthAndLessThanBoardWidth() {
        assertTrue(location.xIsWithinBoard(minBoardWidth + 1));
        assertTrue(location.xIsWithinBoard(maxBoardWidth - 1));
    }

    @Test
    void xIsWithinBoardFALSEWhenXisMoreThanBoardWidth() {
        assertFalse(location.xIsWithinBoard(maxBoardWidth + 1));
    }

    @Test
    void yIsWithinBoardTRUEWhenYIs0() {
        assertTrue(location.yIsWithinBoard(0));
    }

    @Test
    void yIsWithinBoardFALSEWhenYisLessThanMinHeight() {
        assertFalse(location.yIsWithinBoard(minBoardHeight - 1));
    }

    @Test
    void yIsWithinBoardTRUEWhenYisMoreThanMinHeightAndLessThanBoardHeight() {
        assertTrue(location.xIsWithinBoard(minBoardHeight + 1));
        assertTrue(location.xIsWithinBoard(maxBoardHeight - 1));
    }

    @Test
    void yIsWithinBoardFALSEWhenYisMoreBoardHeight() {
        assertFalse(location.yIsWithinBoard(maxBoardHeight + 1));
    }

    @Test
    void allNeighbours() {
    }

    @Test
    void cardinalNeighbours() {

    }

    @Test
    void canMoveInDirection() {
    }

    @Test
    void willMoveOfBoardToEastWhenXisLessThanMinWidth() {
        //assertTrue(location.willMoveOfBoard(Directions.EAST));
    }

    @Test
    void willMoveOfBoardToEastWhenXisMoreThanMinWidth() {
        //assertFalse(location.willMoveOfBoard(Directions.EAST));
    }

    @Test
    void willMoveOfBoardToSouthWhenYisLessThanMinHeight() {
        //assertTrue(location.willMoveOfBoard(Directions.SOUTH));
    }

    @Test
    void willMoveOfBoardToSouthWhenYisMoreThanMinHeight() {
        //assertFalse(location.willMoveOfBoard(Directions.SOUTH));
    }

    @Test
    void willMoveOfBoardToWestWhenXisMoreThanBoardWidth() {
        //assertTrue(location.willMoveOfBoard(Directions.WEST));
    }

    @Test
    void willMoveOfBoardToWestWhenXisLessThanBoardWidth() {
        //assertFalse(location.willMoveOfBoard(Directions.WEST));
    }

    @Test
    void willMoveOfBoardToNorthWhenYisMoreThanBoardHeight() {
        //assertTrue(location.willMoveOfBoard(Directions.NORTH));
    }

    @Test
    void willMoveOfBoardToNorthWhenYisLessThanBoardHeight() {
        //assertFalse(location.willMoveOfBoard(Directions.NORTH));
    }

    @Test
    void isOfBoard() {
    }

    @Test
    void isWithinBoard() {
    }

    @Test
    void locationInDirectionNORTH() {
        Location locationInDirection = new Location(location.getX(),location.getY() + 1);
        assertEquals(locationInDirection, location.locationInDirection(Directions.NORTH));
    }

    @Test
    void locationInDirectionSOUTH() {
        Location locationInDirection = new Location(location.getX(),location.getY() - 1);
        assertEquals(locationInDirection, location.locationInDirection(Directions.SOUTH));
    }

    @Test
    void locationInDirectionEAST() {
        Location locationInDirection = new Location(location.getX() - 1,location.getY());
        assertEquals(locationInDirection, location.locationInDirection(Directions.EAST));
    }

    @Test
    void locationInDirectionWEST() {
        Location locationInDirection = new Location(location.getX() + 1,location.getY());
        assertEquals(locationInDirection, location.locationInDirection(Directions.WEST));
    }

    @Test
    void locationsInLineToGivenLocation() {
    }

    @Test
    void locationsInDirection() {
    }

    @Test
    void locationsInDirectionUntilObstacle() {
    }

    @Test
    void locationInDirectionObstacleIsEncountered() {
    }
    */
}