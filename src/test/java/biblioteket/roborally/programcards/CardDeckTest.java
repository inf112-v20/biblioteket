package biblioteket.roborally.programcards;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardDeckTest {
    private CardDeck cardDeck;
    private final int maxNumberOfCards = 84;

    @BeforeEach
    void setUp() throws IOException {
        cardDeck = new CardDeck();
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
        ArrayList<ICard> drawnCards = cardDeck.drawCards(maxNumberOfCards);
        assertEquals(maxNumberOfCards, drawnCards.size());

        ArrayList<ICard> drawnCards2 = cardDeck.drawCards(1);
        assertEquals(1, drawnCards2.size());
    }

}