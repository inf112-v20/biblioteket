package biblioteket.roborally.game;

import biblioteket.roborally.actors.IPlayer;
import biblioteket.roborally.actors.InterfaceRenderer;
import biblioteket.roborally.board.DirVector;
import biblioteket.roborally.board.Direction;
import biblioteket.roborally.board.IBoard;
import biblioteket.roborally.elements.IElement;
import biblioteket.roborally.elements.interacting.InteractingElement;
import biblioteket.roborally.elements.interacting.cogs.CogElement;
import biblioteket.roborally.elements.interacting.conveyorbelts.ConveyorBeltElement;
import biblioteket.roborally.elements.interacting.conveyorbelts.ExpressConveyorBeltElement;
import biblioteket.roborally.elements.walls.LaserWallElement;
import biblioteket.roborally.programcards.CardDeck;
import biblioteket.roborally.programcards.ICard;
import biblioteket.roborally.programcards.ICardDeck;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

/**
 * Once every player has finished programming their robot,
 * plays a turn of RoboRally
 */
public class GameLoop {
    private final IBoard board;
    private final int amountOfFlags;
    private final List<LaserWallElement> laserWalls;
    private final List<IPlayer> players;
    private int currentPlayerPtr = 0;
    private ICardDeck cardDeck;

    public GameLoop(IBoard board, List<IPlayer> players) {
        this.board = board;
        this.players = players;
        amountOfFlags = board.getNumberOfFlags();
        laserWalls = board.getLaserWalls();

        try {
            cardDeck = new CardDeck();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void startGame() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                int y = Gdx.graphics.getHeight() - 1 - screenY; // Translate from y-down to y-up
                return registerInput(screenX, y, getCurrentPlayer());
            }

            // Keyboard movement for testing
            @Override
            public boolean keyUp(int keycode) {
                IPlayer currentPlayer = getCurrentPlayer();
                switch (keycode) {
                    case Input.Keys.A:
                        currentPlayer.moveRobot(Direction.WEST, 0);
                        return true;
                    case Input.Keys.D:
                        currentPlayer.moveRobot(Direction.EAST, 0);
                        return true;
                    case Input.Keys.W:
                        currentPlayer.moveRobot(Direction.NORTH, 0);
                        return true;
                    case Input.Keys.S:
                        currentPlayer.moveRobot(Direction.SOUTH, 0);
                        return true;
                    case Input.Keys.SPACE:
                        board.interact(currentPlayer);
                        return true;
                    case Input.Keys.P:
                        return board.registerFlag(currentPlayer);
                    case Input.Keys.ENTER:
                        interactWithBoardElements();
                        return true;
                    case Input.Keys.UP:
                        currentPlayer.moveRobot(currentPlayer.getRobot().getDirection(), 0);
                        return true;
                    case Input.Keys.LEFT:
                        currentPlayer.rotateRobot(false, 0);
                        return true;
                    case Input.Keys.RIGHT:
                        currentPlayer.rotateRobot(true, 0);
                        return true;
                    default:
                        return true;
                }
            }
        });
    }

    /**
     * Lets player choose program cards for robot, once player is done programming
     * robot, signals to game that player is ready to start turn
     *
     * @param x coordinate from user input
     * @param y coordinate from user input
     * @return true if input was handled correctly
     */
    private boolean registerInput(int x, int y, IPlayer player) {
        InterfaceRenderer interfaceRenderer = player.getInterfaceRenderer();
        ICard card = interfaceRenderer.contains(x, y);
        if (card != null)
            player.addCardToProgramRegister(card.copy(), cardDeck);

        if (player.fullProgramRegister())
            nextPlayer();

        return true;
    }

    /**
     *
     */
    public void doTurn() {
        Map<ICard, IPlayer> cardMapping = new LinkedHashMap<>();
        Map<ICard, IPlayer> priorityMap = new TreeMap<>(Collections.reverseOrder());
        for (int i = 0; i < 5; i++) {
            for (IPlayer player : players) {
                List<ICard> programRegister = player.getProgramRegister();
                priorityMap.put(programRegister.get(i), player);
            }
            for (Entry<ICard, IPlayer> entry : priorityMap.entrySet()) {
                cardMapping.put(entry.getKey(), entry.getValue());
            }
            priorityMap.clear();
        }

        // Execute program cards in order from highest to lowest priority
        for (Entry<ICard, IPlayer> entry : cardMapping.entrySet()) {
            entry.getKey().doCardAction(entry.getValue());
        }

        // Robots interact with board elements
        interactWithBoardElements();
    }

    /**
     * Robots interact with board elements
     */
    private void interactWithBoardElements() {

        // Express conveyor belts move first
        interactWithBoardElement(ExpressConveyorBeltElement.class);

        // All conveyor belts move second
        interactWithBoardElement(ConveyorBeltElement.class);


        // Gears rotate
        interactWithBoardElement(CogElement.class);

        // Lasers shoot
        for (LaserWallElement laserWall : laserWalls) {
            laserWall.interact(board, players);
        }

        // Interact with priority 2 elements
        for (IPlayer player : players) {
            InteractingElement element = board.getInteractingElement(player.getRobot().getPosition());
            if (element != null && element.getPriority() == 2) {
                board.interact(player);
            }
        }

//         Register flags
        for (IPlayer player : players) {
            board.registerFlag(player);
        }

    }

    /**
     * @return true if any player has registered all flags on board
     */
    private boolean checkWinCondition() {
        for (IPlayer player : players) {
            if (player.getNumberOfVisitedFlags() == amountOfFlags) return true;
        }
        return false;
    }

    /**
     * @return true if every player is dead
     */
    private boolean everyPlayerDead() {
        for (IPlayer player : players) {
            if (!player.isPermanentDead())
                return false;
        }
        return true;
    }

    /**
     * All players robots interact with a certain interacting element
     *
     * @param instance robots should interact with
     */
    private void interactWithBoardElement(Class<? extends InteractingElement> instance) {
        for (IPlayer player : players) {
            DirVector position = player.getRobot().getPosition();
            IElement element = board.getInteractingElement(position);
            if (instance.isInstance(element)) {
                board.interact(player);
            }
        }
    }

    private void nextPlayer(){
        currentPlayerPtr++;
        if (currentPlayerPtr == players.size()){
            currentPlayerPtr = 0;
            doTurn();
        }
    }

    public IPlayer getCurrentPlayer(){
        return players.get(currentPlayerPtr);
    }

    public void newTurn() {
        if (checkWinCondition() || everyPlayerDead())
            Gdx.app.exit();
        for (IPlayer player : players) {
            player.newTurn(cardDeck);
        }
    }

}
