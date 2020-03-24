package biblioteket.roborally.elements.interactingelements;

import biblioteket.roborally.actors.IPlayer;
import biblioteket.roborally.actors.IRobot;

public class SingleWrenchRepairElement implements InteractingElement {
    private int priority = 2;

    /**
     * Robot that ends turn on a repair station discards one damage token
     *
     * @param player the player with the robot on current cell
     */
    @Override
    public void interact(IPlayer player) {
        IRobot robot = player.getRobot();
        robot.removeDamageTokens(1);
        robot.setArchiveMarker(robot.getPosition());

        System.out.println(
                "Robot at " + player.getRobot().getPosition().getX() + ","
                        + player.getRobot().getPosition().getY() + " discards one damage token");
    }

    @Override
    public int getPriority() {
        return priority;
    }

}
