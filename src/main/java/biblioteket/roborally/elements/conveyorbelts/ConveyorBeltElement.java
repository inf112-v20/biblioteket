package biblioteket.roborally.elements.conveyorbelts;

import biblioteket.roborally.actors.IPlayer;
import biblioteket.roborally.actors.IRobot;
import biblioteket.roborally.actors.Robot;
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
     * @param player with robot to be moved
     */
    @Override
    public void interact(IPlayer player) {
        IRobot robot = player.getRobot();
        robot.pushRobotInDirection(direction);
    }

    @Override
    public String toString() {
        return "Conveyor Belt";
    }

}
