package biblioteket.roborally.elements.interacting;

import biblioteket.roborally.actors.IActor;
import biblioteket.roborally.actors.IRobot;
import com.badlogic.gdx.Gdx;

public class SingleWrenchRepairElement implements InteractingElement {

    /**
     * Robot that ends turn on a repair station discards one damage token
     *
     * @param player the player with the robot on current cell
     */
    @Override
    public void interact(IActor player) {
        IRobot robot = player.getRobot();
        robot.removeDamageTokens(1);
        robot.setArchiveMarker(robot.getPosition());

        Gdx.app.log(player.getName(), " discards one damage token");
    }

    @Override
    public int getPriority() {
        return 2;
    }

}
