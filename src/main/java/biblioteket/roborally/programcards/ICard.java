package biblioteket.roborally.programcards;

import biblioteket.roborally.actors.IActor;

public interface ICard {

    /**
     * Get which type the card is
     *
     * @return which type the card is
     */
    CardType getType();

    /**
     * Get the priority number of the card
     *
     * @return priority number of card
     */
    int getPriorityNumber();

    /**
     * Make the robot do the action of the program card
     *
     * @param player player to interact with
     */
    void doCardAction(IActor player);

    /**
     * @return a copy of this card
     */
    ICard copy();
}
