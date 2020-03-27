package biblioteket.roborally.userinterface;

import biblioteket.roborally.programcards.ICard;
import com.badlogic.gdx.math.Rectangle;

/**
 * Datastructure for multiple rectangles which can be have coordinates and
 * return an ICard if they are touched
 */
public class TouchableCards extends Rectangle {
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
