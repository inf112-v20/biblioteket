package biblioteket.roborally.elements.interacting.cogs;

import biblioteket.roborally.actors.IPlayer;
import biblioteket.roborally.actors.IRobot;

public class LeftRotatingCogElement extends CogElement {

    @Override
    public void interact(IPlayer player) {
        player.rotateRobot(false, 500);
    }

    @Override
    public String toString() {
        return "Left rotating cog";
    }
}
