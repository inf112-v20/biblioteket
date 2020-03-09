package biblioteket.roborally.actors;

import biblioteket.roborally.board.DirVector;
import biblioteket.roborally.board.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RobotTest {
    Robot robot;

    @BeforeEach
    void setUp() {
        DirVector position = new DirVector(0,0, Direction.NORTH);
        robot = new Robot(position);
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
        robot.removeDamageTokens(robot.getNumberOfDamageTokens());
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
    void moveForwardWhenFacingNorth() {
        robot.setDirection(Direction.NORTH);
        robot.moveForward();
        assertEquals(robot.getPosition(), new DirVector(0, 1, Direction.NORTH));
    }

    @Test
    void moveForwardWhenFacingSouth() {
        robot.setDirection(Direction.SOUTH);
        robot.moveForward();
        assertEquals(robot.getPosition(), new DirVector(0, -1, Direction.SOUTH));
    }

    @Test
    void moveForwardWhenFacingEast() {
        robot.setDirection(Direction.EAST);
        robot.moveForward();
        assertEquals(robot.getPosition(), new DirVector(1, 0, Direction.EAST));
    }

    @Test
    void moveForwardWest() {
        robot.setDirection(Direction.WEST);
        robot.moveForward();
        assertEquals(robot.getPosition(), new DirVector(-1, 0, Direction.WEST));
    }

    @Test
    void moveBackwardWhenFacingNorth() {
        robot.setDirection(Direction.NORTH);
        robot.moveBackward();
        assertEquals(robot.getPosition(), new DirVector(0, -1, Direction.NORTH));
    }

    @Test
    void moveBackwardWhenFacingSouth() {
        robot.setDirection(Direction.SOUTH);
        robot.moveBackward();
        assertEquals(robot.getPosition(), new DirVector(0, 1, Direction.SOUTH));
    }

    @Test
    void moveBackwardWhenFacingEast() {
        robot.setDirection(Direction.EAST);
        robot.moveBackward();
        assertEquals(robot.getPosition(), new DirVector(-1, 0, Direction.EAST));
    }

    @Test
    void moveBackwardWhenFacingWest() {
        robot.setDirection(Direction.WEST);
        robot.moveBackward();
        assertEquals(robot.getPosition(), new DirVector(1, 0, Direction.WEST));
    }
}