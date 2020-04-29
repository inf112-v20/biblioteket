package biblioteket.roborally.actors;

import biblioteket.roborally.board.IBoard;
import biblioteket.roborally.game.GameScreen;
import biblioteket.roborally.programcards.ICard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.List;
import java.util.Objects;

/**
 * Handles rendering of player interface, displaying necessary information for playing the game
 * Handles user input for selecting program cards
 */
public class InterfaceRenderer {

    private final Texture background;
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
    private final Texture damageToken;
    private final Texture powerDownButtonPre;
    private final Texture powerDownButtonPost;

    private final SpriteBatch batch;
    private final BitmapFont font;
    private final SpriteBatch fontBatch;
    private final ICard[] cardHand;
    private final ICard[] programRegister;
    private final TouchableCards touchableCardHand;
    private final TouchableCards touchableProgramRegister;
    private final OrthographicCamera camera;
    private int flagsVisited;
    private int lives;
    private String name;

    private float cardWidth;
    private float cardHeight;
    private float touchableWidth;
    private float touchableHeight;
    private float rightOfBoard;
    private float healthFlagSize;
    private float damageTokenSize;
    private float powerdownSize;
    private float powerDownX;
    private float powerDownY;


    public InterfaceRenderer() {

        camera = GameScreen.getCamera();

        background = new Texture("assets/background2.jpg");
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
        damageToken = new Texture("assets/damageToken.png");
        powerDownButtonPre = new Texture("assets/buttons/powerdownPre.png");
        powerDownButtonPost = new Texture("assets/buttons/powerdownPost.png");


        batch = new SpriteBatch();
        font = new BitmapFont();
        fontBatch = new SpriteBatch();


        flagsVisited = 0;
        lives = 3;
        cardHand = new ICard[9];
        programRegister = new ICard[5];


        graphicSize();
        // Card hand
        touchableCardHand = new TouchableCards(cardHand.length);
        for (int i = 0; i < cardHand.length; i++) {
            if (i < 4) {
                touchableCardHand.initializeCard(i, rightOfBoard + rightOfBoard / 2 - cardWidth + cardWidth / 2 * i, cardHeight, touchableWidth, touchableHeight);
            } else
                touchableCardHand.initializeCard(i, rightOfBoard + rightOfBoard / 2 - cardWidth * 1.25f + cardWidth / 2 * (i - 4), 0, touchableWidth, touchableHeight);
        }

        // Progamregister
        touchableProgramRegister = new TouchableCards(programRegister.length);
        for (int i = 0; i < 5; i++) {
            touchableProgramRegister.initializeCard(i, rightOfBoard + rightOfBoard / 2 - cardWidth * 1.25f + cardWidth / 2 * i, Gdx.graphics.getHeight() / (640f / 250f), touchableWidth, touchableHeight);

        }


    }

    public void graphicSize() {
        cardWidth = (Gdx.graphics.getHeight() / (640f / 130f));
        cardHeight = (Gdx.graphics.getHeight() / (640f / 90f));
        touchableWidth = (Gdx.graphics.getWidth() / (640f / 40f));
        touchableHeight = (Gdx.graphics.getWidth() / (640f / 90f));
        rightOfBoard = Gdx.graphics.getWidth() / 2f;
        healthFlagSize = Gdx.graphics.getHeight() / (640f / 40f);
        damageTokenSize = Gdx.graphics.getHeight() / (640f / 35f);
        powerdownSize = Gdx.graphics.getHeight() / 10f;
        powerDownX = camera.viewportWidth / 2f + camera.viewportWidth / 4f - powerdownSize / 2f;
        powerDownY = camera.viewportHeight / 1.6f;

    }

    public void renderInterface(IBoard board) {
        graphicSize();

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        fontBatch.setProjectionMatrix(camera.combined);


        batch.begin();
        batch.draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);

        for (int i = 0; i < 4; i++) {
            drawCard(cardHand[i], rightOfBoard + rightOfBoard / 2 - cardWidth + cardWidth / 2 * i, cardHeight, cardWidth, cardHeight);

        }
        for (int i = 0; i < 5; i++) {
            drawCard(cardHand[4 + i], rightOfBoard + rightOfBoard / 2 - cardWidth * 1.25f + cardWidth / 2 * i, 0, cardWidth, cardHeight);

        }

        for (int i = 0; i < 5; i++) {
            drawCard(programRegister[0 + i], rightOfBoard + rightOfBoard / 2 - cardWidth * 1.25f + cardWidth / 2 * i, camera.viewportHeight / (640f / 250f), cardWidth, cardHeight);

        }

        for (int i = 0; i < 10; i++) {
            batch.draw(damageToken, rightOfBoard + rightOfBoard / 2 - damageTokenSize * 1.06f + damageTokenSize / 1.15f * (i - 4), camera.viewportHeight / 1.8f, damageTokenSize, damageTokenSize);
        }

        for (int i = 0; i < 4; i++) {
            // Player 1-4
            batch.draw(flag, (rightOfBoard / ((320f / 315f))) + rightOfBoard / 4 * i, camera.viewportHeight - (camera.viewportHeight / 16) * 140 / 100, healthFlagSize, healthFlagSize);
            batch.draw(hp, (rightOfBoard + healthFlagSize / 4 * 2) + rightOfBoard / 4 * i, camera.viewportHeight - (camera.viewportHeight / 16) * 140 / 100, healthFlagSize, healthFlagSize);
            font.draw(batch, "Player " + (i + 1), rightOfBoard + rightOfBoard / 4 * i, camera.viewportHeight - (camera.viewportHeight / 16f) / 10f);
            font.draw(batch, Integer.toString(lives), rightOfBoard + healthFlagSize * 1.2f + rightOfBoard / 4 * i, camera.viewportHeight - camera.viewportHeight / (640f / 40f));
            font.draw(batch, Integer.toString(flagsVisited), rightOfBoard + healthFlagSize * 0.2f + rightOfBoard / 4 * i, camera.viewportHeight - camera.viewportHeight / (640f / 40f));

            // Player 5-8
            batch.draw(flag, (rightOfBoard / (320f / 315f)) + rightOfBoard / 4 * i, camera.viewportHeight - ((camera.viewportHeight / 5f)), healthFlagSize, healthFlagSize);
            batch.draw(hp, (rightOfBoard + healthFlagSize / 4 * 2) + rightOfBoard / 4 * i, camera.viewportHeight - ((camera.viewportHeight / 5f)), healthFlagSize, healthFlagSize);
            font.draw(batch, "Player " + (5 + i), rightOfBoard + rightOfBoard / 4 * i, camera.viewportHeight - ((camera.viewportHeight / 8f)));
            font.draw(batch, Integer.toString(lives), rightOfBoard + healthFlagSize * 1.2f + rightOfBoard / 4 * i, camera.viewportHeight - camera.viewportHeight / (640f / 110f));
            font.draw(batch, Integer.toString(flagsVisited), rightOfBoard + healthFlagSize * 0.2f + rightOfBoard / 4 * i, camera.viewportHeight - camera.viewportHeight / (640f / 110f));
        }
        batch.draw(powerDownButtonPre, powerDownX, powerDownY, powerdownSize, powerdownSize);

        if (Gdx.input.getX() < powerDownX + powerdownSize && Gdx.input.getX() > powerDownX && camera.viewportHeight - Gdx.input.getY() < powerDownY + powerdownSize / 1.1f && camera.viewportHeight - Gdx.input.getY() > powerDownY + powerdownSize / (6f)) {
            batch.draw(powerDownButtonPost, powerDownX, powerDownY, powerdownSize, powerdownSize);
            if (Gdx.input.isTouched()) {
                System.out.println("powerdown metode her");
            }
        }

        batch.end();

        fontBatch.begin();
        for (int i = 0; i < 4; i++) {
            if (cardHand[i] != null) {
                font.draw(fontBatch, Integer.toString(cardHand[i].getPriorityNumber()), rightOfBoard + rightOfBoard / 2 - cardWidth * 0.80f + cardWidth / 2 * i, cardHeight / (54f / 100f));
            }
        }
        for (int i = 4; i < 9; i++) {
            if (cardHand[i] != null) {
                font.draw(fontBatch, Integer.toString(cardHand[i].getPriorityNumber()), rightOfBoard + rightOfBoard / 2 - cardWidth * 1.06f + cardWidth / 2 * (i - 4), cardHeight / (117f / 100f));
            }
        }
        for (int i = 0; i < 5; i++) {
            if (programRegister[i] != null) {
                font.draw(fontBatch, Integer.toString(programRegister[i].getPriorityNumber()), rightOfBoard + rightOfBoard / 2 - cardWidth * 1.06f + cardWidth / 2 * i, cardHeight * (3.63f));
            }
        }
        fontBatch.end();
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
    private void drawCard(ICard card, float x, float y, float width, float height) {
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

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param cardHand to be drawn
     */
    public void setCardHand(List<ICard> cardHand) {
        if (cardHand.size() > this.cardHand.length) {
            Gdx.app.error("InterfaceRenderer: %s", cardHand.toString());
            Gdx.app.error("InterfaceRenderer: %d", String.valueOf(this.cardHand.length));
            throw new IndexOutOfBoundsException("Tried to deal too many cards to player");
        }

        for (int i = 0; i < this.cardHand.length; i++) {
            this.cardHand[i] = null;
            touchableCardHand.setCard(i, null);
        }

        for (int i = 0; i < cardHand.size(); i++) {
            ICard card = cardHand.get(i);
            this.cardHand[i] = card;
            touchableCardHand.setCard(i, card);
        }

    }

    public int moveCard(ICard card, boolean toRegister) {
        ICard[] cardsFrom = toRegister ? cardHand : programRegister;
        ICard[] cardsTo = toRegister ? programRegister : cardHand;
        TouchableCards touchableFrom = toRegister ? touchableCardHand : touchableProgramRegister;
        TouchableCards touchableTo = toRegister ? touchableProgramRegister : touchableCardHand;

        // Remove card where it is being moved from
        for (int i = 0; i < cardsFrom.length; i++) {
            if (card.equals(cardsFrom[i])) {
                cardsFrom[i] = null;
                touchableFrom.removeCard(i);
                break;
            }
        }
        // Add card to where it is being moved to
        for (int i = 0; i < cardsTo.length; i++) {
            if (cardsTo[i] == null) {
                cardsTo[i] = card;
                touchableTo.setCard(i, card);
                return i;
            }
        }
        return -1;
    }

    /**
     * @param card  Card to place
     * @param index Index to place card in
     */
    public void addCardToLockedRegister(ICard card, int index) {
        programRegister[index] = card;
    }

    /**
     * @param card  Card to place
     * @param index Index to place card in
     */
    public void addCardToProgramRegisterIndex(ICard card, int index) {
        for (int i = 0; i < cardHand.length; i++) {
            if (card.equals(cardHand[i])) {
                cardHand[i] = null;
                touchableCardHand.removeCard(i);
                break;
            }
        }

        programRegister[index] = card;
        touchableProgramRegister.setCard(index, card);
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
        ICard programRegisterCard = touchableProgramRegister.contains(x, y);
        return cardHandCard != null ? cardHandCard : programRegisterCard;
    }

    /**
     * Datastructure for multiple rectangles which can contain coordinates and
     * return an ICard if they are touched
     */
    private static class TouchableCards {
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
        private static class TouchableCard extends Rectangle {
            private transient ICard card;

            TouchableCard(float x, float y, float width, float height) {
                super(x, y, width, height);
            }

            public ICard getCard() {
                return card;
            }

            public void setCard(ICard card) {
                this.card = card;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                if (!super.equals(o)) return false;
                TouchableCard that = (TouchableCard) o;
                return Objects.equals(card, that.card);
            }

            @Override
            public int hashCode() {
                return Objects.hash(super.hashCode(), card);
            }
        }
    }


}



