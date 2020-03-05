package biblioteket.roborally.grid;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {
    IPosition<Object> position;

    @BeforeEach
    public void setUp() {
        position = new Position<>(0, 0);
    }


    @Test
    void putTest() {
        Object object = new Object();
        position.put(object);
        Object retrievedObject = position.getContents().get(0);
        assertEquals(object, retrievedObject);

    }

    @Test
    void removeTest() {
        Object object = new Object();
        position.put(object);
        boolean removed = position.remove(object);
        assert (removed);
        List<Object> contents = position.getContents();
        assert (contents.isEmpty());
        assert (position.getContents().isEmpty());
    }

    @Test
    void positionWithoutWallExitNotBlocked(){
        boolean exitToNorthBlocked = position.wallBlockingExit(Direction.NORTH);
        boolean exitToWestBlocked = position.wallBlockingExit(Direction.WEST);
        boolean exitToSouthBlocked = position.wallBlockingExit(Direction.SOUTH);
        boolean exitToEastBlocked = position.wallBlockingExit(Direction.EAST);

        assertFalse(exitToNorthBlocked || exitToWestBlocked || exitToSouthBlocked || exitToEastBlocked);
    }

    @Test
    void positionWithoutWallEntryNotBlocked(){
        boolean entryToNorthBlocked = position.wallBlockingEntry(Direction.NORTH);
        boolean entryToWestBlocked = position.wallBlockingEntry(Direction.WEST);
        boolean entryToSouthBlocked = position.wallBlockingEntry(Direction.SOUTH);
        boolean entryToEastBlocked = position.wallBlockingEntry(Direction.EAST);

        assertFalse(entryToNorthBlocked || entryToWestBlocked || entryToSouthBlocked || entryToEastBlocked);
    }


    @Test
    void wallBlockingExitTest(){
        position.setWall(Direction.WEST, Direction.NORTH);

        boolean exitToNorthBlocked = position.wallBlockingExit(Direction.NORTH);
        boolean exitToWestBlocked = position.wallBlockingExit(Direction.WEST);
        boolean exitToSouthBlocked = position.wallBlockingExit(Direction.SOUTH);
        boolean exitToEastBlocked = position.wallBlockingExit(Direction.EAST);

        assertTrue(exitToNorthBlocked);
        assertTrue(exitToWestBlocked);
        assertFalse(exitToSouthBlocked);
        assertFalse(exitToEastBlocked);
    }

    @Test
    void wallBlockingEntryTest(){
        position.setWall(Direction.WEST, Direction.NORTH);

        boolean entryToNorthBlocked = position.wallBlockingEntry(Direction.NORTH);
        boolean entryToWestBlocked = position.wallBlockingEntry(Direction.WEST);
        boolean entryToSouthBlocked = position.wallBlockingEntry(Direction.SOUTH);
        boolean entryToEastBlocked = position.wallBlockingEntry(Direction.EAST);

        assertFalse(entryToNorthBlocked);
        assertFalse(entryToWestBlocked);
        assertTrue(entryToSouthBlocked);
        assertTrue(entryToEastBlocked);
    }
}
