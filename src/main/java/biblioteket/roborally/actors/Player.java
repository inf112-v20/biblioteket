package biblioteket.roborally.actors;

import biblioteket.roborally.board.DirVector;
import biblioteket.roborally.board.Direction;
import biblioteket.roborally.board.IBoard;
import biblioteket.roborally.programcards.ICard;
import biblioteket.roborally.programcards.ICardDeck;
import biblioteket.roborally.userinterface.InterfaceRenderer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.ArrayList;
import java.util.List;

public class Player implements IPlayer {
    private final TiledMapTileLayer.Cell playerCell;
    private final ArrayList<ICard> programRegister;
    private final InterfaceRenderer interfaceRenderer;
    private final RobotRenderer robotRenderer;
    private int lives = 3;
    private int visitedFlags = 0;
    private IRobot robot;
    private IBoard board;

    public Player(TiledMapTileLayer.Cell playerCell, InterfaceRenderer interfaceRenderer, IBoard board) {
        this.playerCell = playerCell;
        this.interfaceRenderer = interfaceRenderer;
        this.board = board;
        programRegister = new ArrayList<>();
        robotRenderer = new RobotRenderer(board.getPlayerLayer(), playerCell);
    }

    public void moveRobot(Direction direction){
        IRobot robot = getRobot();
        if(board.canMove(robot.getPosition(), direction)){
            DirVector oldPosition = robot.getPosition().copy();
            robot.pushRobotInDirection(direction);
            DirVector newPosition = robot.getPosition().copy();
            robotRenderer.requestRendering(oldPosition,newPosition);
        }
    }


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
        this.lives -= 1;
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

    @Override
    public TiledMapTileLayer.Cell getPlayerCell() {
        return playerCell;
    }

    @Override
    public InterfaceRenderer getInterfaceRenderer() {
        return interfaceRenderer;
    }

    @Override
    public RobotRenderer getRobotRenderer() {
        return robotRenderer;
    }

    @Override
    public void updateInterfaceRenderer() {
        interfaceRenderer.setFlagsVisited(getNumberOfVisitedFlags());
        interfaceRenderer.setLives(getLives());
        interfaceRenderer.clearProgramRegister();
    }

    @Override
    public void drawCards(ICardDeck cardDeck) {
        ArrayList<ICard> cards = cardDeck.drawCards(9);
        interfaceRenderer.setCardHand(cards);
    }

    @Override
    public void addCardToProgramRegister(ICard card) {
        programRegister.add(card);
        interfaceRenderer.addCardToProgramRegister(card);
    }

    @Override
    public List<ICard> getProgramRegister() {
        List<ICard> programCards = new ArrayList<>(programRegister);
        programRegister.clear();
        return programCards;
    }


    @Override
    public boolean fullProgramRegister() {
        return programRegister.size() == 5;
    }


}