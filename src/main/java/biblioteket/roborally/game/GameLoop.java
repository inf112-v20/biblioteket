package biblioteket.roborally.game;

import biblioteket.roborally.actors.IPlayer;
import biblioteket.roborally.board.Board;
import biblioteket.roborally.board.DirVector;
import biblioteket.roborally.board.Direction;
import biblioteket.roborally.elements.IElement;
import biblioteket.roborally.elements.interacting.InteractingElement;
import biblioteket.roborally.elements.interacting.cogs.CogElement;
import biblioteket.roborally.elements.interacting.conveyorbelts.ConveyorBeltElement;
import biblioteket.roborally.elements.interacting.conveyorbelts.ExpressConveyorBeltElement;
import biblioteket.roborally.elements.walls.LaserWallElement;
import biblioteket.roborally.programcards.ReverseCardComparator;
import biblioteket.roborally.programcards.CardDeck;
import biblioteket.roborally.programcards.ICard;
import biblioteket.roborally.programcards.ICardDeck;
import biblioteket.roborally.userinterface.InterfaceRenderer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Once every player has finished programming their robot,
 * plays a turn of RoboRally
 */
public class GameLoop {
    private final Board board;
    private final int amountOfFlags;
    private final List<LaserWallElement> laserWalls;
    private List<IPlayer> players;
    private int currentPlayerPtr;
    private IPlayer currentPlayer;
    private ICardDeck cardDeck;

    public GameLoop(Board board, List<IPlayer> players) {
        this.board = board;
        this.players = players;
        currentPlayerPtr = 0;
        currentPlayer = players.get(0);
        amountOfFlags = board.getNumFlags();
        laserWalls = board.getLaserWalls();

        try {
            cardDeck = new CardDeck();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (IPlayer player : players) {
            player.drawCards(cardDeck);
            player.getRobot().setPlayer(player);
        }

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button){
                int y = Gdx.graphics.getHeight() - 1 - screenY; // Translate from y-down to y-up
                return registerInput(screenX,y);
            }

            // Keyboard movement for testing
            @Override
            public boolean keyUp(int keycode) {
                switch (keycode) {
                    case Input.Keys.A:
                        return currentPlayer.getRobot().move(Direction.WEST, board);
                    case Input.Keys.D:
                        return currentPlayer.getRobot().move(Direction.EAST, board);
                    case Input.Keys.W:
                        return currentPlayer.getRobot().move(Direction.NORTH, board);
                    case Input.Keys.S:
                        return currentPlayer.getRobot().move(Direction.SOUTH, board);
                    case Input.Keys.SPACE:
                        DirVector newPosition = board.interact(currentPlayer);
                        return newPosition != null;
                    case Input.Keys.P:
                        return board.registerFlag(currentPlayer);
                    case Input.Keys.ENTER:
                        interactWithBoardElements();
                        return false;
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
    private boolean registerInput(int x, int y) {
        InterfaceRenderer interfaceRenderer = currentPlayer.getInterfaceRenderer();
        ICard card = interfaceRenderer.contains(x,y);
        if(card != null)
            currentPlayer.addCardToProgramRegister(card);

        if(currentPlayer.fullProgramRegister()) doTurn();

        return true;
    }

    /**
     *
     */
    public void doTurn(){
        // Use red-black tree to sort every programming card according to their priority,
        // mapped to correct robot
        Map<ICard, IPlayer> cardMapping = new TreeMap<>(new ReverseCardComparator());
        for (IPlayer player : players) {
            List<ICard> programRegister = player.getProgramRegister();
            for (ICard card : programRegister) {
                cardMapping.put(card,player);
            }
        }

        // Execute program cards in order from highest to lowest priority
        for (ICard card : cardMapping.keySet()) {
            IPlayer player = cardMapping.get(card);
            card.doCardAction(player.getRobot(),board);
        }

        // Robots interact with board elements
        interactWithBoardElements();

        // End turn
        if(checkWinCondition() || everyPlayerDead())
            Gdx.app.exit();
        else {
            for (IPlayer player : players) {
                player.drawCards(cardDeck);
                player.updateInterfaceRenderer();
            }
        }

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
    private boolean everyPlayerDead(){
        for (IPlayer player : players) {
            if(!player.isPermanentDead())
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

    /**
     * Updated the players position in player layer
     */
    public void renderPlayers(){
        for (IPlayer player : players) {
            DirVector position = player.getRobot().getPosition();
            board.getPlayerLayer().setCell(position.getX(), position.getY(), player.getPlayerCell());
        }
    }

}
