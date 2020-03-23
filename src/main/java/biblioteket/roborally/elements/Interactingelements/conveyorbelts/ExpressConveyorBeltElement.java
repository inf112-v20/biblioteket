package biblioteket.roborally.elements.Interactingelements.conveyorbelts;

import biblioteket.roborally.board.Direction;

public class ExpressConveyorBeltElement extends ConveyorBeltElement {

    public ExpressConveyorBeltElement(Direction direction) {
        super(direction);
    }

    @Override
    public String toString() {
        return "Express Converor Belt";
    }
}
