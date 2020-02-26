package biblioteket.roborally;

import biblioteket.roborally.actors.ImmovableElement;
import biblioteket.roborally.grid.IGrid;
import biblioteket.roborally.grid.IPosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class GameBoardTest {
    GameBoard gameBoard;
    int width;
    int height;

    Random random;

    @BeforeEach
    void setUp(){
        this.random = new Random();
        // Random width and height between 5 and 14
        this.width = random.nextInt(10) + 5;
        this.height = random.nextInt(10) + 5;

        this.gameBoard = new GameBoard(width,height);
    }

    @Test
    void containsImmovableObjectTest(){
        // x,y coordinates not touching edge of grid
        int x = random.nextInt(width - 2) + 1;
        int y = random.nextInt(height - 2) + 1;
        IGrid<IElement> grid = gameBoard.getGrid();

        IPosition<IElement> initialPosition = grid.getPosition(x,y);

        //Choose random direction
        Direction direction = Direction.FOUR_DIRECTIONS.get(random.nextInt(4));

        //Place immovable element in direction
        IPosition<IElement> positionInDirection = grid.positionInDirection(initialPosition,direction);
        IElement immovableElement = new ImmovableElement();
        gameBoard.placeElement(positionInDirection, immovableElement);

        //Check if there is immovable element in direction
        assertTrue(gameBoard.containsImmovableObject(initialPosition,direction));
    }

    //TODO
    @Disabled
    @Test
    void firstCollisionInDirectionTest(){
        fail();
    }
}
