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
    private final RoboRally game;
    private final OrthographicCamera camera;
    private Texture logo;
    private Texture background;
    private Texture playPre;
    private Texture playPost;
    private Texture quitPre;
    private Texture quitPost;
    private int buttonHeight = 250;
    private int buttonWidth = 180;


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
        int center = (int) camera.viewportWidth / 2 - buttonWidth / 2;

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0, 700, 700);
        game.getBatch().draw(logo, 70, 340);
        game.getBatch().draw(playPre, center, camera.viewportHeight / 4, buttonWidth, buttonHeight);
        game.getBatch().draw(quitPre, center, camera.viewportHeight / 10, buttonWidth, buttonHeight);

        if (Gdx.input.getX() < center + buttonWidth && Gdx.input.getX() > center && camera.viewportHeight - Gdx.input.getY() < camera.viewportHeight / 4 + buttonWidth && camera.viewportHeight - Gdx.input.getY() > camera.viewportHeight / 4 + buttonWidth / (1.5)) {
            game.getBatch().draw(quitPost, center, camera.viewportHeight / 10, buttonWidth, buttonHeight);
            if (Gdx.input.isTouched()) {
                game.setScreen(new GameScreen(game));
                dispose();
            }
        } else if (Gdx.input.getX() < center + buttonWidth && Gdx.input.getX() > center && camera.viewportHeight - Gdx.input.getY() < camera.viewportHeight / 7 + buttonWidth && camera.viewportHeight - Gdx.input.getY() > camera.viewportHeight / 10 + buttonWidth / (1.5)) {
            game.getBatch().draw(playPost, center, camera.viewportHeight / 4, buttonWidth, buttonHeight);
            if (Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
        } else {
            game.getBatch().draw(playPost, center, camera.viewportHeight / 4, buttonWidth, buttonHeight);
            game.getBatch().draw(quitPost, center, camera.viewportHeight / 10, buttonWidth, buttonHeight);

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
