package biblioteket.roborally.actors;

import biblioteket.roborally.board.DirVector;
import biblioteket.roborally.board.Direction;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * Class that handles rendering the movement of robots
 * Called from the GameScreen render method
 */
public class RobotRenderer {
    private final TiledMapTileLayer playerLayer;
    private final Queue<RobotStep> movements;

    public RobotRenderer(TiledMapTileLayer playerLayer) {
        this.playerLayer = playerLayer;
        movements = new LinkedList<>();
    }

    /**
     * Updates the PlayerLayer of the TiledMap with a single robot step
     */
    public void render() {
        RobotStep movement = movements.remove();

        DirVector oldPosition = movement.getOldPosition();
        DirVector newPosition = movement.getNewPosition();
        int rotation = movement.getRotation();
        int delay = movement.getDelay();
        TiledMapTileLayer.Cell playerCell = movement.getPlayerCell();

        // Update the playerlayer with robots new position
        playerLayer.setCell(oldPosition.getX(), oldPosition.getY(), null);
        playerLayer.setCell(newPosition.getX(), newPosition.getY(), playerCell);
        playerCell.setRotation(rotation);

        // Add delay so players can see each move
        wait(delay);
    }

    /**
     * Adds a single robot step to the movements queue to be rendered
     *
     * @param oldPosition position robot is moving from
     * @param newPosition position the robot is moving to
     * @param playerCell  the playercell of the player moving a robot
     */
    public void requestRendering(DirVector oldPosition, DirVector newPosition, Direction direction, int delay, TiledMapTileLayer.Cell playerCell) {
        RobotStep movement = new RobotStep(oldPosition, newPosition, direction, delay, playerCell);
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
            System.err.println("RobotRenderer timeout failed");
        }
    }

    /**
     * Datastructure that holds a single step of a single robot
     */
    private class RobotStep {
        private final DirVector oldPosition;
        private final DirVector newPosition;
        private final Direction direction;
        private final int delay;
        private final TiledMapTileLayer.Cell playerCell;

        public RobotStep(DirVector oldPosition, DirVector newPosition, Direction direction, int delay, TiledMapTileLayer.Cell playerCell) {
            this.oldPosition = oldPosition;
            this.newPosition = newPosition;
            this.direction = direction;
            this.delay = delay;
            this.playerCell = playerCell;
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
                case WEST:
                    return 1;
                case SOUTH:
                    return 2;
                case EAST:
                    return 3;
                default:
                    return 0;  // Case NORTH
            }
        }

        public int getDelay() {
            return delay;
        }
    }


}
