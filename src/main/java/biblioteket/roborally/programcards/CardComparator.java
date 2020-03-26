package biblioteket.roborally.programcards;

import java.util.Comparator;

public class CardComparator implements Comparator<ICard> {
    @Override
    public int compare(ICard o1, ICard o2) {
        return o1.getPriorityNumber() - o2.getPriorityNumber();
    }
}
