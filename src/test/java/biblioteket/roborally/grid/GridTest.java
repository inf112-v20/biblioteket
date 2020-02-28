package biblioteket.roborally.grid;

import biblioteket.roborally.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GridTest {
    int width, height;
    Grid<Object> grid;
    Random random;

    @BeforeEach
    public void setUp() {
        random = new Random();
        // Random width and height between 5 and 14
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

    @Test
    void allNeighboursNotInCornerReturnsAllEightNeighboursTest() {
        // x,y coordinates not touching edge of grid
        int x = random.nextInt(width - 2) + 1;
        int y = random.nextInt(height - 2) + 1;
        List<IPosition<Object>> neighbourhood = grid.allNeighbours(x, y);

        IPosition<Object> northWest = grid.getPosition(x - 1, y - 1);
        assert (neighbourhood.contains(northWest));
        IPosition<Object> north = grid.getPosition(x, y - 1);
        assert (neighbourhood.contains(north));
        IPosition<Object> northEast = grid.getPosition(x + 1, y - 1);
        assert (neighbourhood.contains(northEast));
        IPosition<Object> west = grid.getPosition(x - 1, y);
        assert (neighbourhood.contains(west));
        IPosition<Object> east = grid.getPosition(x + 1, y);
        assert (neighbourhood.contains(east));
        IPosition<Object> southWest = grid.getPosition(x - 1, y + 1);
        assert (neighbourhood.contains(southWest));
        IPosition<Object> south = grid.getPosition(x, y + 1);
        assert (neighbourhood.contains(south));
        IPosition<Object> southEast = grid.getPosition(x + 1, y + 1);
        assert (neighbourhood.contains(southEast));

        // Check that these locations are the only ones in neighbourhood
        assert (neighbourhood.size() == 8);
    }

    @Test
    void allNeighboursDoesNotContainInitialLocationTest() {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        IPosition<Object> initialPosition = grid.getPosition(x, y);
        List<IPosition<Object>> neighbourhood = grid.allNeighbours(initialPosition);

        assertFalse(neighbourhood.contains(initialPosition));
    }

    @Test
    void allNeighborsOfCornerPositionHandlesIndexOutOfBoundsExceptionTest() {
        List<IPosition<Object>> neighbourhood = grid.allNeighbours(0, 0);

        IPosition<Object> east = grid.getPosition(1, 0);
        assert (neighbourhood.contains(east));
        IPosition<Object> south = grid.getPosition(0, 1);
        assert (neighbourhood.contains(south));
        IPosition<Object> southEast = grid.getPosition(1, 1);
        assert (neighbourhood.contains(southEast));

        // Check that these locations are the only ones in neighbourhood
        assert (neighbourhood.size() == 3);
    }

    @Test
    void cardinalNeighborsNotInCornerReturnsAllCardinalNeighborsTest() {
        // x,y coordinates not touching edge of grid
        int x = random.nextInt(width - 2) + 1;
        int y = random.nextInt(height - 2) + 1;
        List<IPosition<Object>> neighbourhood = grid.cardinalNeighbours(x, y);

        IPosition<Object> north = grid.getPosition(x, y - 1);
        assert (neighbourhood.contains(north));
        IPosition<Object> south = grid.getPosition(x, y + 1);
        assert (neighbourhood.contains(north));
        IPosition<Object> east = grid.getPosition(x - 1, y);
        assert (neighbourhood.contains(north));
        IPosition<Object> west = grid.getPosition(x + 1, y);
        assert (neighbourhood.contains(north));

        // Check that these locations are the only ones in neighbourhood
        assert (neighbourhood.size() == 4);
    }

    @Test
    void cardinalNeighboursDoesNotContainInitialLocationTest() {
        int x = random.nextInt(height - 1);
        int y = random.nextInt(width - 1);
        IPosition<Object> initialPosition = grid.getPosition(x, y);
        List<IPosition<Object>> neighbourhood = grid.cardinalNeighbours(x, y);

        assertFalse(neighbourhood.contains(initialPosition));
    }

    @Test
    void cardinalNeighborsInCornerHandlesIndexOutOfBoundsTest() {
        IPosition<Object> initialPosition = grid.getPosition(0, 0);
        List<IPosition<Object>> neighbourhood = grid.cardinalNeighbours(initialPosition);

        IPosition<Object> east = grid.getPosition(1, 0);
        assert (neighbourhood.contains(east));
        IPosition<Object> south = grid.getPosition(0, 1);
        assert (neighbourhood.contains(south));

        // Check that these locations are the only ones in neighbourhood
        assert (neighbourhood.size() == 2);
    }

    @Test
    void positionInDirectionReturnsCorrectPositionTest() {
        // x,y coordinates not touching edge of grid
        int x = random.nextInt(width - 2) + 1;
        int y = random.nextInt(height - 2) + 1;
        IPosition<Object> initialPosition = grid.getPosition(x, y);

        IPosition<Object> north = grid.positionInDirection(initialPosition, Direction.NORTH);
        assert (north.getX() == x);
        assert (north.getY() == y - 1);

        IPosition<Object> south = grid.positionInDirection(initialPosition, Direction.SOUTH);
        assert (south.getX() == x);
        assert (south.getY() == y + 1);

        IPosition<Object> east = grid.positionInDirection(initialPosition, Direction.EAST);
        assert (east.getX() == x + 1);
        assert (east.getY() == y);

        IPosition<Object> west = grid.positionInDirection(initialPosition, Direction.WEST);
        assert (west.getX() == x - 1);
        assert (west.getY() == y);

    }


}
