package biblioteket.roborally.elements;

import biblioteket.roborally.actors.IRobot;

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

}
