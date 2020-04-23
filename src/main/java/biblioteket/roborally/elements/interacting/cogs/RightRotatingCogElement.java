package biblioteket.roborally.elements.interacting.cogs;

import biblioteket.roborally.actors.IActor;

public class RightRotatingCogElement extends CogElement {

    @Override
    public void interact(IActor player) {
        player.rotateRobot(true, 500);
    }

    @Override
    public String toString() {
        return "Right rotating cog";
    }
}
