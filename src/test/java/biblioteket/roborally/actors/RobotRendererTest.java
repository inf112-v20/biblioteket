package biblioteket.roborally.actors;

import biblioteket.roborally.TestRunner;
import biblioteket.roborally.board.Board;
import biblioteket.roborally.board.DirVector;
import biblioteket.roborally.board.Direction;
import biblioteket.roborally.board.IBoard;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(TestRunner.class)
public class RobotRendererTest {
    private static RobotRenderer emptyRenderer;

    @BeforeAll
    static void setup(){
        emptyRenderer = new RobotRenderer(null, null, null);
    }

    @Test
    void robotStepTest(){
        DirVector oldPosition = new DirVector(0,0, Direction.NORTH);
        DirVector newPosition = new DirVector(1,1, Direction.SOUTH);
        Direction direction = Direction.WEST;
        int delay = 100;
        TiledMapTileLayer.Cell playerCell = new TiledMapTileLayer.Cell();

        RobotRenderer.RobotStep robotStep = new RobotRenderer.RobotStep(oldPosition, newPosition, direction, delay, playerCell, true);
        assertEquals(robotStep.getOldPosition(), oldPosition);
        assertEquals(robotStep.getRotation(), 1);       // PlayerCell rotation set to 1 is rotated to the left (west)
        assertEquals(robotStep.getPlayerCell(), playerCell);
        assertEquals(robotStep.isDebug(), true);
    }

    @Test
    void requestRenderingTest(){
        emptyRenderer.requestRendering(null, null, null, 0, null, true);
        assertTrue(emptyRenderer.isRequestingRendering());
    }

    @Test
    void renderStepTest(){
        Random random = new Random();
        List<IActor> players = new ArrayList<>();
        IBoard board = new Board("assets/EmptyTestMap.tmx", new ArrayList<IActor>());
        TiledMapTileLayer playerLayer = board.getPlayerLayer();
        RobotRenderer renderer = new RobotRenderer(playerLayer, players, null);
        TiledMapTileLayer.Cell playerCell = new TiledMapTileLayer.Cell();
        DirVector oldPosition = new DirVector(0,0,null);
        Direction direction = Direction.SOUTH;

        int x = random.nextInt(board.getWidth() - 1) + 1;
        int y = random.nextInt(board.getHeight() - 1) + 1;
        DirVector newPosition = new DirVector(x,y,null);

        renderer.requestRendering(oldPosition,newPosition,direction,0,playerCell,true);
        renderer.renderStep();

        assertEquals(playerCell, playerLayer.getCell(x,y));
        assertEquals(2, playerCell.getRotation());
    }

    @Test
    void removePlayerTest(){
        Random random = new Random();
        List<IActor> players = new ArrayList<>();
        IBoard board = new Board("assets/EmptyTestMap.tmx", new ArrayList<IActor>());
        TiledMapTileLayer playerLayer = board.getPlayerLayer();
        RobotRenderer renderer = new RobotRenderer(playerLayer, players, null);
        TiledMapTileLayer.Cell playerCell = new TiledMapTileLayer.Cell();
        DirVector oldPosition = new DirVector(0,0,null);
        Direction direction = Direction.SOUTH;

        int x = random.nextInt(board.getWidth() - 1) + 1;
        int y = random.nextInt(board.getHeight() - 1) + 1;
        DirVector newPosition = new DirVector(x,y,null);

        renderer.requestRendering(oldPosition,newPosition,direction,0,playerCell,true);
        renderer.renderStep();

        renderer.removePlayer(newPosition, playerCell, true);
        renderer.renderStep();

        // Check that board is empty
        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getHeight(); j++) {
                assertEquals(null, playerLayer.getCell(i,j));
            }
        }
    }
}
