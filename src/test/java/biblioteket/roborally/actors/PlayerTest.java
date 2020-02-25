package biblioteket.roborally.actors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    IPlayer player;

    @BeforeEach
    void setUp() {
        player = new Player();
    }

    @Test
    void isPermanentDead() {

        player.setNumberOfLivesRemaining(0);
        assertTrue(player.isPermanentDead());
    }

    @Test
    void hasLivesLeft() {

        assertTrue(player.hasLivesLeft());
    }

    @Test
    void removeOneLife() {

        int originalNumberOfLives = player.getNumberOfLivesRemaining();
        player.removeOneLife();
        int newNumberOfLives = originalNumberOfLives - 1;
        assertEquals(newNumberOfLives, player.getNumberOfLivesRemaining());
    }

    @Test
    void addToFlagsVisited() {

        int originalNumberOfFlagsVisited = player.getNumberOfVisitedFlags();
        player.addToFlagsVisited();
        int newNumberOfFlagsVisited = originalNumberOfFlagsVisited + 1;
        assertEquals(newNumberOfFlagsVisited, player.getNumberOfVisitedFlags());
    }
}