package biblioteket.roborally.elements;

import biblioteket.roborally.actors.IRobot;
import biblioteket.roborally.board.Direction;

public class FlagElement implements InteractingElement {
    private final int flagNumber;

    public FlagElement(int flagNumber) {
        this.flagNumber = flagNumber;
    }

    /**
     * If correct flag, let player register flag
     * @param robot
     */
    @Override
    public void interact(IRobot robot) {
        // Sjekk om riktig flag, tell at flag er tatt
    }

    public int getFlagNumber(){
        return this.flagNumber;
    }

    @Override
    public String toString(){
        return "Flag";
    }

    @Override
    public boolean blockingExit(Direction direction) {
        return false;
    }

    @Override
    public boolean blockingEntry(Direction direction) {
        return false;
    }
}
