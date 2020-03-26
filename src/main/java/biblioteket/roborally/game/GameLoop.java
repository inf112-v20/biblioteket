package biblioteket.roborally.game;

import biblioteket.roborally.actors.IPlayer;
import biblioteket.roborally.actors.IRobot;
import biblioteket.roborally.board.Board;
import biblioteket.roborally.board.DirVector;
import biblioteket.roborally.elements.IElement;
import biblioteket.roborally.elements.interactingelements.InteractingElement;
import biblioteket.roborally.elements.interactingelements.cogs.CogElement;
import biblioteket.roborally.elements.interactingelements.conveyorbelts.ConveyorBeltElement;
import biblioteket.roborally.elements.interactingelements.conveyorbelts.ExpressConveyorBeltElement;
import biblioteket.roborally.elements.walls.LaserWallElement;
import biblioteket.roborally.programcards.CardComparator;
import biblioteket.roborally.programcards.CardDeck;
import biblioteket.roborally.programcards.ICard;
import biblioteket.roborally.programcards.ICardDeck;
import biblioteket.roborally.userinterface.InterfaceRenderer;
import biblioteket.roborally.userinterface.TouchableCards;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.io.IOException;
import java.util.*;

/**
 * Executes board interactions with robot
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
        this.amountOfFlags = board.getNumFlags();
        this.laserWalls = board.getLaserWalls();

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
                int y = Gdx.graphics.getHeight() - 1 - screenY;
                return registerInput(screenX,y);
            }
        });
    }

    private boolean registerInput(int x, int y) {
        InterfaceRenderer interfaceRenderer = currentPlayer.getInterfaceRenderer();
        ICard card = interfaceRenderer.contains(x,y);
        if(card != null)
            currentPlayer.addCardToProgramRegister(card);

        if(currentPlayer.fullProgramRegister()) doTurn();

        return true;
    }

    public void doTurn(){
        Map<ICard, IPlayer> cardMapping = new TreeMap<>(new CardComparator());
        for (IPlayer player : players) {
            List<ICard> programRegister = player.getProgramRegister();
            for (ICard card : programRegister) {
                cardMapping.put(card,player);
            }
        }

        for (ICard card : cardMapping.keySet()) {
            IPlayer player = cardMapping.get(card);
            card.doCardAction(player.getRobot(),board);

        }

        interactWithEnvironment();

        for (IPlayer player : players) {
            player.drawCards(cardDeck);
            player.updateInterfaceRenderer();
        }

        if(checkWinCondition()) Gdx.app.exit();
    }

    private void interactWithEnvironment() {

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

    public boolean checkWinCondition() {
        for (IPlayer player : players) {
            if (player.getNumberOfVisitedFlags() == amountOfFlags) return true;
        }
        return false;
    }

    private void interactWithBoardElement(Class<? extends InteractingElement> instance) {
        for (IPlayer player : players) {
            DirVector position = player.getRobot().getPosition();
            IElement element = board.getInteractingElement(position);
            if (instance.isInstance(element)) {
                board.interact(player);
            }
        }
    }

    private IPlayer currentRobotIterator(){
        currentPlayerPtr = (currentPlayerPtr + 1) % players.size();
        return players.get(currentPlayerPtr);
    }

    public void render(){
        for (IPlayer player : players) {
            DirVector position = player.getRobot().getPosition();
            board.getPlayerLayer().setCell(position.getX(), position.getY(), player.getPlayerCell());
        }
    }

}
