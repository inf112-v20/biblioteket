package biblioteket.roborally.actors;

import biblioteket.roborally.IElement;
import biblioteket.roborally.board.DirVector;
import biblioteket.roborally.board.Direction;

// Need this for testing, can be removed once we get walls or lasers
public class ImmovableElement implements IElement {

    @Override
    public DirVector getPosition() {
        return null;
    }

    @Override
    public void setPosition(DirVector pos) {

    }

    @Override
    public void setPosition(int x, int y) {

    }

    @Override
    public boolean immovable() {
        return true;
    }

    @Override
    public Direction getDirection() {
        return null;
    }

    @Override
    public void setDirection(Direction direction) {

    }
}
