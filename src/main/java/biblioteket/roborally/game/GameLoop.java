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

/**
 * Executes board interactions with robot
 */
public class GameLoop {
    Board board;
    ArrayList<IPlayer> players;
    ArrayList<Class<? extends InteractingElement>> interactingElementInstances;

    public GameLoop(Board board, ArrayList<IPlayer> players){
        this.board = board;
        this.players = players;

        interactingElementInstances = new ArrayList<>();
        interactingElementInstances.add(ExpressConveyorBeltElement.class);
        interactingElementInstances.add(ConveyorBeltElement.class);
        interactingElementInstances.add(CogElement.class);
    }

    public void doTurn(){
        for(Class instance : interactingElementInstances){
            for(IPlayer player : players){
                DirVector position = player.getRobot().getPosition();
                IElement element = board.getInteractingElement(position);

                if(instance.isInstance(element)){
                    board.interact(player);
                }
            }
        }
    }

}
