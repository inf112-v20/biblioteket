package biblioteket.roborally.programcards;

import biblioteket.roborally.actors.IPlayer;
import biblioteket.roborally.actors.IRobot;

public class Card implements ICard {
    private final CardType type;
    private final int priorityNumber;
    private final static int RENDERING_DELAY = 500;

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

    @Override
    public void doCardAction(IPlayer player) {
        IRobot robot = player.getRobot();
        switch (type) {
            case ROTATE_LEFT:
                player.rotateRobot(false, RENDERING_DELAY);
                break;
            case ROTATE_RIGHT:
                player.rotateRobot(true, RENDERING_DELAY);
                break;
            case U_TURN:
                player.rotateRobot(true, RENDERING_DELAY);
                player.rotateRobot(true, RENDERING_DELAY);
                break;
            case MOVE_1:
                player.moveRobot(1, RENDERING_DELAY);
                break;
            case MOVE_2:
                player.moveRobot(2, RENDERING_DELAY);
            case MOVE_3:
                player.moveRobot(3, RENDERING_DELAY);
            case BACK_UP:
                player.backUpRobot(RENDERING_DELAY);
            default:
                break;
        }
    }

    /**
     * @return a copy of current card
     */
    @Override
    public ICard copy() {
        return new Card(getType(), getPriorityNumber());
    }

    @Override
    public String toString() {
        return "Type= " + type +
                " Priority number= " + priorityNumber;
    }

}
