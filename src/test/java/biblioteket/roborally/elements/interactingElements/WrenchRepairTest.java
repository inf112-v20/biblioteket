package biblioteket.roborally.elements.interactingElements;

import biblioteket.roborally.actors.IPlayer;
import biblioteket.roborally.actors.IRobot;
import biblioteket.roborally.actors.Player;
import biblioteket.roborally.actors.Robot;
import biblioteket.roborally.board.DirVector;
import biblioteket.roborally.board.Direction;
import biblioteket.roborally.elements.ArchiveMarkerElement;
import biblioteket.roborally.elements.interactingelements.SingleWrenchRepairElement;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WrenchRepairTest {
    private IPlayer player;

    @BeforeEach
    void setUp() {
        player = new Player(new TiledMapTileLayer.Cell());
        player.setRobot(new Robot(new ArchiveMarkerElement(1)));
    }

    @Test
    void singleWrenchRepairElementRemovesOneDamageTokenTest() {
        SingleWrenchRepairElement repair = new SingleWrenchRepairElement();
        // Add 5 damage tokens to robot
        player.getRobot().addDamageTokens(5);

        repair.interact(player);

        assertEquals(4, player.getRobot().getNumberOfDamageTokens());
    }

    @Test
    void wrenchElementUpdatesArchiveMarkerTest() {
        SingleWrenchRepairElement repair = new SingleWrenchRepairElement();
        IRobot robot = player.getRobot();
        DirVector newPosition = new DirVector(5, 5, Direction.NORTH);

        robot.setPosition(newPosition);
        repair.interact(player);

        assertEquals(newPosition.getX(), robot.getArchiveMarker().getX());
        assertEquals(newPosition.getY(), robot.getArchiveMarker().getY());
    }
}
