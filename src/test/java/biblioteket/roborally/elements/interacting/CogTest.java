package biblioteket.roborally.elements.interacting;

import biblioteket.roborally.TestRunner;
import biblioteket.roborally.actors.Actor;
import biblioteket.roborally.actors.IActor;
import biblioteket.roborally.actors.Robot;
import biblioteket.roborally.actors.RobotRenderer;
import biblioteket.roborally.board.Board;
import biblioteket.roborally.board.Direction;
import biblioteket.roborally.board.IBoard;
import biblioteket.roborally.elements.ArchiveMarkerElement;
import biblioteket.roborally.elements.interacting.cogs.CogElement;
import biblioteket.roborally.elements.interacting.cogs.LeftRotatingCogElement;
import biblioteket.roborally.elements.interacting.cogs.RightRotatingCogElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(TestRunner.class)
public class CogTest {
    private static IBoard board;
    private static RobotRenderer robotRenderer;
    private IActor player;

    @BeforeAll
    private static void setup() {
        board = new Board("assets/RiskyExchange.tmx", null);
        robotRenderer = new RobotRenderer(null, null, null);
    }

    @BeforeEach
    void setUp() {
        player = new Actor(board, null, null, robotRenderer);
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
