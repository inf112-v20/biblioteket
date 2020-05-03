package biblioteket.roborally.elements.interacting.conveyorbelts;

import biblioteket.roborally.actors.IActor;
import biblioteket.roborally.board.Direction;
import biblioteket.roborally.elements.interacting.InteractingElement;

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
    public void interact(IActor player) {
        player.moveRobot(direction, 500, false, true);
    }

    @Override
    public String toString() {
        return "Conveyor Belt";
    }

}
