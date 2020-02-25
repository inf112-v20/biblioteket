
package biblioteket.roborally;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AppTest extends GameTest {
    @Test
    public void AssertGameNotNullTest() {
        Game game = new Game();
        assertNotNull(game);
    }
}
