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

    /**
     * Discards card, by placing it in a discard cards pile
     * (Could just put it in the end of the draw pile, but then the order would be predictable.)
     *
     * @param card card to be discarded
     */
    void addToDiscardPile(ICard card);

    /**
     * Add card to register pile.
     *
     * @param card card in register
     */
    void addToRegisterPile(ICard card);

    /**
     * Remove card from register pile,
     * and places it in discard pile.
     * @param card card to be removed.
     */
    void removeFromRegisterPile(ICard card);
}
