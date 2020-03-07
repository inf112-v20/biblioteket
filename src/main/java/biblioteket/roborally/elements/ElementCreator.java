package biblioteket.roborally.elements;

import biblioteket.roborally.grid.Direction;

public class ElementCreator {


    /**
     * Takes tiled map IDs and returns the corresponding board element
     * @param ID
     * @return IElement corresponding to ID
     */
    public static IElement getElement(int ID){
        switch(ID){
            // Hole
            case(6): return new HoleElement();
            // Wall
            case(8): return new WallElement(Direction.EAST, Direction.SOUTH);
            case(16): return new WallElement(Direction.EAST, Direction.NORTH);
            case(23): return new WallElement(Direction.EAST, null);
            case(24): return new WallElement(Direction.WEST, Direction.NORTH);
            case(29): return new WallElement(null, Direction.SOUTH);
            case(30): return new WallElement(Direction.WEST, null);
            case(31): return new WallElement(null, Direction.NORTH);
            case(32): return new WallElement(Direction.WEST, Direction.SOUTH);
            // Conveyor Belt
            case(49): return new ConveryorBeltElement(Direction.NORTH);
            case(50): return new ConveryorBeltElement(Direction.SOUTH);
            case(51): return new ConveryorBeltElement(Direction.WEST);
            case(52): return new ConveryorBeltElement(Direction.EAST);
            // Express Conveyor Belt
            case(13): return new ExpressConveyorBeltElement(Direction.NORTH);
            case(14): return new ExpressConveyorBeltElement(Direction.EAST);
            case(21): return new ExpressConveyorBeltElement(Direction.SOUTH);
            case(22): return new ExpressConveyorBeltElement(Direction.WEST);
            // Respawn point
            case(121): return new RespawnPointElement(1);
            case(122): return new RespawnPointElement(2);
            case(123): return new RespawnPointElement(3);
            case(124): return new RespawnPointElement(4);
            case(129): return new RespawnPointElement(5);
            case(130): return new RespawnPointElement(6);
            case(131): return new RespawnPointElement(7);
            case(132): return new RespawnPointElement(8);
            // Flag
            case(55): return new FlagElement(1);
            case(63): return new FlagElement(2);
            case(71): return new FlagElement(3);
            case(79): return new FlagElement(4);
            default: return null;

        }
    }
}
