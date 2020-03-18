package biblioteket.roborally.game;

import biblioteket.roborally.actors.IPlayer;
import biblioteket.roborally.actors.IRobot;
import biblioteket.roborally.actors.Robot;
import biblioteket.roborally.board.Board;
import biblioteket.roborally.board.DirVector;
import biblioteket.roborally.elements.*;
import biblioteket.roborally.elements.cogs.CogElement;
import biblioteket.roborally.elements.conveyorbelts.ConveyorBeltElement;
import biblioteket.roborally.elements.conveyorbelts.ExpressConveyorBeltElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Executes board interactions with robot
 */
public class GameLoop {
    private final Board board;
    private List<IPlayer> players;
    private ArrayList<Class<? extends InteractingElement>> interactingElementInstances;
    private final int amountOfFlags;

    public GameLoop(Board board, List<IPlayer> players){
        this.board = board;
        this.players = players;
        this.amountOfFlags = board.getNumFlags();

        interactingElementInstances = new ArrayList<>();
        interactingElementInstances.add(ExpressConveyorBeltElement.class);
        interactingElementInstances.add(ConveyorBeltElement.class);
        interactingElementInstances.add(CogElement.class);
    }

    /**
     *
     * @return true if one player has collected all flags
     */
    public void doTurn(){
        // Conveyor belts and cogs interact
        for(Class instance : interactingElementInstances){
            for(IPlayer player : players){
                DirVector position = player.getRobot().getPosition();
                IElement element = board.getInteractingElement(position);

                if(instance.isInstance(element)){
                    board.interact(player);
                }
            }
        }

        // Register flags
        for (IPlayer player : players) {
            board.registerFlag(player);
        }

        // Lasers shoot
    }

    public boolean checkWinCondition(){
        for (IPlayer player : players){
            if(player.getNumberOfVisitedFlags() == amountOfFlags) return true;
        }
        return false;
    }

}
