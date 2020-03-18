package biblioteket.roborally.elements.conveyorbelts;

import biblioteket.roborally.actors.IRobot;
import biblioteket.roborally.board.Direction;
import biblioteket.roborally.elements.InteractingElement;

public class ConveyorBeltElement implements InteractingElement {
    protected final Direction direction;

    public ConveyorBeltElement(Direction direction) {
        this.direction = direction;
    }

    /**
     * Moves robot in the direction conveyor belt is facing
     *
     * @param robot to be moved
     */
    @Override
    public void interact(IRobot robot) {
        robot.pushRobotInDirection(direction);
    }

    @Override
    public String toString() {
        return "Conveyor Belt";
    }

}
