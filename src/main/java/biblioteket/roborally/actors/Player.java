package biblioteket.roborally.actors;

import biblioteket.roborally.board.DirVector;
import biblioteket.roborally.board.Direction;
import biblioteket.roborally.board.IBoard;
import biblioteket.roborally.programcards.ICard;
import biblioteket.roborally.programcards.ICardDeck;
import biblioteket.roborally.userinterface.InterfaceRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.ArrayList;
import java.util.List;

public class Player implements IPlayer {
    private final IBoard board;
    private final TiledMapTileLayer.Cell playerCell;

    private final ArrayList<ICard> programRegister;

    private final InterfaceRenderer interfaceRenderer;
    private final RobotRenderer robotRenderer;

    private int lives = 3;
    private int visitedFlags = 0;
    private IRobot robot;
    private ArrayList<ICard> drawnCards;
    private int lockedRegisters = 0;

    private boolean canMove = true;

    public Player(IBoard board, TiledMapTileLayer.Cell playerCell, InterfaceRenderer interfaceRenderer, RobotRenderer robotRenderer) {
        this.board = board;
        this.playerCell = playerCell;
        this.interfaceRenderer = interfaceRenderer;
        this.robotRenderer = robotRenderer;

        programRegister = new ArrayList<>();
        drawnCards = new ArrayList<>();
    }

    @Override
    public void moveRobot(int steps, int delay) {
        for (int i = 0; i < steps; i++) {
            moveRobot(robot.getDirection(), delay);
        }
    }

    @Override
    public void moveRobot(Direction direction, int delay) {
        if (canMove && board.canMove(robot.getPosition(), direction)) {
            DirVector oldPosition = robot.getPosition().copy();
            robot.pushRobotInDirection(direction);
            DirVector newPosition = robot.getPosition().copy();
            renderMove(oldPosition, newPosition, delay);
        }

        // Check if robot moved in hole or out of bounds
        handleRobotOutOfBounds(delay);
    }

    @Override
    public void backUpRobot(int delay) {
        moveRobot(robot.getDirection().opposite(), delay);
    }

    @Override
    public void rotateRobot(boolean right, int delay) {
        if (right) {
            robot.turnRight();
        } else {
            robot.turnLeft();
        }
        renderMove(robot.getPosition().copy(), robot.getPosition().copy(), delay);
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

    public void updateInterfaceRenderer() {
        interfaceRenderer.setFlagsVisited(getNumberOfVisitedFlags());
        interfaceRenderer.setLives(getLives());
        interfaceRenderer.clearProgramRegister();
    }

    public void drawCards(ICardDeck cardDeck) { //Signals start of new round, check damage clean register.
        int defaultNumber = 9; //default number of cards to draw
        int damageTokens = robot.getNumberOfDamageTokens();
        int cardsToDraw = defaultNumber - damageTokens;
        if (!drawnCards.isEmpty())
            for (ICard card : drawnCards)
                cardDeck.addToDiscardPile(card);

        if (!programRegister.isEmpty()) {// Should stop it from breaking first round
            cleanRegister(damageTokens, cardDeck);
            updateRegisterRender();
        }

        drawnCards = cardDeck.drawCards(cardsToDraw);
        interfaceRenderer.setCardHand(drawnCards);
    }

    /**
     * Cleans the register based on damage tokens.
     *
     * @param damageTokens how many damage tokens the players robot has accumulated.
     * @param cardDeck     The cardDeck used in the game.
     */
    private void cleanRegister(int damageTokens, ICardDeck cardDeck) {
        switch (damageTokens) {
            case 9:
                lockedRegisters = 5;
                break;
            case 8:
                lockedRegisters = 4;
                cardDeck.removeFromRegisterPile(programRegister.remove(programRegister.size() - 1));
                break;
            case 7:
                lockedRegisters = 3;
                cardDeck.removeFromRegisterPile(programRegister.remove(programRegister.size() - 1));
                cardDeck.removeFromRegisterPile(programRegister.remove(programRegister.size() - 1));
                break;
            case 6:
                lockedRegisters = 2;
                cardDeck.removeFromRegisterPile(programRegister.remove(programRegister.size() - 1));
                cardDeck.removeFromRegisterPile(programRegister.remove(programRegister.size() - 1));
                cardDeck.removeFromRegisterPile(programRegister.remove(programRegister.size() - 1));
                break;
            case 5:
                lockedRegisters = 1;
                cardDeck.removeFromRegisterPile(programRegister.remove(programRegister.size() - 1));
                cardDeck.removeFromRegisterPile(programRegister.remove(programRegister.size() - 1));
                cardDeck.removeFromRegisterPile(programRegister.remove(programRegister.size() - 1));
                cardDeck.removeFromRegisterPile(programRegister.remove(programRegister.size() - 1));
                break;
            default:
                for (ICard card : programRegister)
                    cardDeck.removeFromRegisterPile(card);
                programRegister.clear();
                break;
        }
    }

    /**
     * Updated the register, so that locked cards are placed correctly
     */
    private void updateRegisterRender() {
        interfaceRenderer.clearProgramRegister();
        if (!programRegister.isEmpty()) {
            int place = 4;
            for (ICard card : programRegister) {
                interfaceRenderer.addCardToLockedRegister(card, place--);
            }
        }
    }

    @Override
    public void newTurn(ICardDeck cardDeck) {
        drawCards(cardDeck);
        canMove = true;
        updateInterfaceRenderer();
    }

    @Override
    public void addCardToProgramRegister(ICard card, ICardDeck cardDeck) {
        drawnCards.remove(card);
        cardDeck.addToRegisterPile(card);
        interfaceRenderer.addCardToProgramRegisterIndex(card, programRegister.size() - lockedRegisters);
        programRegister.add(lockedRegisters, card);
    }

    @Override
    public List<ICard> getProgramRegister(ICardDeck cardDeck) { //Used in game loop to execute moves.
        return new ArrayList<>(programRegister);
    }


    @Override
    public boolean fullProgramRegister() {
        return programRegister.size() == 5;
    }

    /**
     * Requests robotRenderer to render one move
     *
     * @param from position robot is moving from
     * @param to   position robot is moving to
     */
    private void renderMove(DirVector from, DirVector to, int delay) {
        robotRenderer.requestRendering(from, to, robot.getDirection(), delay, playerCell);
    }

    /**
     * If robot is out of bounds, moves robot to archive marker and removes one life.
     */
    private void handleRobotOutOfBounds(int delay) {
        DirVector position = robot.getPosition();
        if (board.outOfBounds(position) || board.isHole(position)) {
            DirVector oldPosition = robot.getPosition().copy();
            robot.moveToArchiveMarker();
            DirVector newPosition = robot.getPosition().copy();
            renderMove(oldPosition, newPosition, delay);
            canMove = false;
            removeOneLife();
        }
    }
}