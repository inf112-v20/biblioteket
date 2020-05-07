package biblioteket.roborally.elements.interacting.cogs;

import biblioteket.roborally.actors.IActor;

public class LeftRotatingCogElement extends CogElement {

    @Override
    public void interact(IActor player) {
        player.rotateRobot(false, 500);
    }

    @Override
    public String toString() {
        return "Left rotating cog";
    }
}
