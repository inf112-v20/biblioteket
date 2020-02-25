package biblioteket.roborally.actors;

import biblioteket.roborally.Directions;
import biblioteket.roborally.IPosition;
import biblioteket.roborally.Position;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RobotTest {
    Robot robot;

    @BeforeEach
    void setUp() {

        IPosition pos = new Position(0,0);
        Directions direction = Directions.NORTH;
        robot = new Robot(pos, pos, direction);
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

        robot.setNumberOfDamageTokens(10);
        assertTrue(robot.isDestroyed());
    }

    @Test
    void turnLeftFromNorth() {

        robot.setDirection(Directions.NORTH);
        robot.turnLeft();
        assertEquals(Directions.WEST, robot.getDirection());
    }
    @Test
    void turnLeftFromSouth() {

        robot.setDirection(Directions.SOUTH);
        robot.turnLeft();
        assertEquals(Directions.EAST, robot.getDirection());
    }
    @Test
    void turnLeftFromEast() {

        robot.setDirection(Directions.EAST);
        robot.turnLeft();
        assertEquals(Directions.NORTH, robot.getDirection());
    }
    @Test
    void turnLeftFromWest() {

        robot.setDirection(Directions.WEST);
        robot.turnLeft();
        assertEquals(Directions.SOUTH, robot.getDirection());
    }

    @Test
    void turnRightFromNorth() {

        robot.setDirection(Directions.NORTH);
        robot.turnRight();
        assertEquals(Directions.EAST, robot.getDirection());
    }

    @Test
    void turnRightFromSouth() {

        robot.setDirection(Directions.SOUTH);
        robot.turnRight();
        assertEquals(Directions.WEST, robot.getDirection());
    }

    @Test
    void turnRightFromEast() {

        robot.setDirection(Directions.EAST);
        robot.turnRight();
        assertEquals(Directions.SOUTH, robot.getDirection());
    }

    @Test
    void turnRightFromWest() {

        robot.setDirection(Directions.WEST);
        robot.turnRight();
        assertEquals(Directions.NORTH, robot.getDirection());
    }

    @Test
    void moveForwardWhenFacingNorth() {

        robot.setDirection(Directions.NORTH);
        IPosition position = robot.getPosition();
        IPosition positionInNorth = position.positionInDirection(Directions.NORTH);
        robot.moveForward();
        assertEquals(positionInNorth, robot.getPosition());
    }

    @Test
    void moveForwardWhenFacingSouth() {

        robot.setDirection(Directions.SOUTH);
        IPosition position = robot.getPosition();
        IPosition positionInSouth = position.positionInDirection(Directions.SOUTH);
        robot.moveForward();
        assertEquals(positionInSouth, robot.getPosition());
    }

    @Test
    void moveForwardWhenFacingEast() {

        robot.setDirection(Directions.EAST);
        IPosition position = robot.getPosition();
        IPosition positionInEast = position.positionInDirection(Directions.EAST);
        robot.moveForward();
        assertEquals(positionInEast, robot.getPosition());

    }

    @Test
    void moveForwardWest() {

        robot.setDirection(Directions.WEST);
        IPosition position = robot.getPosition();
        IPosition positionInWest = position.positionInDirection(Directions.WEST);
        robot.moveForward();
        assertEquals(positionInWest, robot.getPosition());

    }

    @Test
    void moveBackwardWhenFacingNorth() {

        robot.setDirection(Directions.NORTH);
        IPosition position = robot.getPosition();
        IPosition positionOppositeOfNorth = position.positionInDirection(Directions.NORTH.oppositeDirection());
        robot.moveBackward();
        assertEquals(positionOppositeOfNorth, robot.getPosition());
    }

    @Test
    void moveBackwardWhenFacingSouth() {

        robot.setDirection(Directions.SOUTH);
        IPosition position = robot.getPosition();
        IPosition positionOppositeOfSouth = position.positionInDirection(Directions.SOUTH.oppositeDirection());
        robot.moveBackward();
        assertEquals(positionOppositeOfSouth, robot.getPosition());
    }

    @Test
    void moveBackwardWhenFacingEast() {

        robot.setDirection(Directions.SOUTH);
        IPosition position = robot.getPosition();
        IPosition positionOppositeOfEast = position.positionInDirection(Directions.SOUTH.oppositeDirection());
        robot.moveBackward();
        assertEquals(positionOppositeOfEast, robot.getPosition());
    }

    @Test
    void moveBackwardWhenFacingWest(){

        robot.setDirection(Directions.WEST);
        IPosition position = robot.getPosition();
        IPosition positionOppositeOfWest = position.positionInDirection(Directions.WEST.oppositeDirection());
        robot.moveBackward();
        assertEquals(positionOppositeOfWest, robot.getPosition());
    }

}