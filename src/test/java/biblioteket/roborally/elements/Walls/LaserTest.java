package biblioteket.roborally.elements.Walls;

import biblioteket.roborally.TestRunner;
import biblioteket.roborally.actors.*;
import biblioteket.roborally.board.Board;
import biblioteket.roborally.board.DirVector;
import biblioteket.roborally.board.Direction;
import biblioteket.roborally.board.IBoard;
import biblioteket.roborally.elements.walls.Laser;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(TestRunner.class)
public class LaserTest {
    private Laser laser;
    private IBoard board;
    private IActor actor;
    private IActor secondActor;
    private List<IActor> players;

    @BeforeEach
    void setup(){
        laser = new Laser();
        board = new Board("assets/DizzyDash.tmx", new ArrayList<IActor>());
        RobotRenderer robotRenderer = new RobotRenderer(null, null, null);
        players = new ArrayList<>();
        actor = new Player(board, new TiledMapTileLayer.Cell(), null, robotRenderer);
        secondActor = new Player(board, new TiledMapTileLayer.Cell(), null, robotRenderer);

        IRobot robot = new Robot(board.getArchiveMarker(1));    //Position with wall to the north
        IRobot secondRobot = new Robot(board.getArchiveMarker(2));
        actor.setRobot(robot);
        secondActor.setRobot(secondRobot);

        players.add(actor);
        players.add(secondActor);
    }

    @Test
    void robotHitByLaserTakesDamageTest(){
        DirVector vector = new DirVector(0,0, Direction.EAST);
        laser.fireLaser(board, players, vector);
        assertEquals(1, actor.getRobot().getNumberOfDamageTokens());
    }

    @Test
    void wallBlocksLaserTest(){
        // move first robot out of way
        DirVector position = new DirVector(1,1,Direction.NORTH);
        actor.getRobot().setPosition(position);
        // Second robot is in lasers path, but blocked by wall
        DirVector vector = new DirVector(0,0, Direction.EAST);
        laser.fireLaser(board, players, vector);
        assertEquals(0, actor.getRobot().getNumberOfDamageTokens());
    }

    @Test
    void robotBlocksLaserTest(){
        // Place second robot in front of first robot, blocking lasers path
        DirVector position = new DirVector(4, 0, Direction.NORTH);
        secondActor.getRobot().setPosition(position);
        // Fire laser towards robots
        DirVector vector = new DirVector(0,0, Direction.EAST);
        laser.fireLaser(board, players, vector);
        assertEquals(0, actor.getRobot().getNumberOfDamageTokens());
        assertEquals(1, secondActor.getRobot().getNumberOfDamageTokens());
    }
}
