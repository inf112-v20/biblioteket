package biblioteket.roborally.elements;

import biblioteket.roborally.actors.IRobot;
import biblioteket.roborally.grid.Direction;

public class ConveryorBeltElement implements InteractingElement {

    private final Direction direction;

    public ConveryorBeltElement(Direction direction){
        this.direction = direction;

    }

    /**
     * Moves robot in the direction conveyor belt is facing
     * @param robot to be moved
     */
    @Override
    public void interact(IRobot robot) {
        robot.move(direction);
    }

    @Override
    public String toString(){
        return "Conveyor Belt";
    }

}
