package biblioteket.roborally.elements.InteractingElements.conveyorbelts;

import biblioteket.roborally.board.Direction;

public class TurningExpressConveyorBeltElement extends ExpressConveyorBeltElement{
    public TurningExpressConveyorBeltElement(Direction direction) {
        super(direction);
    }

    @Override
    public String toString(){
        return "turning express conveyor belt " + direction;
    }
}
