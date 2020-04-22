package biblioteket.roborally.programcards;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CardDeck implements ICardDeck {
    // Cards get drawn then either placed in the register or discarded
    private final ArrayList<ICard> drawPile;
    private final ArrayList<ICard> registerPile;
    private final ArrayList<ICard> discardPile;

    /**
     * Makes a pile of cards
     *
     * @throws IOException Logg will say something went wrong.
     */
    public CardDeck() throws IOException {
        drawPile = new ArrayList<>();
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("assets/ProgramCards.csv"))) {
            while ((line = bufferedReader.readLine()) != null) {
                line = line.replace("\uFEFF", ""); //Have to take away an invisible space.
                String[] cardInfo = line.split(",");
                CardType type = CardType.valueOf(cardInfo[0]);
                int priorityNum = Integer.parseInt(cardInfo[1]);
                ICard card = new Card(type, priorityNum);
                drawPile.add(card);
            }
        } catch (IOException e) {
            String message = "Something went wrong.";
            Logger logger = Logger.getLogger(CardDeck.class.getName());
            logger.log(Level.SEVERE, message, e);

            throw new IOException(message, e);
        }
        Collections.shuffle(drawPile);
        registerPile = new ArrayList<>();
        discardPile = new ArrayList<>();
    }

    @Override
    public ArrayList<ICard> drawCards(int number) {
        ArrayList<ICard> drawnCards = new ArrayList<>();
        for (int i = 0; i < number; i++)
            drawnCards.add(drawCard());
        return drawnCards;
    }

    /**
     * Get card from top of the draw pile.
     * If draw pile is empty the discard pile is shuffled and made to new draw pile.
     *
     * @return one card
     */
    private ICard drawCard() {
        if (drawPile.isEmpty() && discardPile.isEmpty())
            throw new IllegalStateException("No cards to draw, it is possible that the drawCards method is not followed by addToDiscardPile or that all the cards are stuck in the register.");

        if (drawPile.isEmpty()) {
            Collections.shuffle(discardPile);
            drawPile.addAll(discardPile);
            discardPile.clear();
        }
        return drawPile.remove(0);
    }

    @Override
    public void addToDiscardPile(ICard card) {
        discardPile.add(card);
    }

    @Override
    public void addToRegisterPile(ICard card) {
        registerPile.add(card);
    }

    @Override
    public void removeFromRegisterPile(ICard card) {
        if ((drawPile.size() + discardPile.size() + registerPile.size()) != 84) {
            System.out.println("OBS, CardDeck: Not correct amount of cards " + (drawPile.size() + discardPile.size() + registerPile.size()));
        }
        registerPile.remove(card);
        addToDiscardPile(card);

    }
}
