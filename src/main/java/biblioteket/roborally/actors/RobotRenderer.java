package biblioteket.roborally.actors;

import biblioteket.roborally.board.DirVector;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class RobotRenderer {
    private final TiledMapTileLayer playerLayer;
    private final TiledMapTileLayer.Cell playerCell;

    private Queue<MovePair> movements;

    public RobotRenderer(TiledMapTileLayer playerLayer, TiledMapTileLayer.Cell playerCell){
        this.playerLayer = playerLayer;
        this.playerCell = playerCell;
        movements = new LinkedList<>();
    }

    public void render(){
        MovePair movement = movements.remove();

        DirVector oldPosition = movement.getOldPosition();
        DirVector newPosition = movement.getNewPosition();
        playerLayer.setCell(oldPosition.getX(), oldPosition.getY(), null);
        playerLayer.setCell(newPosition.getX(), newPosition.getY(), playerCell);

        wait(500);
    }

    public void requestRendering(DirVector oldPosition, DirVector newPosition){
        MovePair movement = new MovePair(oldPosition, newPosition);
        movements.add(movement);
    }

    public boolean IsRequestingRendering(){
        return !movements.isEmpty();
    }

    private void wait(int milliseconds){
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class MovePair {
        private DirVector oldPosition;
        private DirVector newPosition;

        public MovePair(DirVector oldPosition, DirVector newPosition){
            this.oldPosition = oldPosition;
            this.newPosition = newPosition;
        }

        public DirVector getOldPosition() {
            return oldPosition;
        }

        public DirVector getNewPosition() {
            return newPosition;
        }
    }

}
