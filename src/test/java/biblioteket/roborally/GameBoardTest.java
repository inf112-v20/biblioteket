package biblioteket.roborally;

import biblioteket.roborally.actors.ImmovableElement;
import biblioteket.roborally.grid.IGrid;
import biblioteket.roborally.grid.IPosition;
import biblioteket.roborally.grid.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class GameBoardTest {
    GameBoard gameBoard;
    int width;
    int height;

    Random random;

    @BeforeEach
    void setUp(){
        this.random = new Random();
        // Random width and height between 10 and 19
        this.width = random.nextInt(10) + 10;
        this.height = random.nextInt(10) + 10;

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
    @Test
    void firstCollisionInDirectionTest(){
        // Run test 100 times
        for (int i = 0; i < 100; i++) {
            this.gameBoard = new GameBoard(this.width, this.height);

            // Random direction
            Direction direction = Direction.FOUR_DIRECTIONS.get(random.nextInt(4));
            // x,y coordinates not touching edge of grid
            int x = random.nextInt(width - 2) + 1;
            int y = random.nextInt(height - 2) + 1;
            IGrid<IElement> grid = gameBoard.getGrid();

            IPosition<IElement> initialPosition = grid.getPosition(x,y);
            IPosition<IElement> positionContainingImmovableElement;

            if(direction == Direction.NORTH){
                int y1 = random.nextInt(height - y - 1) + y + 1;
                positionContainingImmovableElement = grid.getPosition(x,y1);
            } else if (direction == Direction.SOUTH){
                int y1 = random.nextInt(y);
                positionContainingImmovableElement = grid.getPosition(x,y1);
            } else if (direction == Direction.WEST){
                int x1 = random.nextInt(x);
                positionContainingImmovableElement = grid.getPosition(x1,y);
            } else {
                int x1 = random.nextInt(width - x - 1) + x + 1;
                positionContainingImmovableElement = grid.getPosition(x1,y);
            }

            gameBoard.placeElement(positionContainingImmovableElement, new ImmovableElement());
            System.out.println("Immovable element in: " + positionContainingImmovableElement.getX() + "," + positionContainingImmovableElement.getY());
            System.out.println();
            IPosition<IElement> firstCollisionInDirection = gameBoard.firstCollisionInDirection(initialPosition,direction);

            System.out.print("Position containing immovable element: ");
            System.out.println(positionContainingImmovableElement.getX() + "," + positionContainingImmovableElement.getY());
            System.out.print("Position for first collision: ");
            System.out.println(firstCollisionInDirection.getX() + "," + firstCollisionInDirection.getY());
            System.out.println();

            assertTrue(positionContainingImmovableElement.equals(firstCollisionInDirection));
        }
    }

    @Test
    void firstCollisionInDirectionNoCollisionReturnsNullTest(){
        // Random direction
        Direction direction = Direction.FOUR_DIRECTIONS.get(random.nextInt(4));

        int x = this.width / 2;
        int y = this.height / 2;
        IPosition<IElement> initPosition = gameBoard.getPosition(x,y);
        IPosition<IElement> firstCollision = gameBoard.firstCollisionInDirection(initPosition, direction);

        assertEquals(firstCollision, null);
    }

}
