package biblioteket.roborally.game;

import biblioteket.roborally.actors.IPlayer;
import biblioteket.roborally.board.Board;
import biblioteket.roborally.board.DirVector;
import biblioteket.roborally.elements.IElement;
import biblioteket.roborally.elements.interactingelements.InteractingElement;
import biblioteket.roborally.elements.interactingelements.cogs.CogElement;
import biblioteket.roborally.elements.interactingelements.conveyorbelts.ConveyorBeltElement;
import biblioteket.roborally.elements.interactingelements.conveyorbelts.ExpressConveyorBeltElement;
import biblioteket.roborally.elements.walls.LaserWallElement;

import java.util.List;

/**
 * Executes board interactions with robot
 */
public class GameLoop {
    private final Board board;
    private final int amountOfFlags;
    private final List<LaserWallElement> laserWalls;
    private final List<IPlayer> players;

    public GameLoop(Board board, List<IPlayer> players) {
        this.board = board;
        this.players = players;
        this.amountOfFlags = board.getNumFlags();
        this.laserWalls = board.getLaserWalls();
    }

    public void doTurn() {

        // Express conveyor belts move first
        interactWithBoardElement(ExpressConveyorBeltElement.class);

        // All conveyor belts move second
        interactWithBoardElement(ConveyorBeltElement.class);

        // Gears rotate
        interactWithBoardElement(CogElement.class);

        // Lasers shoot
        for (LaserWallElement laserWall : laserWalls) {
            laserWall.interact(board, players);
        }

        // Interact with priority 2 elements
        for (IPlayer player : players) {
            InteractingElement element = board.getInteractingElement(player.getRobot().getPosition());
            if (element != null && element.getPriority() == 2) {
                board.interact(player);
            }
        }


//         Register flags
        for (IPlayer player : players) {
            board.registerFlag(player);
        }

    }

    public boolean checkWinCondition() {
        for (IPlayer player : players) {
            if (player.getNumberOfVisitedFlags() == amountOfFlags) return true;
        }
        return false;
    }

    private void interactWithBoardElement(Class<? extends InteractingElement> instance) {
        for (IPlayer player : players) {
            DirVector position = player.getRobot().getPosition();
            IElement element = board.getInteractingElement(position);
            if (instance.isInstance(element)) {
                board.interact(player);
            }
        }
    }

}
