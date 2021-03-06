package biblioteket.roborally.elements.walls;

import biblioteket.roborally.actors.IActor;
import biblioteket.roborally.actors.IRobot;
import biblioteket.roborally.board.DirVector;
import biblioteket.roborally.board.Direction;
import biblioteket.roborally.board.IBoard;
import com.badlogic.gdx.Gdx;

import java.util.List;

public class Laser {

    /**
     * Laser moves along board in direction until it hits a wall, robot,
     * or is out of bounds
     *
     * @param board   board to interact with
     * @param players list of players in game
     * @param vector  direction/vector that laser shoots in
     */
    public void fireLaser(IBoard board, List<IActor> players, DirVector vector) {
        if (board.outOfBounds(vector)) return;
        // Check if laser hit a robot
        for (IActor player : players) {
            IRobot robot = player.getRobot();
            DirVector robotPosition = robot.getPosition();
            if (vector.compareVector(robotPosition)) {
                robot.addDamageTokens(1);
                Gdx.app.log("Laser: ", player.getName() + " was hit by a laser, " + player.getRobot().getNumberOfDamageTokens() + " damage tokens");
                return;
            }
        }
        // Laser moves one step forward
        Direction direction = vector.getDirection();
        if (board.canMove(vector, direction)) {
            vector.forward(direction);
            fireLaser(board, players, vector);
        }
    }

}
