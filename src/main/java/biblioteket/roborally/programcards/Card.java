package biblioteket.roborally.programcards;

import biblioteket.roborally.actors.IRobot;

public class Card implements ICard {
    CardType type;
    int priorityNumber;

    public Card(CardType type, int priorityNumber) {
        this.type = type;
        this.priorityNumber = priorityNumber;
    }

    @Override
    public CardType getType() {
        return type;
    }

    @Override
    public int getPriorityNumber() {
        return priorityNumber;
    }

    //TODO have made an test-class but not started to implement.
    @Override
    public void doCardAction(IRobot robot) {

    }
}
