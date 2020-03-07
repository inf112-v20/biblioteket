package biblioteket.roborally;

import biblioteket.roborally.elements.IElement;
import biblioteket.roborally.grid.Direction;
import biblioteket.roborally.grid.GameBoard;
import biblioteket.roborally.grid.IPosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class GameBoardTest {
    GameBoard gameBoard;

    Random random = new Random();

    @BeforeEach
    void setUp(){
        // Random width and height between 10 and 19
        int width = random.nextInt(10) + 10;
        int height = random.nextInt(10) + 10;

        this.gameBoard = new GameBoard(width,height);
    }

//    @Test
//    void containsImmovableObjectTest(){
//        IPosition<IElement> initialPosition = randomPositionNotOnEdgeOfMap(gameBoard);
//
//        //Choose random direction
//        Direction direction = Direction.FOUR_DIRECTIONS.get(random.nextInt(4));
//
//        //Place immovable element in direction
//        IPosition<IElement> positionInDirection = gameBoard.positionInDirection(initialPosition,direction);
//        IElement immovableElement = new ImmovableElement();
//        gameBoard.placeElement(positionInDirection, immovableElement);
//
//        //Check if there is immovable element in direction
//        assertTrue(gameBoard.containsImmovableObject(initialPosition,direction));
//    }

//    @Test
//    void firstCollisionInDirectionTest(){
//        // Random width and height between 10 and 19
//        int width = random.nextInt(10) + 10;
//        int height = random.nextInt(10) + 10;
//
//
//        // Run test 100 times
//        for (int i = 0; i < 100; i++) {
//            this.gameBoard = new GameBoard(width, height);
//
//            // Random direction
//            Direction direction = Direction.FOUR_DIRECTIONS.get(random.nextInt(4));
//            // x,y coordinates not touching edge of grid
//            int x = random.nextInt(width - 2) + 1;
//            int y = random.nextInt(height - 2) + 1;
//
//            IPosition<IElement> initialPosition = gameBoard.getPosition(x,y);
//            IPosition<IElement> positionContainingImmovableElement;
//
//            if(direction == Direction.NORTH){
//                int y1 = random.nextInt(y);
//                positionContainingImmovableElement = gameBoard.getPosition(x,y1);
//            } else if (direction == Direction.SOUTH){
//                int y1 = random.nextInt(height - y - 1) + y + 1;
//                positionContainingImmovableElement = gameBoard.getPosition(x,y1);
//            } else if (direction == Direction.WEST){
//                int x1 = random.nextInt(x);
//                positionContainingImmovableElement = gameBoard.getPosition(x1,y);
//            } else {
//                int x1 = random.nextInt(width - x - 1) + x + 1;
//                positionContainingImmovableElement = gameBoard.getPosition(x1,y);
//            }
//
//            gameBoard.placeElement(positionContainingImmovableElement, new ImmovableElement());
//            System.out.println("Immovable element in: " + positionContainingImmovableElement.getX() + "," + positionContainingImmovableElement.getY());
//            System.out.println();
//            IPosition<IElement> firstCollisionInDirection = gameBoard.firstCollisionInDirection(initialPosition,direction);
//
//            System.out.print("Position containing immovable element: ");
//            System.out.println(positionContainingImmovableElement.getX() + "," + positionContainingImmovableElement.getY());
//            System.out.print("Position for first collision: ");
//            System.out.println(firstCollisionInDirection.getX() + "," + firstCollisionInDirection.getY());
//            System.out.println();
//
//            assertTrue(positionContainingImmovableElement.equals(firstCollisionInDirection));
//        }
//    }

//    @Test
//    void firstCollisionInDirectionStoppedByWallOnExitTest(){
//        IPosition<IElement> from = gameBoard.getPosition(0,0);
//        IPosition<IElement> positionWithWallStoppingExit = gameBoard.getPosition(0,5);
//        IPosition<IElement> southOfPositionWithWallStoppingExit = gameBoard.positionInDirection(positionWithWallStoppingExit, Direction.SOUTH);
//        gameBoard.setWall(positionWithWallStoppingExit,null,Direction.SOUTH);
//
//
//        IPosition<IElement> southernCollision = gameBoard.firstCollisionInDirection(from,Direction.SOUTH);
//        assertEquals(southOfPositionWithWallStoppingExit, southernCollision);
//    }
//
//    @Test
//    void FirstCollisionInDirectionStoppedByWallOnEntryTest(){
//        IPosition<IElement> from = gameBoard.getPosition(0,0);
//        IPosition<IElement> positionWithWallStoppingEntry = gameBoard.getPosition(0,5);
//        gameBoard.setWall(positionWithWallStoppingEntry,null,Direction.NORTH);
//
//        IPosition<IElement> southernCollision = gameBoard.firstCollisionInDirection(from,Direction.SOUTH);
//        assertEquals(positionWithWallStoppingEntry, southernCollision);
//    }

    @Test
    void firstCollisionInDirectionNoCollisionReturnsNullTest(){
        // Random direction
        Direction direction = Direction.FOUR_DIRECTIONS.get(random.nextInt(4));

        int x = gameBoard.getWidth() / 2;
        int y = gameBoard.getHeight() / 2;
        IPosition<IElement> initPosition = gameBoard.getPosition(x,y);
        IPosition<IElement> firstCollision = gameBoard.firstCollisionInDirection(initPosition, direction);

        assertEquals(null, firstCollision);
    }

    @Test
    void canMoveReturnsTrueIfNothingIsBlockingMoveTest(){
        IPosition<IElement> from = randomPositionNotOnEdgeOfMap(gameBoard);
        for(Direction direction : Direction.FOUR_DIRECTIONS){
            assertTrue(gameBoard.canMove(from, direction));
        }
    }

//    @Test
//    void canMoveBlockedByImmovableObjectTest(){
//        IPosition<IElement> from = randomPositionNotOnEdgeOfMap(gameBoard);
//        IPosition<IElement> to = gameBoard.positionInDirection(from, Direction.NORTH);
//        gameBoard.placeElement(to, new ImmovableElement());
//
//        boolean canMoveToPositionContainingImmovableElement = gameBoard.canMove(from, Direction.NORTH);
//        assertFalse(canMoveToPositionContainingImmovableElement);
//    }

    @Test
    void canMoveHandlesIndexOutOfBoundsTest(){
        IPosition<IElement> positionInNorthWestCorner = gameBoard.getPosition(0,0);

        boolean canMoveOffGridNorth = gameBoard.canMove(positionInNorthWestCorner, Direction.NORTH);
        boolean canMoveOffGridWest = gameBoard.canMove(positionInNorthWestCorner, Direction.WEST);
        assertFalse(canMoveOffGridNorth && canMoveOffGridWest);
    }

//    @Test
//    void canMoveBlockedByWallExitingPositionTest(){
//        IPosition<IElement> position = randomPositionNotOnEdgeOfMap(gameBoard);
//        Direction direction = Direction.FOUR_DIRECTIONS.get(random.nextInt(4)); // Random direction
//        gameBoard.setWall(position, direction, null);
//
//        assertFalse(gameBoard.canMove(position,direction));
//
//        assertTrue(gameBoard.canMove(position, direction.direction90DegreesToTheLeft()));
//        assertTrue(gameBoard.canMove(position, direction.direction90DegreesToTheRight()));
//        assertTrue(gameBoard.canMove(position, direction.oppositeDirection()));
//
//    }

//    @Test
//    void canMoveBlockedByWallEnteringPositionTest(){
//        IPosition<IElement> from = randomPositionNotOnEdgeOfMap(gameBoard);
//        Direction direction = Direction.FOUR_DIRECTIONS.get(random.nextInt(4)); // Random direction
//        IPosition<IElement> to = gameBoard.positionInDirection(from, direction);
//        gameBoard.setWall(to, direction.oppositeDirection(), null);
//
//        assertFalse(gameBoard.canMove(from,direction));
//        assertTrue(gameBoard.canMove(from,direction.direction90DegreesToTheLeft()));
//        assertTrue(gameBoard.canMove(from,direction.direction90DegreesToTheRight()));
//        assertTrue(gameBoard.canMove(from,direction.oppositeDirection()));
//    }

    private IPosition<IElement> randomPositionNotOnEdgeOfMap(GameBoard gameBoard){
        int width = gameBoard.getWidth();
        int height = gameBoard.getHeight();

        int x = random.nextInt(width - 2) + 1;
        int y = random.nextInt(height - 2) + 1;

        return gameBoard.getPosition(x,y);
    }

}
