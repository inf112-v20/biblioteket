package biblioteket.roborally.elements;

import biblioteket.roborally.actors.IRobot;
import biblioteket.roborally.board.Direction;

public class HoleElement implements InteractingElement {

    /**
     * @param robot
     */
    @Override
    public void interact(IRobot robot) {
        // robot.loseOneHP()
        // robot.respawn();
    }

    @Override
    public String toString(){
        return "Hole";
    }

    @Override
    public boolean blockingExit(Direction direction) {
        return false;
    }

    @Override
    public boolean blockingEntry(Direction direction) {
        return false;
    }
}
