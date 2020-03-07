package biblioteket.roborally.programcards;

import biblioteket.roborally.actors.IRobot;

public class Card implements ICard {
    private CardType type;
    private int priorityNumber;

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

    @Override
    public String toString() {
        return "Type= " + type +
                " Priority number= " + priorityNumber;
    }
}
