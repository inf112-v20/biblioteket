package biblioteket.roborally.actors;

import biblioteket.roborally.TestRunner;
import biblioteket.roborally.board.Board;
import biblioteket.roborally.board.DirVector;
import biblioteket.roborally.board.Direction;
import biblioteket.roborally.board.IBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(TestRunner.class)
class RobotTest {
    private IRobot robot;
    private IBoard board;

    @BeforeEach
    void setUp() {
        board = new Board("assets/TestingMap.tmx");
        robot = new Robot(board.getArchiveMarker(1));
        IPlayer player = new Player(null, null, null, null);
        player.setRobot(robot);
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
    void moveForwardNorth() {
        Direction direction = Direction.NORTH;
        robot.setDirection(direction);
        DirVector newLocation = robot.getPosition().dirVectorInDirection(direction);

        robot.pushRobotInDirection(robot.getDirection());

        assertEquals(robot.getPosition(), newLocation);
        assertEquals(direction, robot.getDirection());
    }

    @Test
    void moveForwardSouth() {
        Direction direction = Direction.SOUTH;
        robot.setDirection(direction);
        DirVector newLocation = robot.getPosition().dirVectorInDirection(direction);

        robot.pushRobotInDirection(robot.getDirection());

        assertEquals(robot.getPosition(), newLocation);
        assertEquals(direction, robot.getDirection());
    }

    @Test
    void moveForwardEast() {
        Direction direction = Direction.EAST;
        robot.setDirection(direction);
        DirVector newLocation = robot.getPosition().dirVectorInDirection(direction);

        robot.pushRobotInDirection(robot.getDirection());

        assertEquals(robot.getPosition(), newLocation);
        assertEquals(direction, robot.getDirection());
    }

    @Test
    void moveForwardWest() {
        Direction direction = Direction.WEST;
        robot.setDirection(direction);
        DirVector newLocation = robot.getPosition().dirVectorInDirection(direction);

        robot.pushRobotInDirection(robot.getDirection());

        assertEquals(robot.getPosition(), newLocation);
        assertEquals(direction, robot.getDirection());
    }

    @Test
    void moveBackwardWhenFacingNorth() {
        Direction direction = Direction.NORTH;
        robot.setDirection(direction);
        DirVector newLocation = robot.getPosition().dirVectorInDirection(direction.opposite());
        newLocation.setDirection(direction);

        robot.pushRobotInDirection(robot.getDirection().opposite());

        assertEquals(robot.getPosition(), newLocation);
        assertEquals(direction, robot.getDirection());
    }

    @Test
    void moveBackwardWhenFacingSouth() {
        Direction direction = Direction.SOUTH;
        robot.setDirection(direction);
        DirVector newLocation = robot.getPosition().dirVectorInDirection(direction.opposite());
        newLocation.setDirection(direction);

        robot.pushRobotInDirection(robot.getDirection().opposite());

        assertEquals(robot.getPosition(), newLocation);
        assertEquals(direction, robot.getDirection());
    }

    @Test
    void moveBackwardWhenFacingEast() {
        Direction direction = Direction.EAST;
        robot.setDirection(direction);
        DirVector newLocation = robot.getPosition().dirVectorInDirection(direction.opposite());
        newLocation.setDirection(direction);

        robot.pushRobotInDirection(robot.getDirection().opposite());

        assertEquals(robot.getPosition(), newLocation);
        assertEquals(direction, robot.getDirection());
    }

    @Test
    void moveBackwardWhenFacingWest() {
        Direction direction = Direction.WEST;
        robot.setDirection(direction);
        DirVector newLocation = robot.getPosition().dirVectorInDirection(direction.opposite());
        newLocation.setDirection(direction);

        robot.pushRobotInDirection(robot.getDirection().opposite());

        assertEquals(robot.getPosition(), newLocation);
        assertEquals(direction, robot.getDirection());
    }

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
