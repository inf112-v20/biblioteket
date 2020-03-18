package biblioteket.roborally.elements.cogs;

import biblioteket.roborally.actors.IRobot;

public class RightRotatingCogElement extends CogElement{

    @Override
    public void interact(IRobot robot) {
        robot.turnRight();
    }

    @Override
    public String toString(){
        return "Right rotating cog";
    }
}
