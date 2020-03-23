package biblioteket.roborally.elements.interactingElements;

import biblioteket.roborally.actors.IPlayer;
import biblioteket.roborally.actors.Player;
import biblioteket.roborally.actors.Robot;
import biblioteket.roborally.board.DirVector;
import biblioteket.roborally.board.Direction;
import biblioteket.roborally.elements.ArchiveMarkerElement;
import biblioteket.roborally.elements.InteractingElements.cogs.CogElement;
import biblioteket.roborally.elements.InteractingElements.cogs.LeftRotatingCogElement;
import biblioteket.roborally.elements.InteractingElements.cogs.RightRotatingCogElement;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CogTest {
    IPlayer player;

    @BeforeEach
    void setUp(){
        player = new Player(new TiledMapTileLayer.Cell());
        player.setRobot(new Robot(new ArchiveMarkerElement(1)));
    }

    @Test
    void leftRotatingCogElementRotatesRobotToTheLeftTest(){
        CogElement cog = new LeftRotatingCogElement();
        Direction initialDirection = player.getRobot().getDirection();

        cog.interact(player);
        Direction postInteractionDirection = player.getRobot().getDirection();

        assertEquals(initialDirection.left(),postInteractionDirection);
    }

    @Test
    void rightRotatingCogElementRotatesRobotToTheRightTest(){
        CogElement cog = new RightRotatingCogElement();
        Direction initialDirection = player.getRobot().getDirection();

        cog.interact(player);
        Direction postInteractionDirection = player.getRobot().getDirection();

        assertEquals(initialDirection.right(),postInteractionDirection);
    }
}
