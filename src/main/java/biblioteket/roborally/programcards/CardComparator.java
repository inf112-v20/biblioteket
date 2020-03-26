package biblioteket.roborally.programcards;

import java.util.Comparator;

public class CardComparator implements Comparator<ICard> {
    @Override
    public int compare(ICard o1, ICard o2) {
        if(o1 == null) return 1;
        if(o2 == null) return -1;
        return o1.getPriorityNumber() - o2.getPriorityNumber();
    }
}
