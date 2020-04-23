package biblioteket.roborally.elements.interacting;

import biblioteket.roborally.actors.IActor;
import biblioteket.roborally.actors.IRobot;

public class HoleElement implements InteractingElement {

    @Override
    public void interact(IActor player) {
        IRobot robot = player.getRobot();
        player.removeOneLife();
        robot.moveToArchiveMarker();
    }

    @Override
    public String toString() {
        return "Hole";
    }

    @Override
    public int getPriority() {
        return 2;
    }

}
