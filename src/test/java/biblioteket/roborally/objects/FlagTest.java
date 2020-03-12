package biblioteket.roborally.objects;

import biblioteket.roborally.IElement;
import biblioteket.roborally.grid.IPosition;
import biblioteket.roborally.grid.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FlagTest {

    IElement Flag;

    @BeforeEach
    void setUp() {
        Flag = new Flag();
    }

    @Test
    void isMovable() {
        IPosition originalPos = Flag.getPos();
        IPosition newPos = new Position(10, 10);
        Flag.setPos(newPos);
        assertTrue(Flag.getPos()!=newPos);
    }


    //@Override
    //void isPickupable() {

    //}


}
