package biblioteket.roborally.actors;

import biblioteket.roborally.programcards.ICard;
import biblioteket.roborally.programcards.ICardDeck;
import biblioteket.roborally.userinterface.InterfaceRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.ArrayList;
import java.util.List;

public class Player implements IPlayer {
    private final TiledMapTileLayer.Cell playerCell;
    private final ArrayList<ICard> programRegister;
    private final InterfaceRenderer interfaceRenderer;
    private int lives = 3;
    private int visitedFlags = 0;
    private IRobot robot;
    private ArrayList<ICard> drawnCards;

    public Player(TiledMapTileLayer.Cell playerCell, InterfaceRenderer interfaceRenderer) {
        this.playerCell = playerCell;
        this.interfaceRenderer = interfaceRenderer;
        programRegister = new ArrayList<>();
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
    public void updateInterfaceRenderer() {
        interfaceRenderer.setFlagsVisited(getNumberOfVisitedFlags());
        interfaceRenderer.setLives(getLives());
        interfaceRenderer.clearProgramRegister();
    }

    //TODO: Quite verbose
    @Override
    public void drawCards(ICardDeck cardDeck) {
        int defaultNumber = 9; //default number of cards to draw
        int damageTokens = robot.getNumberOfDamageTokens();
        int cardsToDraw = defaultNumber - damageTokens;
        System.out.println("Damage: " + damageTokens);
        System.out.println("Lives: " + lives);
        ArrayList<ICard> cards = cardDeck.drawCards(cardsToDraw);
        drawnCards = cards;
        interfaceRenderer.setCardHand(cards);
    }

    @Override
    public void addCardToProgramRegister(ICard card) {
        programRegister.add(card);
        interfaceRenderer.addCardToProgramRegister(card);
    }

    @Override
    public List<ICard> getProgramRegister() { //Used in game loop to execute moves.
        List<ICard> programCards = new ArrayList<>(programRegister);
        programRegister.clear();
        return programCards;
    }


    @Override
    public boolean fullProgramRegister() {
        return programRegister.size() == 5;
    }


}