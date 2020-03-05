package biblioteket.roborally.elements;

import biblioteket.roborally.grid.Direction;
import biblioteket.roborally.grid.IPosition;

import java.util.UUID;

// Need this for testing, can be removed once we get walls or lasers
public class ImmovableElement implements IElement {
    @Override
    public IPosition getPos() {
        return null;
    }

    @Override
    public void setPos(IPosition pos) {

    }

    @Override
    public void setPos(int x, int y) {

    }

    @Override
    public boolean immovable() {
        return true;
    }

    @Override
    public UUID getID() {
        return null;
    }

    @Override
    public Direction getDirection() {
        return null;
    }

    @Override
    public void setDirection(Direction direction) {

    }
}
