package biblioteket.roborally.userinterface;

import biblioteket.roborally.programcards.ICard;
import com.badlogic.gdx.math.Rectangle;

public class TouchableCards extends Rectangle {
    private final ICard card;

    public TouchableCards(int x, int y, float width, float height, ICard card){
        super(x,y,width,height);
        this.card = card;
    }

    public ICard getCard(){
        return card;
    }
}
