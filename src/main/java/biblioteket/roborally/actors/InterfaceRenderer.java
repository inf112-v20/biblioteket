package biblioteket.roborally.actors;

import biblioteket.roborally.game.Assets;
import biblioteket.roborally.game.StandardScreen;
import biblioteket.roborally.programcards.ICard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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

    private float cardWidth;
    private float cardHeight;
    private float touchableWidth;
    private float touchableHeight;
    private float rightOfBoard;
    private float healthFlagSize;
    private float damageTokenSize;
    private float powerDownSize;
    private float powerDownX;
    private float powerDownY;

    public InterfaceRenderer() {
        Assets assets = new Assets();
        assets.load();
        assets.getManager().finishLoading();

        background = assets.getManager().get(Assets.BACKGROUND, Texture.class);
        hp = assets.getManager().get(Assets.HP, Texture.class);
        flag = assets.getManager().get(Assets.FLAG, Texture.class);

        emptyCard = assets.getManager().get(Assets.EMPTY_CARD, Texture.class);
        moveOneCard = assets.getManager().get(Assets.MOVE_ONE_CARD, Texture.class);
        moveTwoCard = assets.getManager().get(Assets.MOVE_TWO_CARD, Texture.class);
        moveThreeCard = assets.getManager().get(Assets.MOVE_THREE_CARD, Texture.class);
        backUpCard = assets.getManager().get(Assets.BACK_UP_CARD, Texture.class);
        rotateRightCard = assets.getManager().get(Assets.ROTATE_RIGHT_CARD, Texture.class);
        rotateLeftCard = assets.getManager().get(Assets.ROTATE_LEFT_CARD, Texture.class);
        uTurnCard = assets.getManager().get(Assets.U_TURN_CARD, Texture.class);
        damageToken = assets.getManager().get(Assets.DAMAGE_TOKEN, Texture.class);
        powerDownButtonPre = assets.getManager().get(Assets.POWER_DOWN_BUTTON_PRE, Texture.class);
        powerDownButtonPost = assets.getManager().get(Assets.POWER_DOWN_BUTTON_POST, Texture.class);

        batch = new SpriteBatch();
        font = new BitmapFont();
        fontBatch = new SpriteBatch();

        cardHand = new ICard[9];
        programRegister = new ICard[5];

        graphicSize();
        // Card hand
        touchableCardHand = new TouchableCards(cardHand.length);
        for (int i = 0; i < cardHand.length; i++) {
            if (i < 4) {
                touchableCardHand.initializeCard(i, rightOfBoard + (rightOfBoard / 2) - cardWidth + cardWidth / 2 * i, cardHeight, touchableWidth, touchableHeight);
            } else
                touchableCardHand.initializeCard(i, rightOfBoard + (rightOfBoard / 2) - cardWidth * 1.25f + cardWidth / 2 * (i - 4), 0, touchableWidth, touchableHeight);
        }
        // Program Register
        touchableProgramRegister = new TouchableCards(programRegister.length);
        for (int i = 0; i < 5; i++) {
            touchableProgramRegister.initializeCard(i, rightOfBoard + (rightOfBoard / 2) - cardWidth * 1.25f + cardWidth / 2 * i, Gdx.graphics.getHeight() / (640f / 250f), touchableWidth, touchableHeight);
        }


    }

    /**
     * Updates the graphic size based on current screen proportions
     */
    private void graphicSize() {
        cardWidth = (Gdx.graphics.getHeight() / (640f / 130f));
        cardHeight = (Gdx.graphics.getHeight() / (640f / 90f));
        touchableWidth = cardWidth * 0.4f;
        touchableHeight = (Gdx.graphics.getWidth() / (640f / 90f));
        rightOfBoard = Gdx.graphics.getWidth() / 2f;
        healthFlagSize = Gdx.graphics.getHeight() / (640f / 40f);
        damageTokenSize = Gdx.graphics.getHeight() / (640f / 35f);
        powerDownSize = Gdx.graphics.getHeight() / 10f;
        powerDownX = StandardScreen.getCamera().viewportWidth / 2f + StandardScreen.getCamera().viewportWidth / 4f - powerDownSize / 2f;
        powerDownY = StandardScreen.getCamera().viewportHeight / 1.6f;

    }

    /**
     * @param players          list of all players
     * @param currentPlayerPtr pointer for the current player who's interface is displayed in player list
     */
    public void renderInterface(List<IActor> players, int currentPlayerPtr) {
        graphicSize();
        StandardScreen.getCamera().update();
        batch.setProjectionMatrix(StandardScreen.getCamera().combined);
        fontBatch.setProjectionMatrix(StandardScreen.getCamera().combined);

        batch.begin();
        batch.draw(background, 0, 0, StandardScreen.getCamera().viewportWidth, StandardScreen.getCamera().viewportHeight);

        drawDamageTokens(players.get(currentPlayerPtr).getRobot().getNumberOfDamageTokens());
        drawPlayerInformation(players, currentPlayerPtr);

        //Row with four cards
        for (int i = 0; i < 4; i++) {
            drawCard(cardHand[i], rightOfBoard + rightOfBoard / 2 - cardWidth + cardWidth / 2 * i, cardHeight, cardWidth, cardHeight);
        }
        // row with five cards.
        for (int i = 0; i < 5; i++) {
            drawCard(cardHand[4 + i], rightOfBoard + rightOfBoard / 2 - cardWidth * 1.25f + cardWidth / 2 * i, 0, cardWidth, cardHeight);
            drawCard(programRegister[i], rightOfBoard + rightOfBoard / 2 - cardWidth * 1.25f + cardWidth / 2 * i, StandardScreen.getCamera().viewportHeight / (640f / 250f), cardWidth, cardHeight);
        }

        //powerDown button
        batch.draw(powerDownButtonPre, powerDownX, powerDownY, powerDownSize, powerDownSize);
        if (Gdx.input.getX() < powerDownX + powerDownSize && Gdx.input.getX() > powerDownX && StandardScreen.getCamera().viewportHeight - Gdx.input.getY() < powerDownY + powerDownSize / 1.1f && StandardScreen.getCamera().viewportHeight - Gdx.input.getY() > powerDownY + powerDownSize / (6f)) {
            batch.draw(powerDownButtonPost, powerDownX, powerDownY, powerDownSize, powerDownSize);
        }
        batch.end();

        //card priority
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
        //card priority program register
        for (int i = 0; i < 5; i++) {
            if (programRegister[i] != null) {
                font.draw(fontBatch, Integer.toString(programRegister[i].getPriorityNumber()), rightOfBoard + rightOfBoard / 2 - cardWidth * 1.06f + cardWidth / 2 * i, cardHeight * (3.63f));
            }
        }
        fontBatch.end();
    }

    /**
     * Draws the amount of damage tokens current player's robot has taken to interface
     *
     * @param damageTokens number of damage tokens for current player
     */
    private void drawDamageTokens(int damageTokens) {
        for (int i = 0; i < damageTokens; i++) {
            batch.draw(damageToken, rightOfBoard + rightOfBoard / 2 - damageTokenSize * 1.06f + damageTokenSize / 1.15f * (i - 4), StandardScreen.getCamera().viewportHeight / 1.8f, damageTokenSize, damageTokenSize);
        }
    }

    /**
     * Draws information of all living players to interface
     * Current player's information displayed in green, powered down players information displayed in red
     *
     * @param players          List of players
     * @param currentPlayerPtr Pointer for the current player who's interface is displayed in player list
     */
    private void drawPlayerInformation(List<IActor> players, int currentPlayerPtr) {
        for (int i = 0; i < players.size(); i++) {
            IActor player = players.get(i);
            int lives = player.getLives();
            int flagsVisited = player.getNumberOfVisitedFlags();

            if (player.isPermanentDead())  // Dead players red
                continue;
            else if (i == currentPlayerPtr)  // Current player green
                font.setColor(Color.GREEN);
            else if (player.isPoweredDown())
                font.setColor(Color.RED);

            if (i < 4) {   // Players 1-4
                batch.draw(flag, rightOfBoard / 1.015f + rightOfBoard / 4 * i, StandardScreen.getCamera().viewportHeight - StandardScreen.getCamera().viewportHeight / 16 * 140 / 100, healthFlagSize, healthFlagSize);
                batch.draw(hp, (rightOfBoard + healthFlagSize / 4 * 2) + rightOfBoard / 4 * i, StandardScreen.getCamera().viewportHeight - StandardScreen.getCamera().viewportHeight / 16 * 140 / 100, healthFlagSize, healthFlagSize);
                font.draw(batch, players.get(i).getName(), rightOfBoard + rightOfBoard / 4 * i, StandardScreen.getCamera().viewportHeight - StandardScreen.getCamera().viewportHeight / 16f / 10f);
                font.draw(batch, Integer.toString(lives), rightOfBoard + healthFlagSize * 1.2f + rightOfBoard / 4 * i, StandardScreen.getCamera().viewportHeight - StandardScreen.getCamera().viewportHeight / (640f / 40f));
                font.draw(batch, Integer.toString(flagsVisited), rightOfBoard + healthFlagSize * 0.2f + rightOfBoard / 4 * i, StandardScreen.getCamera().viewportHeight - StandardScreen.getCamera().viewportHeight / (640f / 40f));
            } else { // Players 5-8
                int j = i - 4;
                batch.draw(flag, rightOfBoard / 1.015f + rightOfBoard / 4 * j, StandardScreen.getCamera().viewportHeight - StandardScreen.getCamera().viewportHeight / 5f, healthFlagSize, healthFlagSize);
                batch.draw(hp, rightOfBoard + healthFlagSize / 4 * 2 + rightOfBoard / 4 * j, StandardScreen.getCamera().viewportHeight - StandardScreen.getCamera().viewportHeight / 5f, healthFlagSize, healthFlagSize);
                font.draw(batch, players.get(i).getName(), rightOfBoard + rightOfBoard / 4 * j, StandardScreen.getCamera().viewportHeight - StandardScreen.getCamera().viewportHeight / 8f);
                font.draw(batch, Integer.toString(lives), rightOfBoard + healthFlagSize * 1.2f + rightOfBoard / 4 * j, StandardScreen.getCamera().viewportHeight - StandardScreen.getCamera().viewportHeight / (640f / 110f));
                font.draw(batch, Integer.toString(flagsVisited), rightOfBoard + healthFlagSize * 0.2f + rightOfBoard / 4 * j, StandardScreen.getCamera().viewportHeight - StandardScreen.getCamera().viewportHeight / (640f / 110f));
            }
            font.setColor(Color.WHITE);
        }
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
     * Returns correct card texture for an ICard
     *
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

    /**
     * Updates the cardhand of current player
     *
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

    /**
     * Moves a card between cardhand and program register
     *
     * @param card       to be moved
     * @param toRegister true if card is being moved to register, false if card is being returned from register to cardhand
     * @return index where card is moved to, or -1 if no card was moved
     */
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
     * Places a card to register
     *
     * @param card  Card to place
     * @param index Index to place card in
     */
    public void addCardToLockedRegister(ICard card, int index) {
        programRegister[index] = card;
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
     * @param x      coordinate
     * @param y      coordinate
     * @param player the player who is interacting
     * @return an ICard if the coordinates contain a card, or null otherwise
     */
    public ICard contains(int x, int y, IActor player) {
        if (powerDownButtonTouched(x, y)) {
            player.announcePowerDown();
            return null;
        }
        ICard cardHandCard = touchableCardHand.contains(x, y);
        ICard programRegisterCard = touchableProgramRegister.contains(x, y);
        return cardHandCard != null ? cardHandCard : programRegisterCard;
    }

    /**
     * @param x coordinate
     * @param y coordinate
     * @return true if power down button was touched
     */
    private boolean powerDownButtonTouched(int x, int y) {
        return x < powerDownX + powerDownSize && x > powerDownX
                && StandardScreen.getCamera().viewportHeight - y < powerDownY + powerDownSize / 1.1f
                && StandardScreen.getCamera().viewportHeight - y > powerDownY + powerDownSize / (6f);
    }


    /**
     * Data structure for multiple rectangles which can contain coordinates and cards, and
     * return an ICard if they are touched
     */
    private static class TouchableCards {
        private final TouchableCard[] cards;

        public TouchableCards(int size) {
            cards = new TouchableCard[size];
        }

        /**
         * Initializes a TouchableCard with coordinates and index
         *
         * @param i      index
         * @param x      coordinate
         * @param y      coordinate
         * @param width  of rectangle
         * @param height of rectangle
         */
        public void initializeCard(int i, float x, float y, float width, float height) {
            cards[i] = new TouchableCard(x, y, width, height);
        }

        /**
         * Sets an ICard to a TouchableCard
         *
         * @param i    index
         * @param card card to be set
         */
        public void setCard(int i, ICard card) {
            cards[i].setCard(card);
        }

        /**
         * @param x coordinates
         * @param y coordinates
         * @return an ICard if any card contains the x,y coordinates, null otherwise
         */
        public ICard contains(int x, int y) {
            // Translate from y-down to y-up
            y = Gdx.graphics.getHeight() - 1 - y;
            // Translate coordinates according to current screen size
            x = (int) ((float) (1280 * x) / (float) Gdx.graphics.getWidth());
            y = (int) ((float) (720 * y) / (float) Gdx.graphics.getHeight());

            for (TouchableCard card : cards) {
                if (card.contains(x, y))
                    return card.getCard();
            }
            return null;
        }

        /**
         * Removes an ICard from a TouchableCard in index i
         *
         * @param i index
         */
        public void removeCard(int i) {
            cards[i].setCard(null);
        }

        /**
         * Class that extends the rectangle class, which has the contains() method for checking
         * weather any x,y input is within the bounds of the rectangle
         * <p>
         * Contains an ICard, and coordinates where the ICard is located on interface
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








