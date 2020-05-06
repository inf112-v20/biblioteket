package biblioteket.roborally.actors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerStateTest {
    private static PlayerState playing;
    private static PlayerState immobile;
    private static PlayerState announcedPowerDown;
    private static PlayerState poweredDown;
    private static PlayerState destroyed;

    @BeforeAll
    private static void setUp(){
        playing = PlayerState.PLAYING;
        immobile = PlayerState.IMMOBILE;
        announcedPowerDown = PlayerState.ANNOUNCED_POWER_DOWN;
        poweredDown = PlayerState.POWERED_DOWN;
        destroyed = PlayerState.DESTROYED;
    }

    @Test
    void isPoweredDownTest(){
        assertTrue(poweredDown.isPoweredDown());

        assertFalse(playing.isPoweredDown());
        assertFalse(immobile.isPoweredDown());
        assertFalse(announcedPowerDown.isPoweredDown());
        assertFalse(destroyed.isPoweredDown());
    }

    @Test
    void isDestroyedTest(){
        assertTrue(destroyed.isDestroyed());

        assertFalse(playing.isDestroyed());
        assertFalse(immobile.isDestroyed());
        assertFalse(announcedPowerDown.isDestroyed());
        assertFalse(poweredDown.isDestroyed());
    }

    @Test
    void canMoveTest(){
        assertTrue(playing.canMove());
        assertTrue(announcedPowerDown.canMove());

        assertFalse(immobile.canMove());
        assertFalse(poweredDown.canMove());
        assertFalse(destroyed.canMove());
    }

    @Test
    void announcePowerDownTest(){
        PlayerState state = playing.announcePowerDown();
        assertEquals(PlayerState.ANNOUNCED_POWER_DOWN, state);

        state = immobile.announcePowerDown();
        assertEquals(PlayerState.ANNOUNCED_POWER_DOWN, state);

        state = announcedPowerDown.announcePowerDown();
        assertEquals(PlayerState.ANNOUNCED_POWER_DOWN, state);

        state = poweredDown.announcePowerDown();
        assertEquals(PlayerState.ANNOUNCED_POWER_DOWN, state);

        state = destroyed.announcePowerDown();
        assertNotEquals(PlayerState.ANNOUNCED_POWER_DOWN, state);
    }

    @Test
    void nextTurnTest(){
        PlayerState state = playing.nextTurn();
        assertEquals(PlayerState.PLAYING, state);

        state = immobile.nextTurn();
        assertEquals(PlayerState.PLAYING, state);

        state = announcedPowerDown.nextTurn();
        assertEquals(PlayerState.POWERED_DOWN, state);

        state = poweredDown.nextTurn();
        assertEquals(PlayerState.PLAYING, state);

        state = destroyed.nextTurn();
        assertEquals(PlayerState.DESTROYED, state);
    }
}
