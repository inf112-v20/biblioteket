package biblioteket.roborally.actors;

import biblioteket.roborally.board.DirVector;
import biblioteket.roborally.board.Direction;
import biblioteket.roborally.board.IBoard;
import biblioteket.roborally.elements.interacting.InteractingElement;
import biblioteket.roborally.programcards.ICard;
import biblioteket.roborally.programcards.ICardDeck;
import biblioteket.roborally.userinterface.InterfaceRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.ArrayList;
import java.util.List;

public class Player implements IPlayer {
    private final IBoard board;
    private final TiledMapTileLayer.Cell playerCell;

    private ArrayList<ICard> programRegister;

    private InterfaceRenderer interfaceRenderer;
    private RobotRenderer robotRenderer;

    private int lives = 3;
    private int visitedFlags = 0;
    private IRobot robot;

    public Player(IBoard board, TiledMapTileLayer.Cell playerCell, InterfaceRenderer interfaceRenderer, RobotRenderer robotRenderer) {
        this.board = board;
        this.playerCell = playerCell;
        this.interfaceRenderer = interfaceRenderer;
        this.robotRenderer = robotRenderer;

        programRegister = new ArrayList<>();
    }

    @Override
    public void moveRobot(Direction direction){
        if(board.canMove(robot.getPosition(), direction)){
            DirVector oldPosition = robot.getPosition().copy();
            robot.pushRobotInDirection(direction);
            DirVector newPosition = robot.getPosition().copy();
            renderMove(oldPosition, newPosition);
        }

        // Check if robot moved in hole or out of bounds
        handleRobotOutOfBounds();
    }

    /**
     * Requests robotRenderer to render one move
     *
     * @param from position robot is moving from
     * @param to position robot is moving to
     */
    private void renderMove(DirVector from, DirVector to){
        robotRenderer.requestRendering(from,to, playerCell);
    }

    /**
     * If robot is out of bounds, moves robot to archive marker and removes one life.
     */
    private void handleRobotOutOfBounds(){
        DirVector position = robot.getPosition();
        if(board.outOfBounds(position) || board.isHole(position)){
            DirVector oldPosition = robot.getPosition().copy();
            robot.moveToArchiveMarker();
            DirVector newPosition = robot.getPosition().copy();
            renderMove(oldPosition,newPosition);
            removeOneLife();
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

    @Override
    public InterfaceRenderer getInterfaceRenderer() {
        return interfaceRenderer;
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