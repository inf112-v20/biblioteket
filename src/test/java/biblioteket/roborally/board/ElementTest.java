package biblioteket.roborally.board;

import biblioteket.roborally.TestRunner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(TestRunner.class)
public class ElementTest {
    private static Board board;

    @BeforeAll
    static void setup() {
        board = new Board("assets/risky_exchange.tmx");
    }

    @Test
    void repairPositionTest() {
        assertEquals(Element.SINGLE_REPAIR.getValue(), board.getGroundLayer().getCell(0, 15).getTile().getId());
        assertEquals(Element.SINGLE_REPAIR.getValue(), board.getGroundLayer().getCell(11, 4).getTile().getId());
        assertEquals(Element.DOUBLE_REPAIR.getValue(), board.getGroundLayer().getCell(7, 8).getTile().getId());
    }

    @Test
    void spawnPositionTest() {
        assertEquals(Element.SPAWN_1.getValue(), board.getGroundLayer().getCell(5, 0).getTile().getId());
        assertEquals(Element.SPAWN_2.getValue(), board.getGroundLayer().getCell(6, 0).getTile().getId());
        assertEquals(Element.SPAWN_3.getValue(), board.getGroundLayer().getCell(3, 1).getTile().getId());
        assertEquals(Element.SPAWN_4.getValue(), board.getGroundLayer().getCell(8, 1).getTile().getId());
        assertEquals(Element.SPAWN_5.getValue(), board.getGroundLayer().getCell(1, 2).getTile().getId());
        assertEquals(Element.SPAWN_6.getValue(), board.getGroundLayer().getCell(10, 2).getTile().getId());
        assertEquals(Element.SPAWN_7.getValue(), board.getGroundLayer().getCell(0, 3).getTile().getId());
        assertEquals(Element.SPAWN_8.getValue(), board.getGroundLayer().getCell(11, 3).getTile().getId());
    }

    @Test
    void holePositionTest() {
        assertEquals(Element.HOLE.getValue(), board.getGroundLayer().getCell(0, 5).getTile().getId());
        assertEquals(Element.HOLE.getValue(), board.getGroundLayer().getCell(2, 14).getTile().getId());
    }

    @Test
    void flagPositionTest() {
        assertEquals(Element.FLAG_1.getValue(), board.getFlagLayer().getCell(7, 14).getTile().getId());
        assertEquals(Element.FLAG_2.getValue(), board.getFlagLayer().getCell(9, 8).getTile().getId());
        assertEquals(Element.FLAG_3.getValue(), board.getFlagLayer().getCell(1, 11).getTile().getId());
    }
}
