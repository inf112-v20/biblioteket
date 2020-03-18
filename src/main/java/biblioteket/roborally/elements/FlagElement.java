package biblioteket.roborally.elements;

import biblioteket.roborally.actors.IPlayer;
import biblioteket.roborally.actors.IRobot;
import biblioteket.roborally.board.Direction;

public class FlagElement implements InteractingElement {
    private final int flagNumber;

    public FlagElement(int flagNumber) {
        this.flagNumber = flagNumber;
    }

    /**
     * If correct flag, let player register flag
     *
     * @param player with robot at flag
     */
    @Override
    public void interact(IPlayer player) {
        int visited = player.getNumberOfVisitedFlags();
        if(flagNumber - 1 == visited) { // Check if player has picked up all previous flags
            System.out.println("Picked up flag " + flagNumber);
            player.addToFlagsVisited();
        }
    }

    public int getFlagNumber() {
        return this.flagNumber;
    }

    @Override
    public String toString() {
        return "Flag";
    }

}
