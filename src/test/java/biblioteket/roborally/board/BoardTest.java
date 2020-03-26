package biblioteket.roborally.board;

import biblioteket.roborally.TestRunner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(TestRunner.class)
public class BoardTest {
    private static Board board;

    @BeforeAll
    static void setup() {
        board = new Board("assets/risky_exchange.tmx");
    }

    @Test
    void testBoardSize() {
        assertEquals(12, board.getWidth());
        assertEquals(16, board.getHeight());
    }

    @Test
    void testBoardTileSize() {
        assertEquals(300, board.getTileWidth());
        assertEquals(300, board.getTileHeight());
    }
}
