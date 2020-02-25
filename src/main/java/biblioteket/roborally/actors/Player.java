package biblioteket.roborally.actors;

public class Player implements IPlayer {
    private int lives = 3;
    private int visitedFlags = 0;

    private IRobot robot;

    @Override
    public int getLives() {
        return lives;
    }

    @Override
    public boolean isPermanentDead() {
        return lives <= 0;
    }

    @Override
    public boolean hasLivesLeft() {
        return lives > 0;
    }

    @Override
    public void removeOneLife() {
        lives--;
    }

    @Override
    public IRobot getRobot() {
        return robot;
    }

    @Override
    public void setRobot(IRobot robot) {
        this.robot = robot;
    }

    @Override
    public int getNumberOfVisitedFlags() {
        return visitedFlags;
    }

    @Override
    public void addToFlagsVisited() {
        visitedFlags++;
    }
}