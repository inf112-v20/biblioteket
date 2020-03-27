package biblioteket.roborally.actors;

import biblioteket.roborally.Pinbot;
import biblioteket.roborally.Direction;
import biblioteket.roborally.grid.IPosition;
import biblioteket.roborally.grid.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PinbotTest {
    Pinbot<Integer> pinbot;

    @BeforeEach
    void setUp() {
        IPosition<Integer> pos = new Position<>(0, 0);
        Direction direction = Direction.NORTH;
        pinbot = new Pinbot(pos, pos, direction);
    }

    @Test
    void removeDamageTokens() {
        int removedDamageTokens = 2;
        int originalNumberOfDamageTokens = pinbot.getNumberOfDamageTokens();
        pinbot.removeDamageTokens(removedDamageTokens);
        int newNumberOfDamageTokens = originalNumberOfDamageTokens - removedDamageTokens;
        assertEquals(newNumberOfDamageTokens, pinbot.getNumberOfDamageTokens());
    }


    @Test
    void addDamageTokens() {
        int addedDamageTokens = 2;
        int originalNumberOfDamageTokens = pinbot.getNumberOfDamageTokens();
        pinbot.addDamageTokens(addedDamageTokens);
        int newNumberOfDamageTokens = originalNumberOfDamageTokens + addedDamageTokens;
        assertEquals(newNumberOfDamageTokens, pinbot.getNumberOfDamageTokens());
    }

    @Test
    void removeAllDamageTokens() {
        pinbot.removeAllDamageTokens();
        assertEquals(0, pinbot.getNumberOfDamageTokens());
    }

    @Test
    void isDestroyed() {
        pinbot.addDamageTokens(10);
        assertTrue(pinbot.isDestroyed());
    }
}