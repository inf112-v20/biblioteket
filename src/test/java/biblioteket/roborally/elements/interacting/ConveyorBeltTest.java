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

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(TestRunner.class)
public class ConveyorBeltTest {
    private static IBoard board;
    private IPlayer player;
    private ArchiveMarkerElement archiveMarker;

    @BeforeAll
    private static void setup(){
        board = new Board("assets/DizzyDash.tmx");
    }

    @BeforeEach
    void setUp() {
        player = new Player(board, null, null, new RobotRenderer(null));
        archiveMarker = new ArchiveMarkerElement(1);
        archiveMarker.setX(1);
        archiveMarker.setY(1);

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
