package biblioteket.roborally.actors;

import biblioteket.roborally.board.DirVector;
import biblioteket.roborally.board.Direction;
import biblioteket.roborally.board.IBoard;
import biblioteket.roborally.elements.walls.Laser;
import biblioteket.roborally.programcards.ICard;
import biblioteket.roborally.programcards.ICardDeck;
import com.badlogic.gdx.Gdx;
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

    private PlayerState state = PlayerState.PLAYING;

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
            moveRobot(robot.getDirection(), delay, false, false);
        }
    }

    @Override
    public boolean moveRobot(Direction direction, int delay, boolean debug, boolean pushed) {
        if (!state.canMove() && !pushed) return false;
        if (board.canMove(robot.getPosition(), direction) && board.pushRobot(robot.getPosition(), direction)) {
            DirVector oldPosition = robot.getPosition();
            robot.pushRobotInDirection(direction);
            DirVector newPosition = robot.getPosition();
            renderMove(oldPosition, newPosition, delay, debug);
            // Check if robot moved in hole or out of bounds
            handleRobotOutOfBounds(delay, debug);
            return true;
        }
        return false;
    }

    @Override
    public void backUpRobot(int delay) {
        moveRobot(robot.getDirection().opposite(), delay, false, false);
    }

    @Override
    public void rotateRobot(boolean right, int delay) {
        if (!state.canMove()) return;
        if (right) {
            robot.turnRight();
        } else {
            robot.turnLeft();
        }
        renderMove(robot.getPosition(), robot.getPosition(), delay, false);
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
        return state.isDestroyed();
    }

    @Override
    public boolean hasLivesLeft() {
        return lives > 0;
    }

    @Override
    public void removeOneLife() {
        if (--lives <= 0) {
            state = PlayerState.DESTROYED;
            Gdx.app.log(getName(), "permanently destroyed");
            robotRenderer.removePlayer(getRobot().getPosition(), playerCell);
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

    /**
     * Cleans the register based on damage tokens.
     *
     * @param damageTokens how many damage tokens the players robot has accumulated.
     * @param cardDeck     The cardDeck used in the game.
     */
    private void cleanRegister(int damageTokens, ICardDeck cardDeck) {
        switch (damageTokens) {
            case 9:
                break;
            case 8:
                cardDeck.removeFromRegisterPile(programRegister.remove(programRegister.size() - 1));
                break;
            case 7:
                cardDeck.removeFromRegisterPile(programRegister.remove(programRegister.size() - 1));
                cardDeck.removeFromRegisterPile(programRegister.remove(programRegister.size() - 1));
                break;
            case 6:
                cardDeck.removeFromRegisterPile(programRegister.remove(programRegister.size() - 1));
                cardDeck.removeFromRegisterPile(programRegister.remove(programRegister.size() - 1));
                cardDeck.removeFromRegisterPile(programRegister.remove(programRegister.size() - 1));
                break;
            case 5:
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
        state = state.nextTurn();
        if (isPoweredDown()) {
            int damageTokens = getRobot().getNumberOfDamageTokens();
            getRobot().removeDamageTokens(damageTokens);
            Gdx.app.log(getName(), "discards " + damageTokens + " damage tokens");
        } else {
            int damageTokens = robot.getNumberOfDamageTokens();  // Check damage and clean register.
            if (!drawnCards.isEmpty()) // Should stop it from breaking first round
                for (ICard card : drawnCards) // adds the cards not uses last round to discard pile
                    cardDeck.addToDiscardPile(card);
            if (!programRegister.isEmpty()) { // Should stop it from breaking first round
                cleanRegister(damageTokens, cardDeck); // Corrects the register
                updateRegisterRender(); // Render cards if they are locked in register
            }
            drawCards(cardDeck);
        }
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

    @Override
    public void fireLaser(List<IActor> players) {
        DirVector vector = getRobot().getPosition();
        if (board.canMove(vector, vector.getDirection())) {
            Laser laser = new Laser();
            vector.forward(vector.getDirection());
            laser.fireLaser(board, players, vector);
        }
    }

    /**
     * Draw a new deck of cards from a random selection of all possible
     * cards. Chooses a valid amount of cards to draw.
     *
     * @param cardDeck a deck of cards
     */
    private void drawCards(ICardDeck cardDeck) {
        int defaultNumber = 9; //default number of cards to draw
        int damageTokens = robot.getNumberOfDamageTokens();
        int cardsToDraw = defaultNumber - damageTokens;

        drawnCards = cardDeck.drawCards(cardsToDraw);
        interfaceRenderer.setCardHand(drawnCards);
    }

    /**
     * Requests robotRenderer to render one move
     *
     * @param from  position robot is moving from
     * @param to    position robot is moving to
     * @param delay rendering delay in milliseconds
     * @param debug true if debugging, will prevent renderer from starting new turn
     */
    private void renderMove(DirVector from, DirVector to, int delay, boolean debug) {
        robotRenderer.requestRendering(from, to, robot.getDirection(), delay, playerCell, debug);
    }

    /**
     * If robot is out of bounds, moves robot to archive marker and removes one life.
     *
     * @param delay rendering delay in milliseconds
     * @param debug true if debugging, will prevent renderer from starting new turn
     */
    private void handleRobotOutOfBounds(int delay, boolean debug) {
        DirVector position = robot.getPosition();
        if (board.outOfBounds(position) || board.isHole(position)) {
            DirVector oldPosition = robot.getPosition();
            robot.moveToArchiveMarker();
            DirVector newPosition = robot.getPosition();
            renderMove(oldPosition, newPosition, delay, debug);
            state = debug ? state : PlayerState.IMMOBILE;
            removeOneLife();
            robot.removeDamageTokens(robot.getNumberOfDamageTokens() - 2);
        }
    }

    @Override
    public void handleRobotDestruction(int delay) {
        if (robot.isDestroyed()) {
            DirVector oldPosition = robot.getPosition();
            robot.moveToArchiveMarker();
            DirVector newPosition = robot.getPosition();
            renderMove(oldPosition, newPosition, delay, false);
            removeOneLife();
            robot.removeDamageTokens(robot.getNumberOfDamageTokens() - 2);
        }
    }

    @Override
    public void announcePowerDown() {
        Gdx.app.log(getName(), "announces power down");
        state = state.announcePowerDown();
    }

    @Override
    public boolean isPoweredDown() {
        return state.isPoweredDown();
    }


}