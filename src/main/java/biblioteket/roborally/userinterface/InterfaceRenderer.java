package biblioteket.roborally.userinterface;

import biblioteket.roborally.board.IBoard;
import biblioteket.roborally.game.GameScreen;
import biblioteket.roborally.programcards.ICard;
import biblioteket.roborally.programcards.ReverseCardComparator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Arrays;

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
    private OrthographicCamera camera;

    float cardWidth;
    float cardHeight;
    float touchableWidth;
    float touchableHeight;
    float leftOfBoard;

    public InterfaceRenderer() {
        camera = GameScreen.getCamera();
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
        for(int i = 0; i < 4; i++) {
            touchableCardHand.initializeCard(0+i, leftOfBoard + leftOfBoard / 2 - cardWidth + cardWidth / 2 * i, cardHeight, 40, 90);
            //touchableCardHand.initializeCard(1, 425, 100, 40, 90);
            //touchableCardHand.initializeCard(2, 475, 100, 40, 90);
            //touchableCardHand.initializeCard(3, 525, 100, 40, 90);
        }
        for(int i = 0; i < 5; i++) {
            touchableCardHand.initializeCard(4+i, leftOfBoard + leftOfBoard / 2 - cardWidth*1.25f + cardWidth / 2 * i, 0, 40, 90);
            //touchableCardHand.initializeCard(5, 400, 0, 40, 90);
            //touchableCardHand.initializeCard(6, 450, 0, 40, 90);
            //touchableCardHand.initializeCard(7, 500, 0, 40, 90);
            //touchableCardHand.initializeCard(8, 550, 0, 40, 90);
        }

        // Progamregister
        touchableProgramRegister = new TouchableCards(programRegister.length);
        for(int i = 0; i < 5; i++) {
            touchableProgramRegister.initializeCard(0+i, leftOfBoard + leftOfBoard / 2 - cardWidth*1.25f + cardWidth / 2 * i, Gdx.graphics.getHeight()/(640f/250f), 40, 90);
            //touchableProgramRegister.initializeCard(1, 400, 250, 40, 90);
            //touchableProgramRegister.initializeCard(2, 450, 250, 40, 90);
            //touchableProgramRegister.initializeCard(3, 500, 250, 40, 90);
            //touchableProgramRegister.initializeCard(4, 550, 250, 40, 90);
        }
    }

    public void cardSize() {
        cardWidth = (Gdx.graphics.getHeight()/(640f/130f));
        cardHeight = (Gdx.graphics.getHeight()/(640f/90f));
        touchableWidth = (Gdx.graphics.getWidth()/(640f/40f));
        touchableHeight = (Gdx.graphics.getWidth()/(640f/90f));
        leftOfBoard = Gdx.graphics.getWidth()/2;

    }


    public void renderInterface(IBoard board) {
        cardSize();
        batch.setProjectionMatrix(camera.combined);
        camera.update();

        batch.begin();
        batch.draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);
        batch.draw(flag, 290, Gdx.graphics.getHeight() - 180, 40, 40);
        batch.draw(hp, 330, Gdx.graphics.getHeight() - 180, 40, 40);
        font.draw(batch, Integer.toString(flagsVisited), 310, Gdx.graphics.getHeight() - 165);
        font.draw(batch, Integer.toString(lives), 360, Gdx.graphics.getHeight() - 165);

        for(int i = 0; i < 4; i++) {
            drawCard(cardHand[0 + i], leftOfBoard + leftOfBoard / 2 - cardWidth + cardWidth / 2 * i, cardHeight, cardWidth, cardHeight);
            //drawCard(cardHand[1], (float) camera.viewportWidth / (640f / 425f), cardHeight, cardWidth, cardHeight);
            //drawCard(cardHand[2], (float) camera.viewportWidth / (640f / 475f), cardHeight, cardWidth, cardHeight);
            //drawCard(cardHand[3], (float) camera.viewportWidth / (640f / 525f), cardHeight, cardWidth, cardHeight);
            //System.out.println(leftOfBoard + leftOfBoard / 2 - cardWidth + cardWidth / 2 * i);
        }
        for(int i = 0; i < 5; i++) {
            drawCard(cardHand[4+i], leftOfBoard + leftOfBoard / 2 - cardWidth*1.25f + cardWidth / 2 * i, 0, cardWidth, cardHeight);
            //drawCard(cardHand[5], (float) camera.viewportWidth / (640f / 400f), 0, cardWidth, cardHeight);
            //drawCard(cardHand[6], (float) camera.viewportWidth / (640f / 450f), 0, cardWidth, cardHeight);
            //drawCard(cardHand[7], (float) camera.viewportWidth / (640f / 500f), 0, cardWidth, cardHeight);
            //drawCard(cardHand[8], (float) camera.viewportWidth / (640f / 550f), 0, cardWidth, cardHeight);
        }

        for(int i = 0; i < 5; i++) {
            drawCard(programRegister[0+i], leftOfBoard + leftOfBoard / 2 - cardWidth*1.25f + cardWidth / 2 * i, camera.viewportHeight/(640f/250f), cardWidth, cardHeight);
            //drawCard(programRegister[1], camera.viewportWidth / (640f / 400f), 250, cardWidth, cardHeight);
            //drawCard(programRegister[2], camera.viewportWidth / (640f / 450f), 250, cardWidth, cardHeight);
            //drawCard(programRegister[3], camera.viewportWidth / (640f / 500f), 250, cardWidth, cardHeight);
            //drawCard(programRegister[4], camera.viewportWidth / (640f / 550f), 250, cardWidth, cardHeight);
        }
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

    /**
     * Moves a program card from hand to program register
     *
     * @param card to be moved to program register
     */
    public void addCardToProgramRegister(ICard card) {
        for (int i = 0; i < cardHand.length; i++) {
            if (cardHand[i] == card) {
                cardHand[i] = null;
                touchableCardHand.removeCard(i);
                break;
            }
        }
        ICard copy = card.copy();
        for (int i = 0; i < programRegister.length; i++) {
            if (programRegister[i] == null) {
                programRegister[i] = copy;
                break;
            }
        }
        Arrays.sort(programRegister, new ReverseCardComparator());
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
        return touchableCardHand.contains(x, y);
    }

}
