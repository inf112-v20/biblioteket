package biblioteket.roborally.objects;

import biblioteket.roborally.IElement;
import biblioteket.roborally.grid.IPosition;
import biblioteket.roborally.grid.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HoleTest {
    IElement Hole;

    @BeforeEach
    void setUp() {
        Hole = new Hole();
    }

    @Test
    void isImmovable() {
        IPosition originalPos = Hole.getPos();
        IPosition newPos = new Position(10, 10);
        Hole.setPos(newPos);
        assertTrue(Hole.getPos()==originalPos);
    }

}
