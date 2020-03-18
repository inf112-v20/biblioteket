package biblioteket.roborally.elements.conveyorbelts;

import biblioteket.roborally.board.Direction;

public class TurningConveyorBeltElement extends ConveyorBeltElement {
    public TurningConveyorBeltElement(Direction direction) {
        super(direction);
    }

    @Override
    public String toString(){
        return "turning conveyor belt " + direction;
    }
}
