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
        int defaultNumber = 9; //default number of cards to draw
        int damageTokens = super.getRobot().getNumberOfDamageTokens();
        int cardsToDraw = defaultNumber - damageTokens;

        List<ICard> cards = deck.drawCards(cardsToDraw);
        while (!super.fullProgramRegister()) {
            int id = new Random().nextInt(cards.size());
            super.addCardToProgramRegister(cards.get(id), deck);
            deck.addToDiscardPile(cards.remove(id));
        }
    }
}
