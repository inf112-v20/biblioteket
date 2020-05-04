package biblioteket.roborally.actors;

import biblioteket.roborally.TestRunner;
import biblioteket.roborally.elements.ArchiveMarkerElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(TestRunner.class)
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

    @Test
    void announcingPowerDownResultsInPowerDownNextTurnTest(){
        player.setRobot(new Robot(new ArchiveMarkerElement(1)));
        player.announcePowerDown();
        player.newTurn(null);
        assertTrue(player.isPoweredDown());
    }

    @Test
    void powerDownRemovesDamageTokensTest(){
        player.setRobot(new Robot(new ArchiveMarkerElement(1)));
        player.getRobot().addDamageTokens(5);
        player.announcePowerDown();
        player.newTurn(null);

        int damageTokens = player.getRobot().getNumberOfDamageTokens();
        assertEquals(0, damageTokens);
    }
}