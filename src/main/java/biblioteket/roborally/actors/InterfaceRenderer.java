package biblioteket.roborally.actors;

import biblioteket.roborally.board.IBoard;
import biblioteket.roborally.programcards.ICard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

/**
 * Handles rendering of player interface, displaying necessary information for playing the game
 * Handles user input for selecting program cards
 */
public class InterfaceRenderer {

    private final Texture background;
    private final Texture playerOverview;
    private final Texture flag;
    private final Texture hp;

    private final Texture emptyCard;
    private final Texture moveOneCard;
    private final Texture moveTwoCard;
    private final Texture moveThreeCard;
    private final Texture backUpCard;
    private final Texture rotateRightCard;
    private final Texture rotateLeftCard;
    private final Texture uTurnCard;

    private final SpriteBatch batch;
    private final BitmapFont font;
    private final ICard[] cardHand;
    private final ICard[] programRegister;
    private final TouchableCards touchableCardHand;
    private final TouchableCards touchableProgramRegister;
    private int flagsVisited;
    private int lives;
    private String name;

    public InterfaceRenderer() {
        background = new Texture("assets/background2.jpg");
        playerOverview = new Texture("assets/playerOverview.jpg");
        hp = new Texture("assets/hp.png");
        flag = new Texture("assets/flag.png");

        emptyCard = new Texture("assets/programCards/cards.png");
        moveOneCard = new Texture("assets/programCards/move1.png");
        moveTwoCard = new Texture("assets/programCards/move2.png");
        moveThreeCard = new Texture("assets/programCards/move3.png");
        backUpCard = new Texture("assets/programCards/backUp.png");
        rotateRightCard = new Texture("assets/programCards/rotateRight.png");
        rotateLeftCard = new Texture("assets/programCards/rotateLeft.png");
        uTurnCard = new Texture("assets/programCards/uTurn.png");

        batch = new SpriteBatch();
        font = new BitmapFont();

        flagsVisited = 0;
        lives = 3;
        cardHand = new ICard[9];
        programRegister = new ICard[5];

        // Card hand
        touchableCardHand = new TouchableCards(cardHand.length);
        touchableCardHand.initializeCard(0, 375, 100, 40, 90);
        touchableCardHand.initializeCard(1, 425, 100, 40, 90);
        touchableCardHand.initializeCard(2, 475, 100, 40, 90);
        touchableCardHand.initializeCard(3, 525, 100, 40, 90);
        touchableCardHand.initializeCard(4, 350, 0, 40, 90);
        touchableCardHand.initializeCard(5, 400, 0, 40, 90);
        touchableCardHand.initializeCard(6, 450, 0, 40, 90);
        touchableCardHand.initializeCard(7, 500, 0, 40, 90);
        touchableCardHand.initializeCard(8, 550, 0, 40, 90);

        // Progamregister
        touchableProgramRegister = new TouchableCards(programRegister.length);
        touchableProgramRegister.initializeCard(0, 350, 250, 40, 90);
        touchableProgramRegister.initializeCard(1, 400, 250, 40, 90);
        touchableProgramRegister.initializeCard(2, 450, 250, 40, 90);
        touchableProgramRegister.initializeCard(3, 500, 250, 40, 90);
        touchableProgramRegister.initializeCard(4, 550, 250, 40, 90);
    }

    public void renderInterface(IBoard board) {
        batch.begin();
        batch.draw(playerOverview, 0, Gdx.graphics.getHeight() - 90, Gdx.graphics.getWidth(), 90);
        batch.draw(background, board.getTileWidth(), 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(flag, 290, Gdx.graphics.getHeight() - 180, 40, 40);
        batch.draw(hp, 330, Gdx.graphics.getHeight() - 180, 40, 40);
        font.draw(batch, Integer.toString(flagsVisited), 310, Gdx.graphics.getHeight() - 165);
        font.draw(batch, Integer.toString(lives), 360, Gdx.graphics.getHeight() - 165);
        font.draw(batch, name, 410, Gdx.graphics.getHeight() - 165);


        drawCard(cardHand[0], 375, 100, 100, 90);
        drawCard(cardHand[1], 425, 100, 100, 90);
        drawCard(cardHand[2], 475, 100, 100, 90);
        drawCard(cardHand[3], 525, 100, 100, 90);
        drawCard(cardHand[4], 350, 0, 100, 90);
        drawCard(cardHand[5], 400, 0, 100, 90);
        drawCard(cardHand[6], 450, 0, 100, 90);
        drawCard(cardHand[7], 500, 0, 100, 90);
        drawCard(cardHand[8], 550, 0, 100, 90);

        drawCard(programRegister[0], 350, 250, 100, 90);
        drawCard(programRegister[1], 400, 250, 100, 90);
        drawCard(programRegister[2], 450, 250, 100, 90);
        drawCard(programRegister[3], 500, 250, 100, 90);
        drawCard(programRegister[4], 550, 250, 100, 90);

        batch.end();
    }

    /**
     * Draws a specific program card to a specific location on the screen
     *
     * @param card   to be drawn
     * @param x      coordinate
     * @param y      coordinate
     * @param width  of the texture
     * @param height of the texture
     */
    private void drawCard(ICard card, int x, int y, float width, float height) {
        Texture cardTexture = getCardTexture(card);
        batch.draw(cardTexture, x, y, width, height);
    }

    /**
     * @param card to be drawn
     * @return texture for card
     */
    private Texture getCardTexture(ICard card) {
        if (card == null) return emptyCard;
        switch (card.getType()) {
            case MOVE_1:
                return moveOneCard;
            case MOVE_2:
                return moveTwoCard;
            case MOVE_3:
                return moveThreeCard;
            case BACK_UP:
                return backUpCard;
            case ROTATE_RIGHT:
                return rotateRightCard;
            case ROTATE_LEFT:
                return rotateLeftCard;
            case U_TURN:
                return uTurnCard;
            default:
                return emptyCard;
        }
    }

    public void setFlagsVisited(int flagsVisited) {
        this.flagsVisited = flagsVisited;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void setName(String name){
        this.name = name;
    }

    /**
     * @param cardHand to be drawn
     */
    public void setCardHand(ArrayList<ICard> cardHand) {
        if (cardHand.size() > this.cardHand.length)
            throw new IndexOutOfBoundsException("Tried to deal too many cards to player");

        for (int i = 0; i < cardHand.size(); i++) {
            ICard card = cardHand.get(i);
            this.cardHand[i] = card;
            touchableCardHand.setCard(i, card);
        }

    }

    public void moveCard(ICard card, boolean toRegister){
        ICard[] cardsFrom = toRegister ? cardHand : programRegister;
        ICard[] cardsTo = toRegister ? programRegister : cardHand;
        TouchableCards touchableFrom = toRegister ? touchableCardHand : touchableProgramRegister;
        TouchableCards touchableTo = toRegister ? touchableProgramRegister : touchableCardHand;

        // Remove card where it is being moved from
        for (int i = 0; i < cardsFrom.length; i++) {
            if(card.equals(cardsFrom[i])){
                cardsFrom[i] = null;
                touchableFrom.removeCard(i);
                break;
            }
        }
        // Add card to where it is being moved to
        for (int i = 0; i < cardsTo.length; i++) {
            if(cardsTo[i] == null){
                cardsTo[i] = card;
                touchableTo.setCard(i, card);
                break;
            }
        }
    }

    /**
     * Clears program register
     */
    public void clearProgramRegister() {
        for (int i = 0; i < programRegister.length; i++) {
            programRegister[i] = null;
            touchableProgramRegister.setCard(i, null);
        }
    }

    /**
     * @param x coordinate
     * @param y coordinate
     * @return an ICard if the coordinates contain a card, or null otherwise
     */
    public ICard contains(int x, int y) {
        ICard cardHandCard = touchableCardHand.contains(x, y);
        ICard programRegisterCard = touchableProgramRegister.contains(x,y);
        return cardHandCard != null ? cardHandCard : programRegisterCard;
    }

    /**
     * Datastructure for multiple rectangles which can contain coordinates and
     * return an ICard if they are touched
     */
    private class TouchableCards {
        private final TouchableCard[] cards;

        public TouchableCards(int size) {
            cards = new TouchableCard[size];
        }

        public void initializeCard(int pos, float x, float y, float width, float height) {
            cards[pos] = new TouchableCard(x, y, width, height);
        }

        public void setCard(int pos, ICard card) {
            cards[pos].setCard(card);
        }

        /**
         * @param x coordinates
         * @param y coordinates
         * @return an ICard if any card contains the x,y coordinates, otherwise null
         */
        public ICard contains(int x, int y) {
            for (TouchableCard card : cards) {
                if (card.contains(x, y))
                    return card.getCard();

            }
            return null;
        }

        public void removeCard(int pos) {
            cards[pos].setCard(null);
        }

        /**
         * Class that extends the rectangle class, which has the contains() method for checking
         * weather any x,y input is within the bounds of the rectangle
         */
        private class TouchableCard extends Rectangle {
            private ICard card;

            TouchableCard(float x, float y, float width, float height) {
                super(x, y, width, height);
            }

            public ICard getCard() {
                return card;
            }

            public void setCard(ICard card) {
                this.card = card;
            }
        }
    }

}
