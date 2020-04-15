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
    private int lockedRegisters = 0;

    public Player(TiledMapTileLayer.Cell playerCell, InterfaceRenderer interfaceRenderer) {
        this.playerCell = playerCell;
        this.interfaceRenderer = interfaceRenderer;
        programRegister = new ArrayList<>();
        drawnCards = new ArrayList<>();
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


}