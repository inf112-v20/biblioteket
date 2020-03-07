package biblioteket.roborally.programcards;

import java.util.ArrayList;

public interface ICardDeck {


    /**
     * Generate the sub deck of given card type
     *
     * @param type          which card type the sub deck is of
     * @param priorityStart where to start priority for sub deck
     * @param priorityEnd   where to end priority for sub deck
     * @param step          how much to add to get to next priority number
     * @return sub deck of given type
     */
    ArrayList<ICard> generateCards(CardType type, int priorityStart, int priorityEnd, int step);

    /**
     * Get as many cards as specified,
     * from the conceptual "top of the draw pile".
     *
     * @param number
     * @return list with as many cards as specified.
     */
    ArrayList<ICard> drawCards(int number);

}
