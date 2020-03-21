package biblioteket.roborally.elements;

import biblioteket.roborally.actors.IPlayer;

public class DoubleWrenchRepairElement extends SingleWrenchRepairElement {

    /**
     * Robot that ends turn on a double wrench repair station discards one damage token and picks up an option card
     * @param player the player with the robot on current cell
     */
    @Override
    public void interact(IPlayer player){
        player.getRobot().removeDamageTokens(1);
        // Player picks up option card

        System.out.println(
                "Robot at " + player.getRobot().getPosition().getX() + ","
                + player.getRobot().getPosition().getY() + "Discards one damage token");
    }

}
