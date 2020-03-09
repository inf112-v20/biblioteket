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
        /*
        switch(type){
            case ROTATE_LEFT: robot.turnLeft();
                break;
            case ROTATE_RIGHT: robot.turnRight();
                break;
            case U_TURN: {
                robot.turnLeft();
                robot.turnLeft();
            }
                break;
            case MOVE_1: robot.moveForward();
                break;
            case MOVE_2: {
                robot.moveForward();
                robot.moveForward();
            }
                break;
            case MOVE_3: {
                robot.moveForward();
                robot.moveForward();
                robot.moveForward();
            }
                break;
            case BACK_UP: robot.moveBackward();
                break;
        }
         */
    }

    @Override
    public String toString() {
        return "Type= " + type +
                " Priority number= " + priorityNumber;
    }
}
