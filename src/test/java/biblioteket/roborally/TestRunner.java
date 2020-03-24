package biblioteket.roborally;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.GL20;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.mockito.Mockito;

/**
 * Runs a headless (without graphics) test of an application.
 */
public class TestRunner implements BeforeAllCallback {
    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        new HeadlessApplication(new ApplicationListener() {
            @Override
            public void create() {
                // Intentionally left empty because this is headless test
            }

            @Override
            public void resize(int width, int height) {
                // Intentionally left empty because this is headless test
            }

            @Override
            public void render() {
                // Intentionally left empty because this is headless test
            }

            @Override
            public void pause() {
                // Intentionally left empty because this is headless test
            }

            @Override
            public void resume() {
                // Intentionally left empty because this is headless test
            }

            @Override
            public void dispose() {
                // Intentionally left empty because this is headless test
            }
        });

        Gdx.gl20 = Mockito.mock(GL20.class);
        Gdx.gl = Gdx.gl20;
    }
}
