package biblioteket.roborally.elements;

import biblioteket.roborally.actors.IRobot;

public interface InteractingElement extends IElement {
    void interact(IRobot robot);
}
