package biblioteket.roborally.elements.Interactingelements.cogs;

import biblioteket.roborally.actors.IPlayer;
import biblioteket.roborally.actors.IRobot;

public class LeftRotatingCogElement extends CogElement{

    @Override
    public void interact(IPlayer player) {
        IRobot robot = player.getRobot();
        robot.turnLeft();
    }

    @Override
    public String toString(){
        return "Left rotating cog";
    }
}
