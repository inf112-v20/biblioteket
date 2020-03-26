package biblioteket.roborally.programcards;

import biblioteket.roborally.actors.IRobot;
import biblioteket.roborally.board.IBoard;

import java.util.Comparator;
import java.util.Objects;

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
    public void doCardAction(IRobot robot, IBoard board) {
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
                robot.move(robot.getDirection(), board);
                break;
            case MOVE_2:
                robot.move(robot.getDirection(),board);
                //if (robot.getPlayer().hasLivesLeft())
                    robot.moveForward(board);
                break;
            case MOVE_3:
                robot.move(robot.getDirection(), board);
                //if (robot.getPlayer().hasLivesLeft())
                robot.move(robot.getDirection(), board);
                //if (robot.getPlayer().hasLivesLeft())
                robot.move(robot.getDirection(), board);
                break;
            case BACK_UP:
                robot.move(robot.getDirection().opposite(), board);
                break;
            default:
                break;
        }
    }

    @Override
    public ICard clone() {
        ICard clone = new Card(getType(), getPriorityNumber());
        return clone;
    }

    @Override
    public String toString() {
        return "Type= " + type +
                " Priority number= " + priorityNumber;
    }

}
