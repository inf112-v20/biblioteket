package biblioteket.roborally.elements;

import biblioteket.roborally.actors.IRobot;
import biblioteket.roborally.board.Direction;

public class ConveyorBeltElement implements InteractingElement {
    private final Direction direction;

    public ConveyorBeltElement(Direction direction){
        this.direction = direction;
    }

    /**
     * Moves robot in the direction conveyor belt is facing
     * @param robot to be moved
     */
    @Override
    public void interact(IRobot robot) {
        robot.pushRobotInDirection(direction);
    }

    @Override
    public String toString(){
        return "Conveyor Belt";
    }

    @Override
    public boolean blockingExit(Direction direction) {
        return false;
    }

    @Override
    public boolean blockingEntry(Direction direction) {
        return false;
    }
}
