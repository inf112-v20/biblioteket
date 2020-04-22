package biblioteket.roborally.elements.walls;

import biblioteket.roborally.actors.IPlayer;
import biblioteket.roborally.actors.IRobot;
import biblioteket.roborally.board.DirVector;
import biblioteket.roborally.board.Direction;
import biblioteket.roborally.board.IBoard;

import java.util.List;

public class Laser {

    /**
     * @param board   board to interact with
     * @param players list of players in game
     * @param vector  direction/vector that laser shoots in
     */
    public void fireLaser(IBoard board, List<IPlayer> players, DirVector vector) {
        if (board.outOfBounds(vector)) return;

        for (IPlayer player : players) {
            IRobot robot = player.getRobot();
            DirVector robotPosition = robot.getPosition();
            if (vector.compareVector(robotPosition)) {
                robot.addDamageTokens(1);
                System.out.println(player.getName()  + " was hit by a laser, " + player.getRobot().getNumberOfDamageTokens() + " damage tokens");
                return;
            }
        }

        Direction direction = vector.getDirection();
        if (board.canMove(vector, direction)) {
            vector.forward(direction);
            fireLaser(board, players, vector);
        }
    }

}
