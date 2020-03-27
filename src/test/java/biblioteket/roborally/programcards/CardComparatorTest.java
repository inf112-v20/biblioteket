package biblioteket.roborally.programcards;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardComparatorTest {
    ReverseCardComparator comparator;

    @BeforeEach
    void setUp() {
        comparator = new ReverseCardComparator();
    }

    @Test
    void correctlyComparesTwoCardsTest(){
        ICard lowerPriorityCard = new Card(null, 1);
        ICard higherPriorityCard = new Card(null, 2);

        // comparator should return the card with the lowest priority as largest
        assertTrue(comparator.compare(lowerPriorityCard,higherPriorityCard) > 0);
    }

    @Test
    void nullValuesLargerThanCardTest(){
        ICard card = new Card(null, 1);
        ICard nullCard = null;

        assertTrue(comparator.compare(card,nullCard) < 0);
    }

    @Test
    void correctlySortsCardsTest(){
        Random random = new Random();
        int size = 100;
        ICard[] cards = new ICard[size];
        // Seed array with 100 cards with random priority
        for (int i = 0; i < size; i++) {
            int randomPriority = random.nextInt(100);
            ICard card = new Card(null, randomPriority);
            cards[i] = card;
        }

        // Sort array
        Arrays.sort(cards, comparator);

        // Check that every card in position i+1 has lower or equal priority than any card in index i
        for (int i = 0; i < size - 1; i++) {
            int lowerIndexPriorityNumber = cards[i].getPriorityNumber();
            int higherIndexPriorityNumber = cards[i + 1].getPriorityNumber();
            assertTrue(lowerIndexPriorityNumber >= higherIndexPriorityNumber);
        }

    }

    @Test
    void correctlySortsCardsAndNullValuesTest(){
        Random random = new Random();
        int size = 100;
        ICard[] cards = new ICard[size];
        // Seed array with 90 cards with random priority and 10 null values
        for (int i = 0; i < size; i++) {
            if(i % 10 != 0){
                int randomPriority = random.nextInt(100);
                ICard card = new Card(null, randomPriority);
                cards[i] = card;
            }
        }

        // Sort array
        Arrays.sort(cards,comparator);

        // Check that every card in position i+1 has lower or equal priority than any card in index i,
        for (int i = 0; i < size - 11; i++) {
            int lowerIndexPriorityNumber = cards[i].getPriorityNumber();
            int higherIndexPriorityNumber = cards[i + 1].getPriorityNumber();
            assertTrue(lowerIndexPriorityNumber >= higherIndexPriorityNumber);
        }
        // Check that all 10 null values are at the end of the list
        for (int i = size - 10; i < size; i++) {
            assertNull(cards[i]);
        }


    }
}
