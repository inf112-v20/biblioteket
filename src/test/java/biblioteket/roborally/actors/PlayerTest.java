package biblioteket.roborally.actors;

import biblioteket.roborally.elements.ArchiveMarkerElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerTest {
    private IActor player;

    @BeforeEach
    void setUp() {
        player = new Actor(null, null, null, new RobotRenderer(null, null, null));
    }

    @Test
    void isPermanentDead() {
        player.setRobot(new Robot(new ArchiveMarkerElement(1)));

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