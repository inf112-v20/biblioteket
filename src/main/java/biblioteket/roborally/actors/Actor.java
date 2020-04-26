package biblioteket.roborally.actors;

import biblioteket.roborally.board.DirVector;
import biblioteket.roborally.board.Direction;
import biblioteket.roborally.board.IBoard;
import biblioteket.roborally.programcards.ICard;
import biblioteket.roborally.programcards.ICardDeck;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.ArrayList;
import java.util.List;

public class Actor implements IActor {
    private final IBoard board;
    private final TiledMapTileLayer.Cell playerCell;

    private final ArrayList<ICard> programRegister; // This is reversed, reg 5 is placed at 0

    private final InterfaceRenderer interfaceRenderer;
    private final RobotRenderer robotRenderer;

    private int lives = 3;
    private int visitedFlags = 0;
    private IRobot robot;
    private String name;
    private ArrayList<ICard> drawnCards;

    private boolean canMove = true;

    public Actor(IBoard board, TiledMapTileLayer.Cell playerCell, InterfaceRenderer interfaceRenderer, RobotRenderer robotRenderer) {
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
            moveRobot(robot.getDirection(), delay, false);
        }
    }

    @Override
    public boolean moveRobot(Direction direction, int delay, boolean debug) {
        if (canMove && board.canMove(robot.getPosition(), direction) && board.pushRobot(robot.getPosition(), direction)) {
            DirVector oldPosition = robot.getPosition().copy();
            robot.pushRobotInDirection(direction);
            DirVector newPosition = robot.getPosition().copy();
            renderMove(oldPosition, newPosition, delay, debug);
            // Check if robot moved in hole or out of bounds
            handleRobotOutOfBounds(delay, debug);
            return true;
        }
        return false;
    }

    @Override
    public void backUpRobot(int delay) {
        moveRobot(robot.getDirection().opposite(), delay, false);
    }

    @Override
    public void rotateRobot(boolean right, int delay) {
        if (!canMove) return;
        if (right) {
            robot.turnRight();
        } else {
            robot.turnLeft();
        }
        renderMove(robot.getPosition().copy(), robot.getPosition().copy(), delay, false);
    }

    @Override
    public int getLives() {
        return lives;
    }

    @Override
    public TiledMapTileLayer.Cell getPlayerCell() {
        return playerCell;
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
        if (lives <= 0) {
            robotRenderer.removePlayer(getRobot().getPosition(),playerCell);
        }
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
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
        interfaceRenderer.setName(name);
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
    }

    @Override
    public void drawCards(ICardDeck cardDeck) {
        int defaultNumber = 9; //default number of cards to draw
        int damageTokens = robot.getNumberOfDamageTokens();
        int cardsToDraw = defaultNumber - damageTokens;

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
                int lockedRegisters = 5;
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
                lockedRegisters = 0;
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
        int damageTokens = robot.getNumberOfDamageTokens();  // Check damage and clean register.
        if (!drawnCards.isEmpty()) // Should stop it from breaking first round
            for (ICard card : drawnCards) // adds the cards not uses last round to discard pile
                cardDeck.addToDiscardPile(card);
        if (!programRegister.isEmpty()) { // Should stop it from breaking first round
            cleanRegister(damageTokens, cardDeck); // Corrects the register
            updateRegisterRender(); // Render cards if they are locked in register
        }
        drawCards(cardDeck);
        canMove = true;
        updateInterfaceRenderer();
    }

    @Override
    public void addCardToProgramRegister(ICard card, ICardDeck cardDeck) {
        if (programRegister.contains(card)) {
            programRegister.remove(card);
            interfaceRenderer.moveCard(card, false);
            return;
        }
        drawnCards.remove(card);
        cardDeck.addToRegisterPile(card); // Cleaning up
        int index = interfaceRenderer.moveCard(card, true);
        programRegister.add(programRegister.size() - index, card);
    }

    @Override
    public List<ICard> getProgramRegister() {
        return programRegister;
    }


    @Override
    public boolean fullProgramRegister() {
        return programRegister.size() == 5;
    }

    /**
     * Requests robotRenderer to render one move
     *
     * @param from  position robot is moving from
     * @param to    position robot is moving to
     * @param debug print debug information
     */
    private void renderMove(DirVector from, DirVector to, int delay, boolean debug) {
        robotRenderer.requestRendering(from, to, robot.getDirection(), delay, playerCell, debug);
    }

    /**
     * If robot is out of bounds, moves robot to archive marker and removes one life.
     */
    private void handleRobotOutOfBounds(int delay, boolean debug) {
        DirVector position = robot.getPosition();
        if (board.outOfBounds(position) || board.isHole(position)) {
            DirVector oldPosition = robot.getPosition().copy();
            robot.moveToArchiveMarker();
            DirVector newPosition = robot.getPosition().copy();
            renderMove(oldPosition, newPosition, delay, debug);
            canMove = debug;
            removeOneLife();
            robot.removeDamageTokens(robot.getNumberOfDamageTokens() - 2);
        }
    }

    @Override
    public void handleRobotDestruction(int delay) {
        if (robot.isDestroyed()) {
            DirVector oldPosition = robot.getPosition().copy();
            robot.moveToArchiveMarker();
            DirVector newPosition = robot.getPosition().copy();
            renderMove(oldPosition, newPosition, delay, false);
            removeOneLife();
            robot.removeDamageTokens(robot.getNumberOfDamageTokens() - 2);
        }
    }
}