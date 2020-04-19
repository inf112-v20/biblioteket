//package biblioteket.roborally.userinterface;
//
//import biblioteket.roborally.programcards.Card;
//import biblioteket.roborally.programcards.ICard;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.Random;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class TouchableCardsTest {
//    Random random = new Random();
//    TouchableCards touchableCards;
//
//    @BeforeEach
//    void setUp() {
//        touchableCards = new TouchableCards(100);
//    }
//
//    @Test
//    void coordinatesInsideTouchableCardReturnCorrectCardTest() {
//        ICard card = new Card(null, 0);
//        int x = random.nextInt(100);
//        int y = random.nextInt(100);
//        int width = random.nextInt(1000 - x) + x;
//        int height = random.nextInt(1000 - y) + y;
//
//        touchableCards.initializeCard(0, x, y, width, height);
//        touchableCards.setCard(0, card);
//
//        for (int i = 0; i < 1000; i++) {
//            int xCoordinate = random.nextInt(width - x) + x;
//            int yCoordinate = random.nextInt(height - y) + y;
//            ICard retrievedCard = touchableCards.contains(xCoordinate, yCoordinate);
//            assertEquals(card, retrievedCard);
//        }
//    }
//
//
//}
