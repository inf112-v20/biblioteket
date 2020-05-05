package biblioteket.roborally.game;

import biblioteket.roborally.actors.IActor;
import biblioteket.roborally.actors.INonPlayer;
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
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Once every player has finished programming their robot,
 * plays a turn of RoboRally
 */
public class GameLoop {
    private final IBoard board;
    private final int amountOfFlags;
    private final List<LaserWallElement> laserWalls;
    private final List<IActor> players;
    boolean programmingPhase = true;
    private int currentPlayerPtr = 0;
    private ICardDeck cardDeck;

    public GameLoop(IBoard board, List<IActor> players) {
        this.board = board;
        this.players = players;
        amountOfFlags = board.getNumberOfFlags();
        laserWalls = board.getLaserWalls();

        try {
            cardDeck = new CardDeck();
        } catch (IOException e) {
            Gdx.app.error("GameLoop: %s", e.toString());
        }
    }

    /**
     * Sets inputprocesser, allowing players to start game events
     */
    public void startGame() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if (!programmingPhase) return false;
                return registerInput(screenX, screenY, getCurrentPlayer());
            }

            // Keyboard movement for debugging
            @Override
            public boolean keyUp(int keycode) {
                IActor currentPlayer = getCurrentPlayer();
                switch (keycode) {
                    case Input.Keys.A:
                        currentPlayer.moveRobot(Direction.WEST, 0, true, false);
                        return true;
                    case Input.Keys.D:
                        currentPlayer.moveRobot(Direction.EAST, 0, true, false);
                        return true;
                    case Input.Keys.W:
                        currentPlayer.moveRobot(Direction.NORTH, 0, true, false);
                        return true;
                    case Input.Keys.S:
                        currentPlayer.moveRobot(Direction.SOUTH, 0, true, false);
                        return true;
                    case Input.Keys.SPACE:
                    case Input.Keys.ENTER:
                        interactWithBoardElements();
                        return true;
                    case Input.Keys.P:
                        getCurrentPlayer().announcePowerDown();
                        return true;
                    case Input.Keys.UP:
                        currentPlayer.moveRobot(currentPlayer.getRobot().getDirection(), 0, false, false);
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
    private boolean registerInput(int x, int y, IActor player) {
        InterfaceRenderer interfaceRenderer = player.getInterfaceRenderer();
        ICard card = interfaceRenderer.contains(x, y, player);
        if (card != null)
            player.addCardToProgramRegister(card.copy(), cardDeck);

        if (player.fullProgramRegister())
            nextPlayer();

        return true;
    }

    /**
     *  Runs through one turn of game
     */
    public void doTurn() {
        // Dont allow players to program robots while turn is rendering
        programmingPhase = false;

        // Execute program cards in correct order
        Map<ICard, IActor> registersInPriority = new TreeMap<>(Collections.reverseOrder());
        for (int i = 4; i >= 0; i--) { // Five registers, program register is reversed.
            // Only iterate over alive players, not powered down
            for (IActor player : getLivingPlayers().stream().filter(player -> !player.isPoweredDown()).collect(Collectors.toList())) {
                ICard currentCard = player.getProgramRegister().get(i);
                registersInPriority.put(currentCard, player);
            }
            for (Entry<ICard, IActor> entry : registersInPriority.entrySet()) {
                entry.getKey().doCardAction(entry.getValue());
            }
            registersInPriority.clear();
        }

        // Robots interact with board elements*/
        interactWithBoardElements();

        // Start new turn if all players are powered down
        for (IActor player : getLivingPlayers()) {
            if (!player.isPoweredDown()) return;
        }
        newTurn();
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
        for (IActor player : players) {
            InteractingElement element = board.getInteractingElement(player.getRobot().getPosition());
            if (element != null && element.getPriority() == 2) {
                board.interact(player);
            }
        }
        // Players fire main forwarding laser
        for (IActor player : getLivingPlayers()) {
            player.fireLaser(players);
        }
        // Handle destructed robots, register flags
        for (IActor player : players) {
            board.registerFlag(player);
            player.handleRobotDestruction(500);
        }
    }

    /**
     * @return a list of all players not permanently dead
     */
    public List<IActor> getLivingPlayers() {
        return players.stream().filter(player -> !player.isPermanentDead()).collect(Collectors.toList());
    }

    /**
     * @return true if any player has registered all flags on board, or less than 2 players are alive
     */
    public boolean checkWinCondition() {
        if (getLivingPlayers().size() == 1) {
            Gdx.app.log(getLivingPlayers().get(0).getName(), " wins by being the last player alive");
            return true;
        }
        for (IActor player : players) {
            if (player.getNumberOfVisitedFlags() == amountOfFlags) {
                Gdx.app.log(player.getName(), " wins by picking up all flags");
                return true;
            }
        }
        if (getLivingPlayers().size() == 0) {
            Gdx.app.log("", "all players died");
            return true;
        }
        return false;
    }

    /**
     * All players robots interact with a certain interacting element
     *
     * @param instance robots should interact with
     */
    private void interactWithBoardElement(Class<? extends InteractingElement> instance) {
        for (IActor player : getLivingPlayers()) {
            DirVector position = player.getRobot().getPosition();
            IElement element = board.getInteractingElement(position);
            if (instance.isInstance(element)) {
                board.interact(player);
            }
        }
    }

    /**
     * Updates currentPlayerPtr to next player, skipping players not playing and handling AI players
     */
    private void nextPlayer() {
        currentPlayerPtr++;
        if (currentPlayerPtr >= players.size()) {
            currentPlayerPtr = 0;
            doTurn();
        } else if (getCurrentPlayer().isPermanentDead() || getCurrentPlayer().isPoweredDown()) {
            nextPlayer();
        } else if (getCurrentPlayer() instanceof INonPlayer) {
            ((INonPlayer) getCurrentPlayer()).chooseCards(cardDeck);
            nextPlayer();
        } else if (getCurrentPlayer().getRobot().getNumberOfDamageTokens() == 9) {
            nextPlayer();
        }
    }


    /**
     * @return current player
     */
    public IActor getCurrentPlayer() {
        return players.get(currentPlayerPtr);
    }

    /**
     * Renders interface of current player
     */
    public void renderCurrentInterface() {
        getCurrentPlayer().getInterfaceRenderer().renderInterface(players, currentPlayerPtr);
    }

    /**
     * Starts a new turn
     */
    public void newTurn() {
        if (checkWinCondition()) {
            return;
        }
        for (IActor player : getLivingPlayers()) {
            player.newTurn(cardDeck);
        }
        programmingPhase = true;
        if (getCurrentPlayer().isPoweredDown() || getCurrentPlayer().isPermanentDead()) {
            nextPlayer();
        }
    }
}
