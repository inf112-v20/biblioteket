package biblioteket.roborally.board;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.GL20;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TileTest {
    private static Board board;

    @BeforeAll
    static void setup() {
        Application _application = new HeadlessApplication(new ApplicationListener() {
            @Override public void create() {}
            @Override public void resize(int width, int height) {}
            @Override public void render() {}
            @Override public void pause() {}
            @Override public void resume() {}
            @Override public void dispose() {}
        });

        Gdx.gl20 = Mockito.mock(GL20.class);
        Gdx.gl = Gdx.gl20;

        board = new Board("assets/risky_exchange.tmx");
    }

    @Test
    void repairPositionTest() {
        assertEquals(Tile.SINGLE_REPAIR.getValue(), board.getGroundLayer().getCell(0,16).getTile().getId());
        assertEquals(Tile.SINGLE_REPAIR.getValue(), board.getGroundLayer().getCell(11,5).getTile().getId());
        assertEquals(Tile.DOUBLE_REPAIR.getValue(), board.getGroundLayer().getCell(7,9).getTile().getId());
    }

    @Test
    void spawnPositionTest() {
        assertEquals(Tile.SPAWN_1.getValue(), board.getGroundLayer().getCell(5, 0).getTile().getId());
        assertEquals(Tile.SPAWN_2.getValue(), board.getGroundLayer().getCell(6, 0).getTile().getId());
        assertEquals(Tile.SPAWN_3.getValue(), board.getGroundLayer().getCell(3, 1).getTile().getId());
        assertEquals(Tile.SPAWN_4.getValue(), board.getGroundLayer().getCell(8, 1).getTile().getId());
        assertEquals(Tile.SPAWN_5.getValue(), board.getGroundLayer().getCell(1, 2).getTile().getId());
        assertEquals(Tile.SPAWN_6.getValue(), board.getGroundLayer().getCell(10, 2).getTile().getId());
        assertEquals(Tile.SPAWN_7.getValue(), board.getGroundLayer().getCell(0, 3).getTile().getId());
        assertEquals(Tile.SPAWN_8.getValue(), board.getGroundLayer().getCell(11, 3).getTile().getId());
    }

    @Test
    void holePositionTest() {
        assertEquals(Tile.HOLE.getValue(), board.getGroundLayer().getCell(0, 6).getTile().getId());
        assertEquals(Tile.HOLE.getValue(), board.getGroundLayer().getCell(2, 15).getTile().getId());
    }

    @Test
    void flagPositionTest() {
        assertEquals(Tile.FLAG_1.getValue(), board.getFlagLayer().getCell(7, 15).getTile().getId());
        assertEquals(Tile.FLAG_2.getValue(), board.getFlagLayer().getCell(9, 9).getTile().getId());
        assertEquals(Tile.FLAG_3.getValue(), board.getFlagLayer().getCell(1, 12).getTile().getId());
    }
}
