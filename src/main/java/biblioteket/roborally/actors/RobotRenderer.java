package biblioteket.roborally.actors;

import biblioteket.roborally.board.DirVector;
import biblioteket.roborally.board.Direction;
import biblioteket.roborally.game.GameLoop;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * Class that handles rendering the movement of robots
 * Called from the GameScreen render method
 */
public class RobotRenderer {
    private final TiledMapTileLayer playerLayer;
    private final GameLoop gameLoop;
    private final Queue<RobotStep> movements;
    private final List<IActor> players;

    public RobotRenderer(TiledMapTileLayer playerLayer, List<IActor> players, GameLoop gameLoop) {
        this.playerLayer = playerLayer;
        this.players = players;
        this.gameLoop = gameLoop;
        movements = new LinkedList<>();
    }

    /**
     * Updates the PlayerLayer of the TiledMap with a single robot step
     */
    public void renderStep() {
        RobotStep movement = movements.remove();

        DirVector oldPosition = movement.getOldPosition();
        DirVector newPosition = movement.getNewPosition();
        int rotation = movement.getRotation();
        int delay = movement.getDelay();
        TiledMapTileLayer.Cell playerCell = movement.getPlayerCell();
        boolean debug = movement.isDebug();

        // Update the playerlayer with robots new position
        playerLayer.setCell(oldPosition.getX(), oldPosition.getY(), null);
        playerLayer.setCell(newPosition.getX(), newPosition.getY(), playerCell);
        playerCell.setRotation(rotation);

        // Add delay so players can see each move
        wait(delay);

        if (movements.isEmpty() && !debug) {
            renderAllPlayers();
            gameLoop.newTurn(); // New turn event starts only after all moves have been rendered
        }
    }

    /**
     * Adds a single robot step to the movements queue to be rendered
     *
     * @param oldPosition position robot is moving from
     * @param newPosition position the robot is moving to
     * @param playerCell  the playercell of the player moving a robot
     * @param debug       true if debug, wont start a new round after rendering
     */
    public void requestRendering(DirVector oldPosition, DirVector newPosition, Direction direction, int delay, TiledMapTileLayer.Cell playerCell, boolean debug) {
        RobotStep movement = new RobotStep(oldPosition, newPosition, direction, delay, playerCell, debug);
        movements.add(movement);
    }

    /**
     * @return true if there are any movements left in queue to be rendered
     */
    public boolean isRequestingRendering() {
        return !movements.isEmpty();
    }

    /**
     * method to wait
     *
     * @param milliseconds to wait
     */
    private void wait(int milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            Gdx.app.error("RobotRenderer: ", "Timeout faled");
        }
    }

    /**
     * Handles rare visual bug caused by two robots standing on top of each other causes only one to be rendered
     */
    private void renderAllPlayers() {
        for (IActor player : players) {
            if (!player.isPermanentDead()) {
                DirVector position = player.getRobot().getPosition();
                TiledMapTileLayer.Cell playerCell = player.getPlayerCell();
                playerLayer.setCell(position.getX(), position.getY(), playerCell);
            }
        }
    }

    /**
     * Moves permanently dead robots off the grid
     *
     * @param oldPosition of dead robot
     * @param playerCell  of dead robot
     */
    public void removePlayer(DirVector oldPosition, TiledMapTileLayer.Cell playerCell) {
        RobotStep movement = new RobotStep(oldPosition, new DirVector(-1, -1, Direction.NORTH), Direction.NORTH, 100, playerCell, false);
        movements.add(movement);
    }

    /**
     * Datastructure that holds a single step of a single robot
     */
    private static class RobotStep {
        private final DirVector oldPosition;
        private final DirVector newPosition;
        private final Direction direction;
        private final int delay;
        private final TiledMapTileLayer.Cell playerCell;
        private final boolean debug;

        public RobotStep(DirVector oldPosition, DirVector newPosition, Direction direction, int delay, TiledMapTileLayer.Cell playerCell, boolean debug) {
            this.oldPosition = oldPosition;
            this.newPosition = newPosition;
            this.direction = direction;
            this.delay = delay;
            this.playerCell = playerCell;
            this.debug = debug;
        }

        public DirVector getOldPosition() {
            return oldPosition;
        }

        public DirVector getNewPosition() {
            return newPosition;
        }

        public TiledMapTileLayer.Cell getPlayerCell() {
            return playerCell;
        }

        public int getRotation() {
            switch (direction) {
                case NORTH:
                    return 0;
                case WEST:
                    return 1;
                case SOUTH:
                    return 2;
                case EAST:
                    return 3;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public int getDelay() {
            return delay;
        }

        public boolean isDebug() {
            return debug;
        }
    }
}
