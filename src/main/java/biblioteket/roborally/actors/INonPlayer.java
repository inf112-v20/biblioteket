package biblioteket.roborally.actors;

import biblioteket.roborally.programcards.Card;

import java.util.List;

public interface INonPlayer extends IActor {
    List<Card> drawnCards();
}
