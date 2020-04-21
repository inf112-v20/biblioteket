package biblioteket.roborally.elements.interacting;

import biblioteket.roborally.actors.IPlayer;
import biblioteket.roborally.actors.IRobot;
import biblioteket.roborally.actors.Player;
import biblioteket.roborally.actors.Robot;
import biblioteket.roborally.board.DirVector;
import biblioteket.roborally.elements.ArchiveMarkerElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HoleTest {
    private IPlayer player;

    @BeforeEach
    void setUp() {
        player = new Player(null, null, null, null);
        player.setRobot(new Robot(new ArchiveMarkerElement(1)));
    }

    @Test
    void interactingWithHoleAddsDamageTokenTest() {
        HoleElement hole = new HoleElement();
        hole.interact(player);

        assertEquals(1, player.getRobot().getNumberOfDamageTokens());

    }

    @Test
    void interactingWithHoleMovesRobotToArchiveMarkerTest() {
        HoleElement hole = new HoleElement();
        IRobot robot = player.getRobot();
        ArchiveMarkerElement archiveMarker = robot.getArchiveMarker();
        // Set robots archive marker to 1,1 and robots position to 5,5
        archiveMarker.setPosition(new DirVector(1,1,null));
        robot.setPosition(5, 5);

        hole.interact(player);

        assertEquals(archiveMarker.getPosition().getX(), robot.getPosition().getX());
        assertEquals(archiveMarker.getPosition().getY(), robot.getPosition().getY());

    }
}
