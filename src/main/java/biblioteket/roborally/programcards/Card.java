package biblioteket.roborally.programcards;

import biblioteket.roborally.actors.IPlayer;
import biblioteket.roborally.actors.IRobot;

public class Card implements ICard {
    private final CardType type;
    private final int priorityNumber;

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
                robot.turnLeft();
                break;
            case ROTATE_RIGHT:
                robot.turnRight();
                break;
            case U_TURN:
                robot.turnLeft();
                robot.turnLeft();
                break;
            case MOVE_1:
                player.moveRobot(robot.getDirection());
                break;
            case MOVE_2:
                player.moveRobot(robot.getDirection());
                player.moveRobot(robot.getDirection());
                break;
            case MOVE_3:
                player.moveRobot(robot.getDirection());
                player.moveRobot(robot.getDirection());
                player.moveRobot(robot.getDirection());
            case BACK_UP:
                player.moveRobot(robot.getDirection().opposite());
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
