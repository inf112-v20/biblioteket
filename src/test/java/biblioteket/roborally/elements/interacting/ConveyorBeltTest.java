package biblioteket.roborally.elements.interacting;

import biblioteket.roborally.TestRunner;
import biblioteket.roborally.actors.*;
import biblioteket.roborally.board.Board;
import biblioteket.roborally.board.DirVector;
import biblioteket.roborally.board.Direction;
import biblioteket.roborally.board.IBoard;
import biblioteket.roborally.elements.ArchiveMarkerElement;
import biblioteket.roborally.elements.interacting.conveyorbelts.ConveyorBeltElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(TestRunner.class)
public class ConveyorBeltTest {
    private static IActor player;
    private ArchiveMarkerElement archiveMarker;

    @BeforeAll
    private static void setup() {
        List<IActor> players = new ArrayList<>();
        IBoard board = new Board("assets/DizzyDash.tmx", players);
        player = new Actor(board, null, null, new RobotRenderer(null, null, null));
        players.add(player);
    }

    @BeforeEach
    void setUp() {
        archiveMarker = new ArchiveMarkerElement(1);
        archiveMarker.setPosition(new DirVector(1, 1, null));

    }

    @Test
    void conveyorBeltPushesRobotInCorrectDirectionTest() {
        Direction[] directions = new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST};
        for (Direction direction : directions) {
            IRobot robot = new Robot(archiveMarker);
            player.setRobot(robot);
            DirVector position = robot.getPosition();

            ConveyorBeltElement conveyorBelt = new ConveyorBeltElement(direction);
            DirVector initVector = new DirVector(position.getX(), position.getY(), position.getDirection());

            conveyorBelt.interact(player);
            DirVector postInteractionPosition = player.getRobot().getPosition();
            DirVector vectorInDirection = initVector.dirVectorInDirection(direction);

            assertEquals(vectorInDirection.getX(), postInteractionPosition.getX());
            assertEquals(vectorInDirection.getY(), postInteractionPosition.getY());


        }
    }
}
