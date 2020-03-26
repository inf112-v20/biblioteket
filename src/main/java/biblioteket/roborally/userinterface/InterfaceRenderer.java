package biblioteket.roborally.userinterface;

import biblioteket.roborally.board.IBoard;
import biblioteket.roborally.programcards.ICard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class InterfaceRenderer {

    private Texture background;
    private Texture playerOverview;
    private Texture flag;
    private Texture hp;

    private Texture emptyCard;
    private Texture moveOneCard;
    private Texture moveTwoCard;
    private Texture moveThreeCard;
    private Texture backUpCard;
    private Texture rotateRightCard;
    private Texture rotateLeftCard;
    private Texture uTurnCard;

    private SpriteBatch batch;
    private BitmapFont font;

    private int flagsVisited;
    private int lives;
    private ICard[] cards;
    private ArrayList<TouchableCards> touchableCards;

    public InterfaceRenderer(){
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
        cards = new ICard[9];
        touchableCards = new ArrayList<>();
    }

    public void render(IBoard board){
        batch.begin();
        batch.draw(playerOverview, 0, Gdx.graphics.getHeight() - 90, Gdx.graphics.getWidth(), 90);
        batch.draw(background, board.getTileWidth(), 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(flag, 290, Gdx.graphics.getHeight() - 180, 40, 40);
        batch.draw(hp, 330, Gdx.graphics.getHeight() - 180, 40, 40);
        font.draw(batch, Integer.toString(flagsVisited), 310, Gdx.graphics.getHeight() - 165);
        font.draw(batch, Integer.toString(lives), 360, Gdx.graphics.getHeight() - 165);


        drawCard(0, 375, 100, 100, 90);
        drawCard(1, 425, 100, 100, 90);
        drawCard(2, 475, 100, 100, 90);
        drawCard(3, 525, 100, 100, 90);
        drawCard(4, 400, 0, 100, 90);
        drawCard(5, 450, 0, 100, 90);
        drawCard(6, 350, 0, 100, 90);
        drawCard(7, 500, 0, 100, 90);
        drawCard(8, 550, 0, 100, 90);

        batch.end();
    }

    private void drawCard(int i, int x, int y, float width, float height){
        ICard card = cards[i];
        Texture cardTexture = getCardTexture(card);
        batch.draw(cardTexture,x,y,width,height);
        if(touchableCards.size() < 9 && card != null){
            TouchableCards touchableCard = new TouchableCards(x,y,width/2,height,card);
            touchableCards.add(touchableCard);
        }
    }

    private Texture getCardTexture(ICard card){
        if(card == null) return emptyCard;
        switch(card.getType()){
            case MOVE_1: return moveOneCard;
            case MOVE_2: return moveTwoCard;
            case MOVE_3: return moveThreeCard;
            case BACK_UP: return backUpCard;
            case ROTATE_RIGHT: return rotateRightCard;
            case ROTATE_LEFT: return rotateLeftCard;
            case U_TURN: return uTurnCard;
            default: return emptyCard;
        }
    }

    public void setFlagsVisited(int flagsVisited){
        this.flagsVisited = flagsVisited;
    }

    public void setLives(int lives){
        this.lives = lives;
    }

    public void setCardHand(ArrayList<ICard> cardHand){
        for (int i = 0; i < cardHand.size(); i++) {
            ICard card = cardHand.get(i);
            cards[i] = card;
        }

    }

    public ArrayList<TouchableCards> getTouchableCards(){
        return touchableCards;
    }

}
