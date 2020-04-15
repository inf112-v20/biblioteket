package biblioteket.roborally.elements.interacting;

import biblioteket.roborally.actors.IPlayer;
import biblioteket.roborally.actors.IRobot;

public class HoleElement implements InteractingElement {

    @Override
    public void interact(IPlayer player) {
        IRobot robot = player.getRobot();
        System.out.println("OBS, hole: robot at " + robot.getPosition().getX() + "," + robot.getPosition().getY() + " fell into a hole");
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
