package biblioteket.roborally.elements.conveyorbelts;

import biblioteket.roborally.actors.IRobot;
import biblioteket.roborally.board.Direction;
import biblioteket.roborally.elements.InteractingElement;

public class ExpressConveyorBeltElement extends ConveyorBeltElement {

    public ExpressConveyorBeltElement(Direction direction) {
        super(direction);
    }

    @Override
    public String toString() {
        return "Express Converor Belt";
    }
}
