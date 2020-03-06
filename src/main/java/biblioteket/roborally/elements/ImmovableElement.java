package biblioteket.roborally.elements;

import biblioteket.roborally.grid.Direction;
import biblioteket.roborally.grid.IPosition;

import java.util.UUID;

// Need this for testing, can be removed once we get walls or lasers
public class ImmovableElement implements IElement {

    @Override
    public boolean immovable() {
        return true;
    }
}
