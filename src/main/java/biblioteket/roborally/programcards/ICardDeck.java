package biblioteket.roborally.programcards;

import java.util.ArrayList;

public interface ICardDeck {

    /**
     * Get as many cards as specified,
     * from the conceptual "top of the draw pile".
     *
     * @param number how many cards to draw.
     * @return list with as many cards as specified.
     */
    ArrayList<ICard> drawCards(int number);

}
