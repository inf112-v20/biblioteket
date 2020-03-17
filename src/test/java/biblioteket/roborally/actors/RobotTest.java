package biblioteket.roborally.actors;

import biblioteket.roborally.board.DirVector;
import biblioteket.roborally.board.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RobotTest {
    IPlayer player;
    IRobot robot;

    @BeforeEach
    void setUp() {
        DirVector position = new DirVector(0, 0, Direction.NORTH);
        robot = new Robot(position);
        player = new Player(null);
        player.setRobot(robot);
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

    // @Test
    // void moveForwardTowardsNorthWhenItIsOutOfBoundaries() {
    //     int damageBeforeDeath = 2;
    //     robot.setDirection(Direction.NORTH);
    //     robot.setPos(0, 0);
    //     robot.addDamageTokens(damageBeforeDeath);
    //     assertEquals(fullLife, robot.getPlayer().getLives()); // TODO maybe remove this and the next.
    //     assertEquals(damageBeforeDeath, robot.getNumberOfDamageTokens());
    //     robot.moveForward();
    //     assertEquals(fullLife - 1, robot.getPlayer().getLives());
    //     assertEquals(robot.getArchiveMarker(), robot.getPosition());
    //     assertEquals(0, robot.getNumberOfDamageTokens());
    // }

    // @Test
    // void moveForwardTowardSouthWhenARobotWhoCanBePushedIsInTheWay() {
    //     secondRobot = new Robot(pos1x2y, secondArchiveMarker, Direction.NORTH, board);
    //     secondRobot.setPlayer(secondPlayer);

        // robot.setDirection(Direction.SOUTH);
        // secondRobot.setDirection(Direction.NORTH);
        // IPosition firstRobotNewPosition = grid.positionInDirection(robot.getPosition(), robot.getDirection());
        // assertEquals(firstRobotNewPosition, secondRobot.getPosition()); //makes sure we check for right event
        // IPosition secondRobotNewPosition = grid.positionInDirection(secondRobot.getPosition(), robot.getDirection());

        // robot.moveForward();

        // assertEquals(Direction.NORTH, secondRobot.getDirection()); //Makes sure nothing weird happens, might be removed.
        // assertEquals(firstRobotNewPosition, robot.getPosition());
        // assertEquals(secondRobotNewPosition, secondRobot.getPosition());

        // //Makes sure there is one robot at each position
        // int numberOfRobotsOnPosition1 = 0;
        // List<IElement> elementsInPos = firstRobotNewPosition.getContents();
        // for (IElement element : elementsInPos)
        //     if (element instanceof IRobot)
        //         numberOfRobotsOnPosition1++;
        // assertEquals(1, numberOfRobotsOnPosition1);


        // int numberOfRobotsOnPosition2 = 0;
        // List<IElement> elementsInPos2 = secondRobotNewPosition.getContents();
        // for (IElement element : elementsInPos2)
        //     if (element instanceof IRobot)
        //         numberOfRobotsOnPosition2++;
        // assertEquals(1, numberOfRobotsOnPosition2);

    // }
}
