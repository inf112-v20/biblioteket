package biblioteket.roborally.elements.walls;

import biblioteket.roborally.TestRunner;
import biblioteket.roborally.actors.*;
import biblioteket.roborally.board.Board;
import biblioteket.roborally.board.DirVector;
import biblioteket.roborally.board.Direction;
import biblioteket.roborally.board.IBoard;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(TestRunner.class)
public class WallTest {
    private static IBoard board;
    private static IActor actor;

    @BeforeAll
    static void setUp(){
        board = new Board("assets/TestingMap.tmx", new ArrayList<IActor>());
        actor = new Player(board, new TiledMapTileLayer.Cell(), null, new RobotRenderer(null, null, null));
        IRobot robot = new Robot(board.getArchiveMarker(2));    //Position with wall to the north
        actor.setRobot(robot);
    }

    @Test
    void enterCellWithWallBlockingTest(){
        // Try to move into cell with wall blocking entry the north
        assertFalse(board.canMove(actor.getRobot().getPosition(), Direction.NORTH));
    }

    @Test
    void exitCellWithWallBlockingTest(){
        DirVector position = actor.getRobot().getPosition();
        position.forward(Direction.NORTH);
        // Set robot at position blocking exit to the south
        actor.getRobot().setPosition(position);
        // Try to move with wall blocking exit to the south
        assertFalse(board.canMove(actor.getRobot().getPosition(), Direction.SOUTH));
    }

}
