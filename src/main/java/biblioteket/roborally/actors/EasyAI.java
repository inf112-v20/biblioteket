package biblioteket.roborally.actors;

import biblioteket.roborally.board.Element;
import biblioteket.roborally.board.IBoard;
import biblioteket.roborally.elements.IElement;
import biblioteket.roborally.elements.interacting.FlagElement;
import biblioteket.roborally.programcards.Card;
import biblioteket.roborally.userinterface.InterfaceRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.ArrayList;
import java.util.List;

public class EasyAI extends Actor implements INonPlayer {
    List<IElement> flags;

    public EasyAI(IBoard board, TiledMapTileLayer.Cell playerCell, InterfaceRenderer interfaceRenderer, RobotRenderer robotRenderer) {
        super(board, playerCell, interfaceRenderer, robotRenderer);
        flags = new ArrayList<>();
        this.findFlags(board);
    }

    private void findFlags(IBoard board) {
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                try {
                    int id = board.getGroundLayer().getCell(x, y).getTile().getId();
                    IElement element = Element.getInteractiveElement(id);
                    if (element instanceof FlagElement) {
                        flags.add(element);
                    }
                } catch (Exception ignored) {
                    // Ignore error because the getId() function throws.
                }
            }
        }
    }

    @Override
    public List<Card> drawnCards() {
        return null;
    }
}
