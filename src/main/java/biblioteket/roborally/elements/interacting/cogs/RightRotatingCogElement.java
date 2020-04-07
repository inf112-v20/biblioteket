package biblioteket.roborally.elements.interacting.cogs;

import biblioteket.roborally.actors.IPlayer;
import biblioteket.roborally.actors.IRobot;

public class RightRotatingCogElement extends CogElement {

    @Override
    public void interact(IPlayer player) {
        player.rotateRobot(true, 500);
    }

    @Override
    public String toString() {
        return "Right rotating cog";
    }
}
