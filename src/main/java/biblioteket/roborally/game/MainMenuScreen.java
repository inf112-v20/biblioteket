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
    private OrthographicCamera camera;
    private Texture logo;
    private Texture background;
    private Texture playPre;
    private Texture playPost;
    private Texture quitPre;
    private Texture quitPost;
    private float buttonHeight;
    private float buttonWidth;
    private float center;
    private float startY;
    private float exitY;


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

    public void buttonSize() {
        center = camera.viewportWidth / 2 - buttonWidth / 2;
        buttonHeight =  camera.viewportHeight/(256/100);
        buttonWidth = camera.viewportHeight/(356/100);
        startY = camera.viewportHeight/(6);
        exitY = camera.viewportHeight/130;
    }

    @Override
    public void render(float delta) {
        buttonSize();

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);
        game.getBatch().draw(logo, camera.viewportWidth/2 - logo.getWidth()/2*camera.viewportHeight/640, camera.viewportHeight/2,logo.getWidth()*camera.viewportHeight/640,logo.getHeight()*camera.viewportHeight/640);
        game.getBatch().draw(playPre, center, startY, buttonWidth, buttonHeight);
        game.getBatch().draw(quitPre, center, exitY, buttonWidth, buttonHeight);

        if (Gdx.input.getX() < center + buttonWidth && Gdx.input.getX() > center && camera.viewportHeight - Gdx.input.getY() < startY + buttonHeight/1.35 && camera.viewportHeight - Gdx.input.getY() > startY + buttonWidth / (1.35)) {
            game.getBatch().draw(quitPost, center, exitY, buttonWidth, buttonHeight);
            if (Gdx.input.isTouched()) {
                game.setScreen(new MapSelect(game));
                dispose();
            }
        } else if (Gdx.input.getX() < center + buttonWidth && Gdx.input.getX() > center && camera.viewportHeight - Gdx.input.getY() < exitY + buttonHeight/1.35 && camera.viewportHeight - Gdx.input.getY() > exitY + buttonWidth / (1.35)) {
            game.getBatch().draw(playPost, center, startY, buttonWidth, buttonHeight);
            if (Gdx.input.isTouched()) {
                Gdx.app.exit();

            }
        } else {
            game.getBatch().draw(playPost, center, startY, buttonWidth, buttonHeight);
            game.getBatch().draw(quitPost, center, exitY, buttonWidth, buttonHeight);

        }

        game.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);
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
