package biblioteket.roborally.actors;

import biblioteket.roborally.Direction;
import biblioteket.roborally.GameBoard;
import biblioteket.roborally.IElement;
import biblioteket.roborally.grid.IGrid;
import biblioteket.roborally.grid.IPosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lwjgl.Sys;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RobotTest {
    int width = 5;
    int height = 5;
    GameBoard board;
    IGrid grid;

    IPosition pos1x1y;
    IPosition pos1x2y;
    int fullLife = 3;

    IRobot robot;
    IPlayer player;
    IPosition archiveMarker;

    Player secondPlayer;
    IRobot secondRobot;
    IPosition secondArchiveMarker;

    @BeforeEach
    void setUp() {
        board = new GameBoard(width, height);
        grid = board.getGrid();

        pos1x1y = grid.getPosition(1, 1);
        archiveMarker = grid.getPosition(2, 2);
        player = new Player();
        robot = new Robot(pos1x1y, archiveMarker, Direction.NORTH, board);
        robot.setPlayer(player);

        pos1x2y = grid.getPosition(1, 2);
        secondArchiveMarker = grid.getPosition(3, 3);
        secondPlayer = new Player();


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
        IPosition positionInDirection = grid.positionInDirection(position, Direction.SOUTH);
        robot.moveForward();
        assertEquals(positionInDirection, robot.getPosition());
    }

    @Test
    void moveForwardTowardsEastWhenThereIsNoObstacle() {
        robot.setDirection(Direction.EAST);
        IPosition<Integer> position = robot.getPosition();
        IPosition positionInDirection = grid.positionInDirection(position, Direction.EAST);
        robot.moveForward();
        assertEquals(positionInDirection, robot.getPosition());
    }

    @Test
    void moveForwardTowardsWestWhenThereIsNoObstacle() {
        robot.setDirection(Direction.WEST);
        IPosition<Integer> position = robot.getPosition();
        IPosition positionInDirection = grid.positionInDirection(position, Direction.WEST);
        robot.moveForward();
        assertEquals(positionInDirection, robot.getPosition());
    }

    @Test
    void moveForwardTowardsNorthWhenItIsOutOfBoundaries() {
        int damageBeforeDeath = 2;
        robot.setDirection(Direction.NORTH);
        robot.setPos(0, 0);
        robot.addDamageTokens(damageBeforeDeath);
        assertEquals(fullLife, robot.getPlayer().getLives()); // TODO maybe remove this and the next.
        assertEquals(damageBeforeDeath, robot.getNumberOfDamageTokens());
        robot.moveForward();
        assertEquals(fullLife - 1, robot.getPlayer().getLives());
        assertEquals(robot.getArchiveMarker(), robot.getPosition());
        assertEquals(0, robot.getNumberOfDamageTokens());
    }

    @Test
    void moveForwardTowardSouthWhenARobotWhoCanBePushedIsInTheWay() {
        secondRobot = new Robot(pos1x2y, secondArchiveMarker, Direction.NORTH, board);
        secondRobot.setPlayer(secondPlayer);

        robot.setDirection(Direction.SOUTH);
        secondRobot.setDirection(Direction.NORTH);
        IPosition firstRobotNewPosition = grid.positionInDirection(robot.getPosition(), robot.getDirection());
        assertEquals(firstRobotNewPosition, secondRobot.getPosition()); //makes sure we check for right event
        IPosition secondRobotNewPosition = grid.positionInDirection(secondRobot.getPosition(), robot.getDirection());

        robot.moveForward();

        assertEquals(Direction.NORTH, secondRobot.getDirection()); //Makes sure nothing weird happens, might be removed.
        assertEquals(firstRobotNewPosition, robot.getPosition());
        assertEquals(secondRobotNewPosition, secondRobot.getPosition());

        //Makes sure there is one robot at each position
        int numberOfRobotsOnPosition1 = 0;
        List<IElement> elementsInPos = firstRobotNewPosition.getContents();
        for (IElement element : elementsInPos)
            if (element instanceof IRobot)
                numberOfRobotsOnPosition1++;
        assertEquals(1, numberOfRobotsOnPosition1);


        int numberOfRobotsOnPosition2 = 0;
        List<IElement> elementsInPos2 = secondRobotNewPosition.getContents();
        for (IElement element : elementsInPos2)
            if (element instanceof IRobot)
                numberOfRobotsOnPosition2++;
        assertEquals(1, numberOfRobotsOnPosition2);

    }


    @Test
    void moveBackwardAllDirectionsWhenThereIsNoObstacle() {
    }

}