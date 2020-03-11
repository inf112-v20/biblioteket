package biblioteket.roborally.elements;

import biblioteket.roborally.actors.IRobot;
import biblioteket.roborally.board.Direction;

public class ExpressConveyorBeltElement implements InteractingElement {
    private final Direction direction;

    public ExpressConveyorBeltElement(Direction direction){
        this.direction = direction;
    }

    /**
     * Moves robot in the direction conveyor belt is facing, twice
     * @param robot to be moved
     */
    @Override
    public void interact(IRobot robot) {
        robot.pushRobotInDirection(direction);
        robot.pushRobotInDirection(direction);
    }

    @Override
    public String toString(){
        return "Express Converor Belt";
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
