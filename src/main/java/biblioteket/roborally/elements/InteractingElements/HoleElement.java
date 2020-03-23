package biblioteket.roborally.elements.InteractingElements;

import biblioteket.roborally.actors.IPlayer;
import biblioteket.roborally.actors.IRobot;
import biblioteket.roborally.board.Direction;

public class HoleElement implements InteractingElement {
    private final int priority = 2;

    @Override
    public void interact(IPlayer player) {
        IRobot robot = player.getRobot();
        robot.addDamageTokens(1);
        robot.setPosition(robot.getArchiveMarker());
    }

    @Override
    public String toString() {
        return "Hole";
    }

    @Override
    public int getPriority(){
        return priority;
    }

}
