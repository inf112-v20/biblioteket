package biblioteket.roborally.programcards;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CardDeck implements ICardDeck {
    private final ArrayList<ICard> gameCardDeck;
    private int topOfDrawPile = 0; //Will change as cards are drawn

    public CardDeck() throws IOException {
        gameCardDeck = new ArrayList<>();
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("assets/ProgramCards.csv"))) {
            while ((line = bufferedReader.readLine()) != null) {
                line = line.replace("\uFEFF", ""); //Have to take away an invisible space.
                String[] cardInfo = line.split(",");
                CardType type = CardType.valueOf(cardInfo[0]);
                int priorityNum = Integer.parseInt(cardInfo[1]);
                ICard card = new Card(type, priorityNum);
                gameCardDeck.add(card);
            }
        } catch (IOException e) {
            String message = "Something went wrong.";
            Logger logger = Logger.getLogger(CardDeck.class.getName());
            logger.log(Level.SEVERE, message, e);

            throw new IOException(message, e);
        }
        Collections.shuffle(gameCardDeck);
    }

    @Override
    public ArrayList<ICard> drawCards(int number) {
        if (topOfDrawPile > gameCardDeck.size() - number) {
            topOfDrawPile = 0;
            Collections.shuffle(gameCardDeck);
        }

        ArrayList<ICard> drawnCards = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            drawnCards.add(gameCardDeck.get(topOfDrawPile++));
        }
        return drawnCards;

    }
}
