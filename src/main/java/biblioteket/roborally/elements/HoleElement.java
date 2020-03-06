package biblioteket.roborally.elements;

import biblioteket.roborally.actors.IRobot;
import biblioteket.roborally.grid.Direction;

import java.util.UUID;

public class HoleElement implements InteractingElement {

    @Override
    public void interact(IRobot robot) {
        // robot.respawn();
        // robot.loseOneHP()
    }

    @Override
    public String toString(){
        return "Hole";
    }

}
