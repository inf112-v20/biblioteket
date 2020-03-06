package biblioteket.roborally.elements;

import biblioteket.roborally.actors.IRobot;

public class FlagElement implements InteractingElement {

    private final int flagNumber;

    public FlagElement(int flagNumber) {
        this.flagNumber = flagNumber;
    }

    @Override
    public void interact(IRobot robot) {
        // Sjekk om riktig flag, tell at flag er tatt
    }
}
