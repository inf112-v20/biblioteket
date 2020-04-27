package biblioteket.roborally.elements.interacting;

import biblioteket.roborally.actors.IActor;
import com.badlogic.gdx.Gdx;

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
    public void interact(IActor player) {
        int visited = player.getNumberOfVisitedFlags();
        if (flagNumber - 1 == visited) { // Check if player has picked up all previous flags
            Gdx.app.log(player.getName(), " picked up flag " + flagNumber);
            player.addToFlagsVisited();
            player.getRobot().setArchiveMarker(player.getRobot().getPosition());
        }
    }

    @Override
    public String toString() {
        return "Flag";
    }

    @Override
    public int getPriority() {
        return 2;
    }

}
