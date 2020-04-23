package biblioteket.roborally.elements.interacting;

import biblioteket.roborally.actors.IActor;
import biblioteket.roborally.actors.IRobot;
import biblioteket.roborally.actors.Actor;
import biblioteket.roborally.actors.Robot;
import biblioteket.roborally.board.DirVector;
import biblioteket.roborally.board.Direction;
import biblioteket.roborally.elements.ArchiveMarkerElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlagTest {
    private IActor player;

    @BeforeEach
    void setUp() {
        player = new Actor(null, null, null, null);
        player.setRobot(new Robot(new ArchiveMarkerElement(1)));
    }

    @Test
    void interactingWithCorrectFlagRegistersFlagTest() {
        FlagElement flag = new FlagElement(1);
        flag.interact(player);

        assertEquals(1, player.getNumberOfVisitedFlags());
    }

    @Test
    void cantPickupFlag2BeforeFlag1Test() {
        FlagElement flag1 = new FlagElement(1);
        FlagElement flag2 = new FlagElement(2);

        flag2.interact(player);
        assertEquals(0, player.getNumberOfVisitedFlags());

        flag1.interact(player);
        assertEquals(1, player.getNumberOfVisitedFlags());

        flag2.interact(player);
        assertEquals(2, player.getNumberOfVisitedFlags());

    }

    @Test
    void touchingFlagUpdatesArchiveMarkerTest() {
        FlagElement flag = new FlagElement(1);
        IRobot robot = player.getRobot();
        DirVector newPosition = new DirVector(5, 5, Direction.NORTH);

        robot.setPosition(newPosition);
        flag.interact(player);

        assertEquals(newPosition.getX(), robot.getArchiveMarker().getPosition().getX());
        assertEquals(newPosition.getY(), robot.getArchiveMarker().getPosition().getY());
    }
}
