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

public class BoardTest {
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
    void testBoardSize() {
        assertEquals(12, board.getWidth());
        assertEquals(17, board.getHeight());
    }

    @Test
    void testBoardTileSize() {
        assertEquals(300, board.getTileWidth());
        assertEquals(300, board.getTileHeight());
    }
}
