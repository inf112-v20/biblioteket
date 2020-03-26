package biblioteket.roborally.userinterface;

import biblioteket.roborally.programcards.ICard;
import com.badlogic.gdx.math.Rectangle;

public class TouchableCards extends Rectangle {
    TouchableCard[] cards;

    public TouchableCards(int size){
        cards = new TouchableCard[size];
    }

    public void initializeCard(int pos, float x, float y, float width, float height){
        cards[pos] = new TouchableCard(x,y,width,height);
    }

    public void setCard(int pos, ICard card){
        cards[pos].setCard(card);
    }

    public ICard getCard(int pos){
        return cards[pos].getCard();
    }

    public ICard contains(int x, int y){
        for (TouchableCard card : cards) {
            if (card.contains(x, y))
                return card.getCard();

        }
        return null;
    }


    private class TouchableCard extends Rectangle{
        private  ICard card;

        TouchableCard(float x, float y, float width, float height){
            super(x,y,width,height);
        }
        public void setCard(ICard card){
            this.card = card;
        }

        public ICard getCard(){
            return card;
        }
    }
}
