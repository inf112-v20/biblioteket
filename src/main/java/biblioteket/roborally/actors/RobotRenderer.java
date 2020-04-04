package biblioteket.roborally.actors;

import biblioteket.roborally.board.DirVector;
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
    private Queue<RobotStep> movements;

    public RobotRenderer(TiledMapTileLayer playerLayer){
        this.playerLayer = playerLayer;
        movements = new LinkedList<>();
    }

    /**
     * Updates the PlayerLayer of the TiledMap with a single robot step
     */
    public void render(){
        RobotStep movement = movements.remove();
        DirVector oldPosition = movement.getOldPosition();
        DirVector newPosition = movement.getNewPosition();
        TiledMapTileLayer.Cell playerCell = movement.getPlayerCell();

        // Update the playerlayer with robots new position
        playerLayer.setCell(oldPosition.getX(), oldPosition.getY(), null);
        playerLayer.setCell(newPosition.getX(), newPosition.getY(), playerCell);

        // Wait for 500 milliseconds so users can see each movement
        wait(500);
    }

    /**
     * Adds a single robot step to the movements queue to be rendered
     *
     * @param oldPosition position robot is moving from
     * @param newPosition position the robot is moving to
     * @param playerCell the playercell of the player moving a robot
     */
    public void requestRendering(DirVector oldPosition, DirVector newPosition, TiledMapTileLayer.Cell playerCell){
        RobotStep movement = new RobotStep(oldPosition, newPosition, playerCell);
        movements.add(movement);
    }

    /**
     * @return true if there are any movements left in queue to be rendered
     */
    public boolean IsRequestingRendering(){
        return !movements.isEmpty();
    }

    /**
     * method to wait
     *
     * @param milliseconds to wait
     */
    private void wait(int milliseconds){
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.err.println("RobotRenderer wait method failed");
            e.printStackTrace();
        }
    }

    /**
     * Datastructure that holds a single step of a single robot
     */
    private class RobotStep {
        private DirVector oldPosition;
        private DirVector newPosition;
        private TiledMapTileLayer.Cell playerCell;

        public RobotStep(DirVector oldPosition, DirVector newPosition, TiledMapTileLayer.Cell playerCell){
            this.oldPosition = oldPosition;
            this.newPosition = newPosition;
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
    }

}
