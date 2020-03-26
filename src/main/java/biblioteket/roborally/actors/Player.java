package biblioteket.roborally.actors;

import biblioteket.roborally.programcards.CardComparator;
import biblioteket.roborally.programcards.ICard;
import biblioteket.roborally.programcards.ICardDeck;
import biblioteket.roborally.userinterface.InterfaceRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player implements IPlayer {
    private int lives = 3;
    private int visitedFlags = 0;
    private IRobot robot;
    private TiledMapTileLayer.Cell playerCell;
    private ArrayList<ICard> programRegister;

    private InterfaceRenderer interfaceRenderer;

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
    }

    @Override
    public void drawCards(ICardDeck cardDeck) {
        int damageTokens = getRobot().getNumberOfDamageTokens();
        ArrayList<ICard> cards = cardDeck.drawCards(9 - damageTokens);
        interfaceRenderer.setCardHand(cards);
    }

    @Override
    public void addCardToProgramRegister(ICard card) {
        programRegister.add(card);
    }

    @Override
    public List<ICard> getProgramRegister() {
        ArrayList<ICard> programCards = (ArrayList<ICard>) programRegister.clone();
        programRegister.clear();
        return programCards;
    }


    @Override
    public boolean fullProgramRegister() {
        return programRegister.size() == 5;
    }


}