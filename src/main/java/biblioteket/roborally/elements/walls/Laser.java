package biblioteket.roborally.elements.walls;

import biblioteket.roborally.actors.IPlayer;
import biblioteket.roborally.actors.IRobot;
import biblioteket.roborally.board.DirVector;
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
            if (compareDirVectorPosition(vector, robotPosition)) {
                board.getLayer("Laser Layer").setVisible(true);
                robot.addDamageTokens(1);
                System.out.println("robot at " + robotPosition.getX() + "," + robotPosition.getY() + " was hit by a laser");
                return;
            }
        }
        if (board.canMove(vector, vector.getDirection())) {
            vector.forward(1);
            fireLaser(board, players, vector);
        }
    }

    /**
     * @return true if two DirVectors have the same x and y coordinates
     */
    private boolean compareDirVectorPosition(DirVector v1, DirVector v2) {
        return v1.getX() == v2.getX() && v1.getY() == v2.getY();
    }
}
