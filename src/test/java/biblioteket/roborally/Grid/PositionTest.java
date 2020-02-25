package biblioteket.roborally.Grid;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionTest {
    IPosition<Object> position;

    @BeforeEach
    public void setUp(){
        position = new Position(0,0);
    }


    @Test
    void putTest(){
        Object object = new Object();
        position.put(object);
        Object retrievedObject = position.getContents().get(0);
        assertEquals(object,retrievedObject);

    }

    @Test
    void removeTest(){
        Object object = new Object();
        position.put(object);
        boolean removed = position.remove(object);
        assert(removed);
        List contents = position.getContents();
        assert(contents.isEmpty());
        assert (position.getContents().isEmpty());
    }
}
