package biblioteket.roborally.actors;

import biblioteket.roborally.programcards.ICardDeck;

public interface INonPlayer extends IActor {
    void chooseCards(ICardDeck deck);
}
