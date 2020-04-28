package biblioteket.roborally.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class EndGameScreen implements Screen {

    private final RoboRally game;
    private final OrthographicCamera camera;

    private final Texture quitPre;
    private final Texture quitPost;
    private final Texture mainMenuPre;
    private final Texture mainMenuPost;
    private final Texture background;
    private final Texture logo;
    private float center;
    private float buttonWidth;
    private float buttonHeight;
    private float mainMenuY;
    private float exitY;
    private final BitmapFont font;


    public EndGameScreen(final RoboRally game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, 640, 640);
        font = new BitmapFont();
        font.getData().setScale(1.5f, 1.5f);

        background = new Texture("assets/background2.jpg");
        quitPre = new Texture("assets/buttons/quitPost.png");
        quitPost = new Texture("assets/buttons/quitPre.png");
        mainMenuPre = new Texture("assets/buttons/mainMenuPre.png");
        mainMenuPost = new Texture("assets/buttons/mainMenuPost.png");
        logo = new Texture("assets/logo.png");
    }

    @Override
    public void show() {

    }

    public void buttonSize() {
        center = camera.viewportWidth / 2f - buttonWidth / 2f;
        buttonHeight = camera.viewportHeight / ((256f / 100f));
        buttonWidth = camera.viewportHeight / (356f / 100f);
        mainMenuY = camera.viewportHeight / (8f);
        exitY = camera.viewportHeight / 130f;
    }

    @Override
    public void render(float v) {
        buttonSize();
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);
        game.getBatch().draw(logo, camera.viewportWidth / 2f - (float) logo.getWidth() / 2f * camera.viewportHeight / 640f, camera.viewportHeight / 2f, logo.getWidth() * camera.viewportHeight / 640f, logo.getHeight() * camera.viewportHeight / 640f);
        game.getBatch().draw(mainMenuPre, center, mainMenuY, buttonWidth, buttonHeight);
        game.getBatch().draw(quitPre, center, exitY, buttonWidth, buttonHeight);


        if (Gdx.input.getX() < center + buttonWidth && Gdx.input.getX() > center && camera.viewportHeight - Gdx.input.getY() < mainMenuY + buttonHeight / 1.35f && camera.viewportHeight - Gdx.input.getY() > mainMenuY + buttonWidth / (1.5f)) {
            game.getBatch().draw(mainMenuPost, center, mainMenuY, buttonWidth, buttonHeight);
            if (Gdx.input.isTouched()) {
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
        } else if (Gdx.input.getX() < center + buttonWidth && Gdx.input.getX() > center && camera.viewportHeight - Gdx.input.getY() < exitY + buttonHeight / 1.35f && camera.viewportHeight - Gdx.input.getY() > exitY + buttonWidth / (1.5f)) {
            game.getBatch().draw(quitPost, center, exitY, buttonWidth, buttonHeight);
            if (Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
        }
        font.draw(game.getBatch(), "Player name here" + " has won the game!", camera.viewportWidth / 2f - (float) logo.getWidth() / 3.3f * camera.viewportHeight / 640f, camera.viewportHeight / 2f);
        game.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        camera.update();
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
