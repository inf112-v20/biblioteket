package biblioteket.roborally.actors;

import biblioteket.roborally.Direction;
import biblioteket.roborally.GameBoard;
import biblioteket.roborally.grid.Grid;
import biblioteket.roborally.grid.IGrid;
import biblioteket.roborally.grid.IPosition;
import biblioteket.roborally.grid.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RobotTest {
    Robot<Integer> robot;
    int width = 5;
    int height = 5;
    GameBoard board;
    IGrid grid;
    IPlayer player;

    @BeforeEach
    void setUp() {
        IPosition<Integer> pos = new Position<>(1, 1);
        Direction direction = Direction.NORTH;
        board = new GameBoard(width, height);
        player = new Player();
        robot = new Robot(pos, pos, direction, board);
        robot.setPlayer(player);
    }

    @Test
    void removeDamageTokens() {
        int removedDamageTokens = 2;
        int originalNumberOfDamageTokens = robot.getNumberOfDamageTokens();
        robot.removeDamageTokens(removedDamageTokens);
        int newNumberOfDamageTokens = originalNumberOfDamageTokens - removedDamageTokens;
        assertEquals(newNumberOfDamageTokens, robot.getNumberOfDamageTokens());
    }

    @Test
    void addDamageTokens() {
        int addedDamageTokens = 2;
        int originalNumberOfDamageTokens = robot.getNumberOfDamageTokens();
        robot.addDamageTokens(addedDamageTokens);
        int newNumberOfDamageTokens = originalNumberOfDamageTokens + addedDamageTokens;
        assertEquals(newNumberOfDamageTokens, robot.getNumberOfDamageTokens());
    }

    @Test
    void removeAllDamageTokens() {
        robot.removeAllDamageTokens();
        assertEquals(0, robot.getNumberOfDamageTokens());
    }

    @Test
    void isDestroyed() {
        robot.addDamageTokens(10);
        assertTrue(robot.isDestroyed());
    }

    @Test
    void turnLeftFromNorth() {
        robot.setDirection(Direction.NORTH);
        robot.turnLeft();
        assertEquals(Direction.WEST, robot.getDirection());
    }

    @Test
    void turnLeftFromSouth() {
        robot.setDirection(Direction.SOUTH);
        robot.turnLeft();
        assertEquals(Direction.EAST, robot.getDirection());
    }

    @Test
    void turnLeftFromEast() {
        robot.setDirection(Direction.EAST);
        robot.turnLeft();
        assertEquals(Direction.NORTH, robot.getDirection());
    }

    @Test
    void turnLeftFromWest() {

        robot.setDirection(Direction.WEST);
        robot.turnLeft();
        assertEquals(Direction.SOUTH, robot.getDirection());
    }

    @Test
    void turnRightFromNorth() {
        robot.setDirection(Direction.NORTH);
        robot.turnRight();
        assertEquals(Direction.EAST, robot.getDirection());
    }

    @Test
    void turnRightFromSouth() {
        robot.setDirection(Direction.SOUTH);
        robot.turnRight();
        assertEquals(Direction.WEST, robot.getDirection());
    }

    @Test
    void turnRightFromEast() {
        robot.setDirection(Direction.EAST);
        robot.turnRight();
        assertEquals(Direction.SOUTH, robot.getDirection());
    }

    @Test
    void turnRightFromWest() {
        robot.setDirection(Direction.WEST);
        robot.turnRight();
        assertEquals(Direction.NORTH, robot.getDirection());
    }

    @Test
    void moveForwardTowardsNorthWhenThereIsNoObstacle() {
        robot.setDirection(Direction.NORTH);
        IPosition<Integer> position = robot.getPosition();
        IPosition<Integer> positionInDirection = grid.positionInDirection(position, Direction.NORTH);
        robot.moveForward();
        assertEquals(positionInDirection, robot.getPosition());
    }

    @Test
    void moveForwardTowardsSouthWhenThereIsNoObstacle() {
        robot.setDirection(Direction.SOUTH);
        IPosition<Integer> position = robot.getPosition();
        IPosition<Integer> positionInDirection = grid.positionInDirection(position, Direction.SOUTH);
        robot.moveForward();
        assertEquals(positionInDirection, robot.getPosition());
    }

    @Test
    void moveForwardTowardsEastWhenThereIsNoObstacle() {
        robot.setDirection(Direction.EAST);
        IPosition<Integer> position = robot.getPosition();
        IPosition<Integer> positionInDirection = grid.positionInDirection(position, Direction.EAST);
        robot.moveForward();
        assertEquals(positionInDirection, robot.getPosition());
    }

    @Test
    void moveForwardTowardsWestWhenThereIsNoObstacle() {
        robot.setDirection(Direction.WEST);
        IPosition<Integer> position = robot.getPosition();
        IPosition<Integer> positionInDirection = grid.positionInDirection(position, Direction.WEST);
        robot.moveForward();
        assertEquals(positionInDirection, robot.getPosition());
    }

    @Test
    void moveBackwardAllDirectionsWhenThereIsNoObstacle() {
    }

}