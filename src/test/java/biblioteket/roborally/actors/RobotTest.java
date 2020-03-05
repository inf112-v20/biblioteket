package biblioteket.roborally.actors;

import biblioteket.roborally.grid.Direction;
import biblioteket.roborally.grid.IPosition;
import biblioteket.roborally.grid.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RobotTest {
    Robot<Integer> robot;

    @BeforeEach
    void setUp() {
        IPosition<Integer> pos = new Position<>(0, 0);
        Direction direction = Direction.NORTH;
        robot = new Robot<>(pos, pos, direction);
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

    @Disabled
    @Test
    void moveForwardWhenFacingNorth() {
        robot.setDirection(Direction.NORTH);
        IPosition<Integer> position = robot.getPosition();
        // IPosition<Integer> positionInNorth = position.positionInDirection(Direction.NORTH);
        robot.moveForward();
        // assertEquals(positionInNorth, robot.getPosition());
    }

    @Disabled
    @Test
    void moveForwardWhenFacingSouth() {
        robot.setDirection(Direction.SOUTH);
        IPosition<Integer> position = robot.getPosition();
        // IPosition<Integer> positionInSouth = position.positionInDirection(Direction.SOUTH);
        robot.moveForward();
        // assertEquals(positionInSouth, robot.getPosition());
    }

    @Disabled
    @Test
    void moveForwardWhenFacingEast() {
        robot.setDirection(Direction.EAST);
        IPosition<Integer> position = robot.getPosition();
        // IPosition<Integer> positionInEast = position.positionInDirection(Direction.EAST);
        robot.moveForward();
        // assertEquals(positionInEast, robot.getPosition());
    }

    @Disabled
    @Test
    void moveForwardWest() {
        robot.setDirection(Direction.WEST);
        IPosition<Integer> position = robot.getPosition();
        // IPosition<Integer> positionInWest = position.positionInDirection(Direction.WEST);
        robot.moveForward();
        // assertEquals(positionInWest, robot.getPosition());
    }

    @Disabled
    @Test
    void moveBackwardWhenFacingNorth() {
        robot.setDirection(Direction.NORTH);
        IPosition<Integer> position = robot.getPosition();
        // IPosition<Integer> positionOppositeOfNorth = position.positionInDirection(Direction.NORTH.oppositeDirection());
        robot.moveBackward();
        // assertEquals(positionOppositeOfNorth, robot.getPosition());
    }

    @Disabled
    @Test
    void moveBackwardWhenFacingSouth() {
        robot.setDirection(Direction.SOUTH);
        IPosition<Integer> position = robot.getPosition();
        // IPosition<Integer> positionOppositeOfSouth = position.positionInDirection(Direction.SOUTH.oppositeDirection());
        robot.moveBackward();
        // assertEquals(positionOppositeOfSouth, robot.getPosition());
    }

    @Disabled
    @Test
    void moveBackwardWhenFacingEast() {
        robot.setDirection(Direction.SOUTH);
        IPosition<Integer> position = robot.getPosition();
        // IPosition<Integer> positionOppositeOfEast = position.positionInDirection(Direction.SOUTH.oppositeDirection());
        robot.moveBackward();
        // assertEquals(positionOppositeOfEast, robot.getPosition());
    }

    @Disabled
    @Test
    void moveBackwardWhenFacingWest() {
        robot.setDirection(Direction.WEST);
        IPosition<Integer> position = robot.getPosition();
        // IPosition<Integer> positionOppositeOfWest = position.positionInDirection(Direction.WEST.oppositeDirection());
        robot.moveBackward();
        // assertEquals(positionOppositeOfWest, robot.getPosition());
    }
}