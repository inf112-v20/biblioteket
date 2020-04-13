package biblioteket.roborally.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

/**
 * The landing screen once the user starts the game, this is where the player(s)
 * select how many players there are, multiplayer etc. Currently only a simple
 * canvas is displayed that the user can click and then the game launches.
 */
public class MainMenuScreen implements Screen {
    private static final int BUTTON_HEIGHT = 250;
    private static final int BUTTON_WIDTH = 180;
    private final RoboRally game;
    private final OrthographicCamera camera;
    private final Texture logo;
    private final Texture background;
    private final Texture playPre;
    private final Texture playPost;
    private final Texture quitPre;
    private final Texture quitPost;


    public MainMenuScreen(final RoboRally game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, 640, 640);


        background = new Texture("assets/background2.jpg");
        logo = new Texture("assets/logo.png");
        playPre = new Texture("assets/buttons/playPre.png");
        playPost = new Texture("assets/buttons/playPost.png");
        quitPre = new Texture("assets/buttons/quitPre.png");
        quitPost = new Texture("assets/buttons/quitPost.png");

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        int center = (int) camera.viewportWidth / 2 - BUTTON_WIDTH / 2;

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0, 700, 700);
        game.getBatch().draw(logo, 70, 340);
        game.getBatch().draw(playPre, center, camera.viewportHeight / 4, BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(quitPre, center, camera.viewportHeight / 10, BUTTON_WIDTH, BUTTON_HEIGHT);

        if (Gdx.input.getX() < center + BUTTON_WIDTH && Gdx.input.getX() > center && camera.viewportHeight - Gdx.input.getY() < camera.viewportHeight / 4 + BUTTON_WIDTH && camera.viewportHeight - Gdx.input.getY() > camera.viewportHeight / 4 + BUTTON_WIDTH / (1.5)) {
            game.getBatch().draw(quitPost, center, camera.viewportHeight / 10, BUTTON_WIDTH, BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                game.setScreen(new GameScreen(game));
                dispose();
            }
        } else if (Gdx.input.getX() < center + BUTTON_WIDTH && Gdx.input.getX() > center && camera.viewportHeight - Gdx.input.getY() < camera.viewportHeight / 7 + BUTTON_WIDTH && camera.viewportHeight - Gdx.input.getY() > camera.viewportHeight / 10 + BUTTON_WIDTH / (1.5)) {
            game.getBatch().draw(playPost, center, camera.viewportHeight / 4, BUTTON_WIDTH, BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
        } else {
            game.getBatch().draw(playPost, center, camera.viewportHeight / 4, BUTTON_WIDTH, BUTTON_HEIGHT);
            game.getBatch().draw(quitPost, center, camera.viewportHeight / 10, BUTTON_WIDTH, BUTTON_HEIGHT);

        }

        game.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {

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
