package biblioteket.roborally.elements.Interactingelements.cogs;

import biblioteket.roborally.actors.IPlayer;
import biblioteket.roborally.actors.IRobot;

public class RightRotatingCogElement extends CogElement{

    @Override
    public void interact(IPlayer player) {
        IRobot robot = player.getRobot();
        robot.turnRight();
    }

    @Override
    public String toString(){
        return "Right rotating cog";
    }
}
