package biblioteket.roborally.elements.cogs;

import biblioteket.roborally.actors.IRobot;

public class LeftRotatingCogElement extends CogElement{

    @Override
    public void interact(IRobot robot) {
        robot.turnLeft();
    }

    @Override
    public String toString(){
        return "Left rotating cog";
    }
}
