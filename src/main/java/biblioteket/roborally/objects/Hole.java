package biblioteket.roborally.objects;

import biblioteket.roborally.Direction;
import biblioteket.roborally.IElement;
import biblioteket.roborally.grid.IPosition;

public class Hole implements IElement {
    private IPosition position;
    private Direction direction1 = Direction.EAST;

    public IPosition getPos() {
        return position;
    }

    public void setPos(IPosition pos) {
        if(this.immovable()==false){
            position = pos;
        }
        else {

        }
    }

    public boolean immovable() {
        return true;
    }

    public Direction getDirection() {
        return direction1;
    }

    public void setDirection(Direction direction){
        direction1 = direction;
    }

}
