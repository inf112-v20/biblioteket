package biblioteket.roborally.programcards;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <strong>NOTE:</strong> Make note of the max number of cards, if you add
 * or remove cards to the ProgramCards.csv file the tests will fail.
 */
class CardDeckTest {
    private final int maxNumberOfCards = 84; //default number of cards
    private CardDeck cardDeck;

    @BeforeEach
    void setUp() {
        try {
            cardDeck = new CardDeck();
        } catch (IOException ignored) {
            System.err.println("Could not load card decks, make sure the ProgramCards.csv file is in the assets directory");
        }
    }

    @Test
    void drawOneCard() {
        ArrayList<ICard> drawnCards = cardDeck.drawCards(1);
        assertEquals(1, drawnCards.size());
    }

    @Test
    void drawAllCards() {
        ArrayList<ICard> drawnCards = cardDeck.drawCards(maxNumberOfCards);
        assertEquals(maxNumberOfCards, drawnCards.size());
    }

    @Test
    void firstDrawAllCardsThen1() {
        ArrayList<ICard> discardPile = cardDeck.drawCards(maxNumberOfCards);
        assertEquals(maxNumberOfCards, discardPile.size());
        for (ICard card : discardPile) {
            cardDeck.addToDiscardPile(card);
        }
        ArrayList<ICard> drawnCards2 = cardDeck.drawCards(1);
        assertEquals(1, drawnCards2.size());
    }

    @Test
    void drawHalfOfCardsThenHalfPlusOneCard() {
        ArrayList<ICard> discardPile1 = cardDeck.drawCards(maxNumberOfCards / 2);
        assertEquals(maxNumberOfCards / 2, discardPile1.size());
        for (ICard card : discardPile1) {
            cardDeck.addToDiscardPile(card);
        }
        ArrayList<ICard> discardPile2 = cardDeck.drawCards(maxNumberOfCards / 2 + 1);
        assertEquals(maxNumberOfCards / 2 + 1, discardPile2.size());
    }
}