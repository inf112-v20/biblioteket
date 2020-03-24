package biblioteket.roborally.elements.interactingelements;

import biblioteket.roborally.actors.IPlayer;
import biblioteket.roborally.actors.IRobot;

public class HoleElement implements InteractingElement {
    private int priority = 2;

    @Override
    public void interact(IPlayer player) {
        IRobot robot = player.getRobot();
        robot.addDamageTokens(1);
        robot.moveToArchiveMarker();
    }

    @Override
    public String toString() {
        return "Hole";
    }

    @Override
    public int getPriority() {
        return priority;
    }

}
