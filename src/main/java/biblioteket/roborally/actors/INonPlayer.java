package biblioteket.roborally.actors;

import biblioteket.roborally.programcards.ICardDeck;

public interface INonPlayer extends IActor {
    /**
     * An AI doesn't care about clicking to get cards, it simply draws them through
     * what is called an API.
     *
     * @param deck The deck to choose cards from
     */
    void chooseCards(ICardDeck deck);
}
