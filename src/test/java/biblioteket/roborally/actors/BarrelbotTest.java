package biblioteket.roborally.actors;

import biblioteket.roborally.Barrelbot;
import biblioteket.roborally.Direction;
import biblioteket.roborally.grid.IPosition;
import biblioteket.roborally.grid.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BarrelbotTest {
    Barrelbot<Integer> barrelbot;
    
    @BeforeEach
    void setUp() {
        IPosition<Integer> pos = new Position<>(0, 0);
        Direction direction = Direction.NORTH;
        barrelbot = new Barrelbot(pos, pos, direction);
    }

    @Test
    void removeDamageTokens() {
        int removedDamageTokens = 2;
        int originalNumberOfDamageTokens = barrelbot.getNumberOfDamageTokens();
        barrelbot.removeDamageTokens(removedDamageTokens);
        int newNumberOfDamageTokens = originalNumberOfDamageTokens - removedDamageTokens;
        assertEquals(newNumberOfDamageTokens, barrelbot.getNumberOfDamageTokens());
    }

    @Test
    void addDamageTokens() {
        int addedDamageTokens = 2;
        int originalNumberOfDamageTokens = barrelbot.getNumberOfDamageTokens();
        barrelbot.addDamageTokens(addedDamageTokens);
        int newNumberOfDamageTokens = originalNumberOfDamageTokens + addedDamageTokens;
        assertEquals(newNumberOfDamageTokens, barrelbot.getNumberOfDamageTokens());
    }

    @Test
    void removeAllDamageTokens() {
        barrelbot.removeAllDamageTokens();
        assertEquals(0, barrelbot.getNumberOfDamageTokens());
    }

    @Test
    void isDestroyed() {
        barrelbot.addDamageTokens(10);
        assertTrue(barrelbot.isDestroyed());
    }

    @Test
    void turnLeftFromNorth() {
        barrelbot.setDirection(Direction.NORTH);
        barrelbot.turnLeft();
        assertEquals(Direction.WEST, barrelbot.getDirection());
    }

    @Test
    void turnLeftFromSouth() {
        barrelbot.setDirection(Direction.SOUTH);
        barrelbot.turnLeft();
        assertEquals(Direction.EAST, barrelbot.getDirection());
    }

    @Test
    void turnLeftFromEast() {
        barrelbot.setDirection(Direction.EAST);
        barrelbot.turnLeft();
        assertEquals(Direction.NORTH, barrelbot.getDirection());
    }

    @Test
    void turnLeftFromWest() {

        barrelbot.setDirection(Direction.WEST);
        barrelbot.turnLeft();
        assertEquals(Direction.SOUTH, barrelbot.getDirection());
    }

    @Test
    void turnRightFromNorth() {
        barrelbot.setDirection(Direction.NORTH);
        barrelbot.turnRight();
        assertEquals(Direction.EAST, barrelbot.getDirection());
    }

    @Test
    void turnRightFromSouth() {
        barrelbot.setDirection(Direction.SOUTH);
        barrelbot.turnRight();
        assertEquals(Direction.WEST, barrelbot.getDirection());
    }

    @Test
    void turnRightFromEast() {
        barrelbot.setDirection(Direction.EAST);
        barrelbot.turnRight();
        assertEquals(Direction.SOUTH, barrelbot.getDirection());
    }

    @Test
    void turnRightFromWest() {
        barrelbot.setDirection(Direction.WEST);
        barrelbot.turnRight();
        assertEquals(Direction.NORTH, barrelbot.getDirection());
    }

}
