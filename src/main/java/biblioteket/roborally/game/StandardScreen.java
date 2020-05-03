package biblioteket.roborally.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class StandardScreen implements Screen {

    private static final OrthographicCamera camera = new OrthographicCamera();
    public final RoboRally game;
    public final Texture background;
    public final Texture logo;
    private final Assets assets = new Assets();
    public float buttonCentered;
    public float buttonWidth;
    public float buttonHeight;
    public float exitY;
    public float centerOfScreenX;

    public StandardScreen(RoboRally game) {
        this.game = game;

        camera.setToOrtho(false, game.getWidth(), game.getHeight());

        assets.load();
        assets.getManager().finishLoading();
        background = assets.getManager().get(Assets.BACKGROUND, Texture.class);
        logo = assets.getManager().get(Assets.LOGO, Texture.class);

    }

    public static OrthographicCamera getCamera() {
        return camera;
    }

    //Resizing does not work if made in constructor.
    public void buttonsSizeAndScreenPlacement() {
        centerOfScreenX = camera.viewportWidth / 2f;
        buttonWidth = camera.viewportHeight / (356f / 100f);
        buttonCentered = camera.viewportWidth / 2f - buttonWidth / 2f;
        buttonHeight = camera.viewportHeight / (256f / 100f);
        exitY = camera.viewportHeight / 20f;
    }

    public boolean hoverOverQuit() {
        return Gdx.input.getX() < buttonCentered + buttonWidth && Gdx.input.getX() > buttonCentered && camera.viewportHeight - Gdx.input.getY() < exitY + buttonHeight / 1.35f && camera.viewportHeight - Gdx.input.getY() > exitY + buttonWidth / (1.5f);
    }

    public Assets getAssets() {
        return assets;
    }

    @Override
    public void show() {
        // Not used, but method must be overwritten
    }

    @Override
    public void render(float v) {

        Gdx.gl.glClearColor(v, v, v, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.getBatch().begin();
        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);
        game.getBatch().draw(logo, camera.viewportWidth / 2f - (float) logo.getWidth() / 2f * camera.viewportHeight / 640f, camera.viewportHeight / 2f, logo.getWidth() * camera.viewportHeight / 640f, logo.getHeight() * camera.viewportHeight / 640f);
        game.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        game.getBatch().setProjectionMatrix(camera.combined);
        camera.update();
    }

    @Override
    public void pause() {
        // Not used, but method must be overwritten
    }

    @Override
    public void resume() {
        // Not used, but method must be overwritten
    }

    @Override
    public void hide() {
        // Not used, but method must be overwritten
    }

    @Override
    public void dispose() {
        assets.dispose();
    }
}
