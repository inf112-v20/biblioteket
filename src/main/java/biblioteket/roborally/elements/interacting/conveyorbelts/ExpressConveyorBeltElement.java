package biblioteket.roborally.elements.interacting.conveyorbelts;

import biblioteket.roborally.board.Direction;

public class ExpressConveyorBeltElement extends ConveyorBeltElement {

    public ExpressConveyorBeltElement(Direction direction) {
        super(direction);
    }

    @Override
    public String toString() {
        return "Express Conveyor Belt";
    }
}
