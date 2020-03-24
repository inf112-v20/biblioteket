package biblioteket.roborally.elements.interactingelements.conveyorbelts;

import biblioteket.roborally.actors.IPlayer;
import biblioteket.roborally.actors.IRobot;
import biblioteket.roborally.board.Direction;
import biblioteket.roborally.elements.interactingelements.InteractingElement;

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
