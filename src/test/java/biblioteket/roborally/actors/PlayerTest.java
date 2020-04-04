package biblioteket.roborally.actors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerTest {
    private IPlayer player;

    @BeforeEach
    void setUp() {
        player = new Player(null, null, null, null);
    }

    @Test
    void isPermanentDead() {
        player.removeOneLife();
        player.removeOneLife();
        player.removeOneLife();
        assertTrue(player.isPermanentDead());
    }

    @Test
    void hasLivesLeft() {
        assertTrue(player.hasLivesLeft());
    }

    @Test
    void removeOneLife() {
        int originalNumberOfLives = player.getLives();
        player.removeOneLife();
        int newNumberOfLives = originalNumberOfLives - 1;
        assertEquals(newNumberOfLives, player.getLives());
    }

    @Test
    void addToFlagsVisited() {
        int originalNumberOfFlagsVisited = player.getNumberOfVisitedFlags();
        player.addToFlagsVisited();
        int newNumberOfFlagsVisited = originalNumberOfFlagsVisited + 1;
        assertEquals(newNumberOfFlagsVisited, player.getNumberOfVisitedFlags());
    }
}