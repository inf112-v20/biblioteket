package biblioteket.roborally.elements;

import biblioteket.roborally.actors.IRobot;
import biblioteket.roborally.grid.Direction;

public class ExpressConveyorBelt implements InteractingElement {

    private final Direction direction;

    public ExpressConveyorBelt(Direction direction){
        this.direction = direction;
    }

    @Override
    public void interact(IRobot robot) {
        robot.moveForward();
        robot.moveForward();
    }


    @Override
    public String toString(){
        return "Express Converor Belt";
    }


}
