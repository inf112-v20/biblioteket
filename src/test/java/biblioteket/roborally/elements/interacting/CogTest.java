package biblioteket.roborally.elements.interacting;

import biblioteket.roborally.actors.IPlayer;
import biblioteket.roborally.actors.Player;
import biblioteket.roborally.actors.Robot;
import biblioteket.roborally.board.Direction;
import biblioteket.roborally.elements.ArchiveMarkerElement;
import biblioteket.roborally.elements.interacting.cogs.CogElement;
import biblioteket.roborally.elements.interacting.cogs.LeftRotatingCogElement;
import biblioteket.roborally.elements.interacting.cogs.RightRotatingCogElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CogTest {
    private IPlayer player;

    @BeforeEach
    void setUp() {
        player = new Player(null);
        player.setRobot(new Robot(new ArchiveMarkerElement(1)));
    }

    @Test
    void leftRotatingCogElementRotatesRobotToTheLeftTest() {
        CogElement cog = new LeftRotatingCogElement();
        Direction initialDirection = player.getRobot().getDirection();

        cog.interact(player);
        Direction postInteractionDirection = player.getRobot().getDirection();

        assertEquals(initialDirection.left(), postInteractionDirection);
    }

    @Test
    void rightRotatingCogElementRotatesRobotToTheRightTest() {
        CogElement cog = new RightRotatingCogElement();
        Direction initialDirection = player.getRobot().getDirection();

        cog.interact(player);
        Direction postInteractionDirection = player.getRobot().getDirection();

        assertEquals(initialDirection.right(), postInteractionDirection);
    }
}
