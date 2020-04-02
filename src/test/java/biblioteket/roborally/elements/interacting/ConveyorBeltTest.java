package biblioteket.roborally.elements.interacting;

import biblioteket.roborally.TestRunner;
import biblioteket.roborally.actors.IPlayer;
import biblioteket.roborally.actors.Player;
import biblioteket.roborally.actors.Robot;
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
    private DirVector position;

    @BeforeAll
    static void setup(){
        board = new Board("assets/RiskyExchange.tmx");
    }

    @BeforeEach
    void setUp() {
        player = new Player(board);
        player.initializeRobotRenderer(null,null);
        player.setRobot(new Robot(new ArchiveMarkerElement(1)));
        position = player.getRobot().getPosition();
    }

    @Test
    void conveyorBeltPushesRobotInCorrectDirectionTest() {
        Direction[] directions = new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST};
        for (Direction direction : directions) {
            ConveyorBeltElement conveyorBelt = new ConveyorBeltElement(direction);
            DirVector initVector = new DirVector(position.getX(), position.getY(), position.getDirection());

            conveyorBelt.interact(player);
            DirVector postInteractionPosition = player.getRobot().getPosition();

            assertEquals(initVector.dirVectorInDirection(direction), postInteractionPosition);


        }
    }
}
