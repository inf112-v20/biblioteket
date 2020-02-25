package biblioteket.roborally.Grid;

import biblioteket.roborally.Elements.IElement;
import biblioteket.roborally.Elements.Robot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GridTest {
    int width, height;
    Grid grid;

    @BeforeEach
    public void setUp() {
        Random random = new Random();
        // Width and height between 5 and 14
        this.width = random.nextInt(10) + 5;
        this.height = random.nextInt(10) + 5;
        this.grid = new Grid(width,height);
    }

    @Test
    void getPositionReturnsCorrectPositionTest(){
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                IPosition position = grid.getPosition(x,y);
                assert (x == position.getX());
                assert (y == position.getY());
            }
        }
    }

    @Test
    void placeElementPlacesElementInCorrectPositionTest(){
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                IElement placeRobot = new Robot();
                grid.placeElement(x,y,placeRobot);
                IElement getRobot = (IElement) grid.getPosition(x,y).getContents().get(0);
                assertEquals(placeRobot,getRobot);
            }
        }



    }

}
