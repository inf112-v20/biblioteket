package biblioteket.roborally.elements;

import biblioteket.roborally.actors.IPlayer;
import biblioteket.roborally.actors.IRobot;

public interface InteractingElement extends IElement {
    /**
     * Any element that can interact with a robot once it is on a tile
     * implements this method to e.g. push the robot along, rotate it, damage
     * it etc.
     *
     * @param player the player with the robot on current cell
     */
    void interact(IPlayer player);
}
