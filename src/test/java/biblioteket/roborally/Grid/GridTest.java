package biblioteket.roborally.Grid;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GridTest {
    int width, height;
    Grid<Object> grid;
    Random random;

    @BeforeEach
    public void setUp() {
        random = new Random();
        // Width and height between 5 and 14
        this.width = random.nextInt(10) + 5;
        this.height = random.nextInt(10) + 5;
        this.grid = new Grid<>(width, height);
    }

    @Test
    void getPositionReturnsCorrectPositionTest() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                IPosition<Object> position = grid.getPosition(x, y);
                assert (x == position.getX());
                assert (y == position.getY());
            }
        }
    }

    @Test
    void placeElementPlacesElementInCorrectPositionTest() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Object placedObject = new Object();
                grid.placeElement(x, y, placedObject);
                Object retrievedObject = grid.getPosition(x, y).getContents().get(0);
                assertEquals(placedObject, retrievedObject);
            }
        }
    }

}
