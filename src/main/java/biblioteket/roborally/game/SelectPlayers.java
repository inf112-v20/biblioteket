package biblioteket.roborally.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class SelectPlayers implements Screen {
    private final RoboRally game;
    OrthographicCamera camera;
    private Texture background;

    public SelectPlayers(final RoboRally game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, 640, 640);

        background = new Texture("assets/background2.jpg");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        camera.update();

        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0, 700, 700);
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
