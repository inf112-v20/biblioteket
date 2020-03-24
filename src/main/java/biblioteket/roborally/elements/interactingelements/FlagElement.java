package biblioteket.roborally.elements.interactingelements;

import biblioteket.roborally.actors.IPlayer;

public class FlagElement implements InteractingElement {
    private final int priority = 2;
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
            player.getRobot().setArchiveMarker(player.getRobot().getPosition());
        }
    }

    @Override
    public String toString() {
        return "Flag";
    }

    @Override
    public int getPriority(){
        return priority;
    }

}
