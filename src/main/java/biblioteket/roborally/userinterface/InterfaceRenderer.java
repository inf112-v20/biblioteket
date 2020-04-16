package biblioteket.roborally.userinterface;

import biblioteket.roborally.actors.IPlayer;
import biblioteket.roborally.board.IBoard;
import biblioteket.roborally.game.GameScreen;
import biblioteket.roborally.programcards.ICard;
import biblioteket.roborally.programcards.ReverseCardComparator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
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

    private final SpriteBatch batch;
    private final BitmapFont font;
    private final SpriteBatch fontBatch;
    private final ICard[] cardHand;
    private final ICard[] programRegister;
    private final TouchableCards touchableCardHand;
    private final TouchableCards touchableProgramRegister;
    private int flagsVisited;
    private int lives;
    private final OrthographicCamera camera;
    private IPlayer player;

    private float cardWidth;
    private float cardHeight;
    private float touchableWidth;
    private float touchableHeight;
    private float rightOfBoard;
    private float healthFlagSize;
    private float damageTokenSize;


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
        for(int i = 0; i < cardHand.length; i++) {
            if(i < 4) {
                touchableCardHand.initializeCard(0+i, rightOfBoard + rightOfBoard / 2 - cardWidth + cardWidth / 2 * i, cardHeight, touchableWidth, touchableHeight);
            }
            else touchableCardHand.initializeCard(i, rightOfBoard + rightOfBoard / 2 - cardWidth*1.25f + cardWidth / 2 * (i-4), 0, touchableWidth, touchableHeight);
            //touchableCardHand.initializeCard(1, 425, 100, 40, 90);
            //touchableCardHand.initializeCard(2, 475, 100, 40, 90);
            //touchableCardHand.initializeCard(3, 525, 100, 40, 90);
        }

        // Progamregister
        touchableProgramRegister = new TouchableCards(programRegister.length);
        for(int i = 0; i < 5; i++) {
            touchableProgramRegister.initializeCard(0+i, rightOfBoard + rightOfBoard / 2 - cardWidth*1.25f + cardWidth / 2 * i, Gdx.graphics.getHeight()/(640f/250f), touchableWidth, touchableHeight);
            //touchableProgramRegister.initializeCard(1, 400, 250, 40, 90);
            //touchableProgramRegister.initializeCard(2, 450, 250, 40, 90);
            //touchableProgramRegister.initializeCard(3, 500, 250, 40, 90);
            //touchableProgramRegister.initializeCard(4, 550, 250, 40, 90);
        }


    }

    public void graphicSize() {
        cardWidth = (Gdx.graphics.getHeight()/(640f/130f));
        cardHeight = (Gdx.graphics.getHeight()/(640f/90f));
        touchableWidth = (Gdx.graphics.getWidth()/(640f/40f));
        touchableHeight = (Gdx.graphics.getWidth()/(640f/90f));
        rightOfBoard = Gdx.graphics.getWidth()/2;
        healthFlagSize = Gdx.graphics.getHeight()/(640f/40f);
        damageTokenSize = Gdx.graphics.getHeight()/(640f/35f);

    }


    public void renderInterface(IBoard board) {
        graphicSize();
        player = GameScreen.getPlayer();


        camera.update();
        batch.setProjectionMatrix(camera.combined);
        fontBatch.setProjectionMatrix(camera.combined);


        batch.begin();
        batch.draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);

        for (int i = 0; i < 4; i++) {
            drawCard(cardHand[i], rightOfBoard + rightOfBoard / 2 - cardWidth + cardWidth / 2 * i, cardHeight, cardWidth, cardHeight);
            //drawCard(cardHand[1], (float) camera.viewportWidth / (640f / 425f), cardHeight, cardWidth, cardHeight);
            //drawCard(cardHand[2], (float) camera.viewportWidth / (640f / 475f), cardHeight, cardWidth, cardHeight);
            //drawCard(cardHand[3], (float) camera.viewportWidth / (640f / 525f), cardHeight, cardWidth, cardHeight);
            //System.out.println(rightOfBoard + rightOfBoard / 2 - cardWidth + cardWidth / 2 * i);
        }
        for (int i = 0; i < 5; i++) {
            drawCard(cardHand[4 + i], rightOfBoard + rightOfBoard / 2 - cardWidth * 1.25f + cardWidth / 2 * i, 0, cardWidth, cardHeight);
            //drawCard(cardHand[5], (float) camera.viewportWidth / (640f / 400f), 0, cardWidth, cardHeight);
            //drawCard(cardHand[6], (float) camera.viewportWidth / (640f / 450f), 0, cardWidth, cardHeight);
            //drawCard(cardHand[7], (float) camera.viewportWidth / (640f / 500f), 0, cardWidth, cardHeight);
            //drawCard(cardHand[8], (float) camera.viewportWidth / (640f / 550f), 0, cardWidth, cardHeight);
        }

        for (int i = 0; i < 5; i++) {
            drawCard(programRegister[0 + i], rightOfBoard + rightOfBoard / 2 - cardWidth * 1.25f + cardWidth / 2 * i, camera.viewportHeight / (640f / 250f), cardWidth, cardHeight);
            //drawCard(programRegister[1], camera.viewportWidth / (640f / 400f), 250, cardWidth, cardHeight);
            //drawCard(programRegister[2], camera.viewportWidth / (640f / 450f), 250, cardWidth, cardHeight);
            //drawCard(programRegister[3], camera.viewportWidth / (640f / 500f), 250, cardWidth, cardHeight);
            //drawCard(programRegister[4], camera.viewportWidth / (640f / 550f), 250, cardWidth, cardHeight);
        }

        for (int i = 0; i < 4; i++) {
            // Player 1-4
            batch.draw(flag, (rightOfBoard/((320f/315f))) + rightOfBoard/4*i, camera.viewportHeight-(camera.viewportHeight/16)*140/100, healthFlagSize, healthFlagSize);
            batch.draw(hp, (rightOfBoard + healthFlagSize/4*2) + rightOfBoard/4*i, camera.viewportHeight-(camera.viewportHeight/16)*140/100, healthFlagSize, healthFlagSize);
            font.draw(batch, "Player " + Integer.toString(i + 1), rightOfBoard + rightOfBoard / 4 * i, camera.viewportHeight - (camera.viewportHeight / 16f) / 10f);
            font.draw(batch, Integer.toString(lives), rightOfBoard + healthFlagSize*1.2f + rightOfBoard / 4 * i, camera.viewportHeight - camera.viewportHeight/(640f/40f));
            font.draw(batch, Integer.toString(flagsVisited), rightOfBoard + healthFlagSize*0.2f + rightOfBoard / 4 * i, camera.viewportHeight - camera.viewportHeight/(640f/40f));

            // Player 5-8
            batch.draw(flag, (rightOfBoard/(320f/315f)) + rightOfBoard/4*i, camera.viewportHeight - ((camera.viewportHeight / 5f)), healthFlagSize, healthFlagSize);
            batch.draw(hp, (rightOfBoard + healthFlagSize/4*2) + rightOfBoard/4*i, camera.viewportHeight - ((camera.viewportHeight / 5f)), healthFlagSize, healthFlagSize);
            font.draw(batch, "Player " + Integer.toString(5+i), rightOfBoard + rightOfBoard / 4 * i, camera.viewportHeight - ((camera.viewportHeight / 8f)));
            font.draw(batch, Integer.toString(lives), rightOfBoard + healthFlagSize*1.2f + rightOfBoard / 4 * i, camera.viewportHeight - camera.viewportHeight/(640f/110f));
            font.draw(batch, Integer.toString(flagsVisited), rightOfBoard + healthFlagSize*0.2f + rightOfBoard / 4 * i, camera.viewportHeight - camera.viewportHeight/(640f/110f));
        }

        batch.end();

        fontBatch.begin();
        for(int i = 0; i < 4; i++) {
            if(cardHand[i] != null) {
                font.draw(fontBatch, Integer.toString(cardHand[i].getPriorityNumber()), rightOfBoard + rightOfBoard / 2 - cardWidth * 0.80f + cardWidth / 2 * i, cardHeight / (54f / 100f));
            }
        }
        for(int i = 4; i < 9; i++) {
            if (cardHand[i] != null) {
                font.draw(fontBatch, Integer.toString(cardHand[i].getPriorityNumber()), rightOfBoard + rightOfBoard / 2 - cardWidth * 1.06f + cardWidth / 2 * (i-4), cardHeight/(117f/100f));
            }
        }
        for(int i = 0; i < 5; i++) {
            if (programRegister[i] != null) {
                font.draw(fontBatch, Integer.toString(programRegister[i].getPriorityNumber()),rightOfBoard + rightOfBoard / 2 - cardWidth*1.06f + cardWidth / 2 * i, cardHeight*(3.63f));
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
