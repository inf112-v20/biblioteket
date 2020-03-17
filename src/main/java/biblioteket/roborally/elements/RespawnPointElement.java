package biblioteket.roborally.elements;

import biblioteket.roborally.board.Direction;

// Maybe save x,y position?
// Not sure yet how this would be implemented
public class RespawnPointElement implements IElement {
    private final int point;

    public RespawnPointElement(int point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "Respawn";
    }


    @Override
    public boolean blocking(Direction direction, boolean exit) {
        return false;
    }
}
