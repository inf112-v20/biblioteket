package biblioteket.roborally.elements;

import biblioteket.roborally.actors.IPlayer;

public class SingleWrenchRepairElement implements InteractingElement {

    /**
     * Robot that ends turn on a repair station discards one damage token
     * @param player the player with the robot on current cell
     */
    @Override
    public void interact(IPlayer player) {
        player.getRobot().removeDamageTokens(1);

        System.out.println(
                "Robot at " + player.getRobot().getPosition().getX() + ","
                + player.getRobot().getPosition().getY() + "Discards one damage token");
    }
}
