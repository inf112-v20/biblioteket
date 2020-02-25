package biblioteket.roborally.Grid;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionTest {
    IPosition<Integer> position;

    @BeforeEach
    public void setUp(){
        position = new Position(0,0);
    }


    @Test
    void putTest(){
        position.put(1);
        int get = position.getContents().get(0);
        assertEquals(1,get);

    }

    @Test
    void removeTest(){
        position.put(1);
        int removed = position.remove(1);
        assertEquals(1,removed);
        assert (position.getContents().isEmpty());
    }
}
