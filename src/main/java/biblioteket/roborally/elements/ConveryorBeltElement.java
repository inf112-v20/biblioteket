package biblioteket.roborally.elements;

import biblioteket.roborally.actors.IRobot;
import biblioteket.roborally.grid.Direction;

public class ConveryorBeltElement implements InteractingElement {

    private final Direction direction;

    public ConveryorBeltElement(Direction direction){
        this.direction = direction;

    }

    @Override
    public void interact(IRobot robot) {
        robot.moveBackward();
    }

}
