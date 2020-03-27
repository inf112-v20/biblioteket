package biblioteket.roborally.programcards;

import biblioteket.roborally.actors.IPlayer;
import biblioteket.roborally.actors.IRobot;
import biblioteket.roborally.actors.Player;
import biblioteket.roborally.actors.Robot;
import biblioteket.roborally.board.Board;
import biblioteket.roborally.board.DirVector;
import biblioteket.roborally.board.Direction;
import biblioteket.roborally.board.IBoard;
import biblioteket.roborally.elements.ArchiveMarkerElement;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.GL20;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardTest {
    private IRobot robot;
    private IBoard board;
    private ICard forwardOne;
    private ICard forwardTwo;
    private ICard forwardThree;
    private ICard backUp;
    private ICard uTurn;
    private ICard left;
    private ICard right;

    @BeforeEach
    void setUp() {
        Application _application = new HeadlessApplication(new ApplicationListener() {
            @Override
            public void create() {
            }

            @Override
            public void resize(int width, int height) {
            }

            @Override
            public void render() {
            }

            @Override
            public void pause() {
            }

            @Override
            public void resume() {
            }

            @Override
            public void dispose() {
            }
        });

        Gdx.gl20 = Mockito.mock(GL20.class);
        Gdx.gl = Gdx.gl20;

        board = new Board("assets/TestingMap.tmx");

        robot = new Robot(board.getArchiveMarker(1));
        IPlayer player = new Player(null,null);
        player.setRobot(robot);
        robot.setPlayer(player);

        forwardOne = new Card(CardType.MOVE_1, 4);
        forwardTwo = new Card(CardType.MOVE_2, 6);
        forwardThree = new Card(CardType.MOVE_3, 7);
        backUp = new Card(CardType.BACK_UP, 5);
        uTurn = new Card(CardType.U_TURN, 3);
        left = new Card(CardType.ROTATE_LEFT, 2);
        right = new Card(CardType.ROTATE_RIGHT, 1);
    }


    @Test
    void turnLeftFromNorth() {
        Direction startDir = Direction.NORTH;
        Direction endDir = Direction.WEST;
        robot.setDirection(startDir);
        left.doCardAction(robot, board);
        assertEquals(endDir, robot.getDirection());
    }

    @Test
    void turnLeftFromSouth() {
        Direction startDir = Direction.SOUTH;
        Direction endDir = Direction.EAST;
        robot.setDirection(startDir);
        left.doCardAction(robot, board);
        assertEquals(endDir, robot.getDirection());
    }

    @Test
    void turnLeftFromEast() {
        Direction startDir = Direction.EAST;
        Direction endDir = Direction.NORTH;
        robot.setDirection(startDir);
        left.doCardAction(robot, board);
        assertEquals(endDir, robot.getDirection());
    }

    @Test
    void turnLeftFromWest() {
        Direction startDir = Direction.WEST;
        Direction endDir = Direction.SOUTH;
        robot.setDirection(startDir);
        left.doCardAction(robot, board);
        assertEquals(endDir, robot.getDirection());
    }

    @Test
    void turnRightFromNorth() {
        Direction startDir = Direction.NORTH;
        Direction endDir = Direction.EAST;
        robot.setDirection(startDir);
        right.doCardAction(robot, board);
        assertEquals(endDir, robot.getDirection());
    }

    @Test
    void turnRightFromSouth() {
        Direction startDir = Direction.SOUTH;
        Direction endDir = Direction.WEST;
        robot.setDirection(startDir);
        right.doCardAction(robot, board);
        assertEquals(endDir, robot.getDirection());
    }

    @Test
    void turnRightFromEast() {
        Direction startDir = Direction.EAST;
        Direction endDir = Direction.SOUTH;
        robot.setDirection(startDir);
        right.doCardAction(robot, board);
        assertEquals(endDir, robot.getDirection());
    }

    @Test
    void turnRightFromWest() {
        Direction startDir = Direction.WEST;
        Direction endDir = Direction.NORTH;
        robot.setDirection(startDir);
        right.doCardAction(robot, board);
        assertEquals(endDir, robot.getDirection());
    }

    @Test
    void uTurnFromNorth(){
        Direction startDir = Direction.NORTH;
        Direction endDir = startDir.opposite();
        robot.setDirection(startDir);
        uTurn.doCardAction(robot, board);
        assertEquals(endDir, robot.getDirection());
    }

    @Test
    void uTurnFromSouth(){
        Direction startDir = Direction.SOUTH;
        Direction endDir = startDir.opposite();
        robot.setDirection(startDir);
        uTurn.doCardAction(robot, board);
        assertEquals(endDir, robot.getDirection());
    }

    @Test
    void uTurnFromWest(){
        Direction startDir = Direction.WEST;
        Direction endDir = startDir.opposite();
        robot.setDirection(startDir);
        uTurn.doCardAction(robot, board);
        assertEquals(endDir, robot.getDirection());
    }

    @Test
    void uTurnFromEast(){
        Direction startDir = Direction.EAST;
        Direction endDir = startDir.opposite();
        robot.setDirection(startDir);
        uTurn.doCardAction(robot, board);
        assertEquals(endDir, robot.getDirection());
    }

    @Test
    void moveForwardNorth() {
        Direction direction = Direction.NORTH;
        robot.setDirection(direction);
        DirVector newLocation = robot.getPosition().dirVectorInDirection(direction);

        forwardOne.doCardAction(robot, board);

        assertEquals(robot.getPosition(), newLocation);
        assertEquals(direction, robot.getDirection());
    }

    @Test
    void moveForwardTwoNorth() {
        Direction direction = Direction.NORTH;
        robot.setDirection(direction);
        DirVector newLocationOneForward = robot.getPosition().dirVectorInDirection(direction);
        DirVector newLocationTwoForward = newLocationOneForward.dirVectorInDirection(direction);

        forwardTwo.doCardAction(robot, board);

        assertEquals(newLocationTwoForward, robot.getPosition());
        assertEquals(direction, robot.getDirection());
    }

    @Test
    void moveForwardThreeNorth() {
        Direction direction = Direction.NORTH;
        robot.setDirection(direction);
        DirVector newLocationOneForward = robot.getPosition().dirVectorInDirection(direction);
        DirVector newLocationTwoForward = newLocationOneForward.dirVectorInDirection(direction);
        DirVector newLocationThreeForward = newLocationTwoForward.dirVectorInDirection(direction);


        forwardThree.doCardAction(robot, board);


        assertEquals(newLocationThreeForward, robot.getPosition());
        assertEquals(direction, robot.getDirection());
    }

    @Test
    void moveBackwardFromSouth() {
        Direction direction = Direction.SOUTH;
        robot.setDirection(direction);
        DirVector newLocation = robot.getPosition().dirVectorInDirection(direction.opposite());
        newLocation.setDirection(direction);

        backUp.doCardAction(robot, board);

        assertEquals(robot.getPosition().getX(), newLocation.getX());
        assertEquals(robot.getPosition().getY(), newLocation.getY());
    }

}