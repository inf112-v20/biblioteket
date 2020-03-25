package biblioteket.roborally.actors;

import biblioteket.roborally.game.InterfaceRenderer;
import biblioteket.roborally.programcards.ICard;
import biblioteket.roborally.programcards.ICardDeck;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.ArrayList;

public class Player implements IPlayer {
    private int lives = 3;
    private int visitedFlags = 0;
    private IRobot robot;
    private TiledMapTileLayer.Cell playerCell;
    private ArrayList<ICard> cards;

    public Player(TiledMapTileLayer.Cell playerCell) {
        this.playerCell = playerCell;
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
    public void drawCards(ICardDeck cardDeck) {
        int damageTokens = getRobot().getNumberOfDamageTokens();
        cards = cardDeck.drawCards(9 - damageTokens);
    }

    @Override
    public ICard getCard(int num) {
        if(cards == null) return null;
        return num < cards.size() ? cards.get(num) : null;
    }

}