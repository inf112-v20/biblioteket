package biblioteket.roborally.programcards;

import java.util.Comparator;

/**
 * Comparator that compares ICards according to their priority number,
 * allowing null values to always be sorted to the left and highest priority cards to be sorted to the right
 */
public class ReverseCardComparator implements Comparator<ICard> {
    @Override
    public int compare(ICard o1, ICard o2) {
        if(o1 == o2) return 0;
        if(o1 == null) return 1;
        if(o2 == null) return -1;
        return o2.getPriorityNumber() - o1.getPriorityNumber();
    }
}
