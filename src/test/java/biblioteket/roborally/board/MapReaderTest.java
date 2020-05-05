package biblioteket.roborally.board;

import biblioteket.roborally.TestRunner;
import biblioteket.roborally.elements.ArchiveMarkerElement;
import biblioteket.roborally.elements.IElement;
import biblioteket.roborally.elements.walls.LaserWallElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(TestRunner.class)
public class MapReaderTest {
    private static IBoard board;
    private static MapReader mapReader;

    @BeforeAll
    static void setup(){
        board = new Board("assets/DizzyDash.tmx", null);
        mapReader = new MapReader(board);
    }

    @Test
    void getNumberOfFlagsTest(){
        int flags = mapReader.getNumberOfFlags();
        assertEquals(3, flags);
    }

    @Test
    void getArchiveMarkerTest(){
        ArchiveMarkerElement archiveMarker = mapReader.getArchiveMarker(1);
        assertEquals(1, archiveMarker.getArchiveNum());
    }

    @Test
    void getLaserWallsTest(){
        List<LaserWallElement> laserWalls = mapReader.getLaserWalls();
        System.out.println(laserWalls);
        assertFalse(laserWalls.isEmpty());

        for (IElement element : laserWalls) {
            assertTrue(element instanceof LaserWallElement);
        }
    }

}
