package biblioteket.roborally.userinterface;

import biblioteket.roborally.programcards.ICard;
import com.badlogic.gdx.math.Rectangle;

public class TouchableCards extends Rectangle {
    private final ICard card;

    public TouchableCards(int x, int y, double width, double height, ICard card){
        super(x,y,(float)width,(float)height);
        this.card = card;
    }

    public ICard getCard(){
        return card;
    }
}
