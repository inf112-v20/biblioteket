package biblioteket.roborally.elements.interactingElements;

import biblioteket.roborally.actors.IPlayer;
import biblioteket.roborally.actors.IRobot;
import biblioteket.roborally.actors.Player;
import biblioteket.roborally.actors.Robot;
import biblioteket.roborally.elements.ArchiveMarkerElement;
import biblioteket.roborally.elements.Interactingelements.HoleElement;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HoleTest {
    private IPlayer player;

    @BeforeEach
    void setUp(){
        player = new Player(new TiledMapTileLayer.Cell());
        player.setRobot(new Robot(new ArchiveMarkerElement(1)));
    }

    @Test
    void interactingWithHoleAddsDamageTokenTest(){
        HoleElement hole = new HoleElement();
        hole.interact(player);

        assertEquals(1,player.getRobot().getNumberOfDamageTokens());

    }

    @Test
    void interactingWithHoleMovesRobotToArchiveMarkerTest(){
        HoleElement hole = new HoleElement();
        IRobot robot = player.getRobot();
        ArchiveMarkerElement archiveMarker = robot.getArchiveMarker();
        // Set robots archive marker to 1,1 and robots position to 5,5
        archiveMarker.setX(1);
        archiveMarker.setY(1);
        robot.setPosition(5,5);

        hole.interact(player);

        assertEquals(archiveMarker.getX(),robot.getPosition().getX());
        assertEquals(archiveMarker.getY(),robot.getPosition().getY());

    }
}
