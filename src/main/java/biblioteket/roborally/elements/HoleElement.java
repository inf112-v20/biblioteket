package biblioteket.roborally.elements;

import biblioteket.roborally.actors.IPlayer;
import biblioteket.roborally.actors.IRobot;
import biblioteket.roborally.board.Direction;

public class HoleElement implements InteractingElement {
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
    public boolean blocking(Direction direction, boolean exit) {
        return false;
    }
}
