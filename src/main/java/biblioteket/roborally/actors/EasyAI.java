package biblioteket.roborally.actors;

import biblioteket.roborally.board.IBoard;
import biblioteket.roborally.programcards.ICard;
import biblioteket.roborally.programcards.ICardDeck;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.List;
import java.util.Random;

public class EasyAI extends Actor implements INonPlayer {
    public EasyAI(IBoard board, TiledMapTileLayer.Cell playerCell, InterfaceRenderer interfaceRenderer, RobotRenderer robotRenderer) {
        super(board, playerCell, interfaceRenderer, robotRenderer);
    }

    @Override
    public void chooseCards(ICardDeck deck) {
        List<ICard> cards = deck.drawCards(9);
        while (!super.fullProgramRegister()) {
            int id = new Random().nextInt(cards.size());
            super.addCardToProgramRegister(cards.get(id), deck);
            cards.remove(id);
        }
    }
}
