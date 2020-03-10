package biblioteket.roborally.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import javax.xml.soap.Text;

/**
 * The landing screen once the user starts the game, this is where the player(s)
 * select how many players there are, multiplayer etc. Currently only a simple
 * canvas is displayed that the user can click and then the game launches.
 */
public class MainMenuScreen implements Screen {
    private final RoboRally game;
    private Texture logo;
    private Texture background;
    private Texture button;
    private Texture button2;
    OrthographicCamera camera;
    private SpriteBatch batch;

    public MainMenuScreen(final RoboRally game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        background = new Texture("assets/background2.jpg");
        logo = new Texture("assets/logo.png");
        button = new Texture("assets/button.png");
        button2 = new Texture("assets/buttoninactive.png");

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        game.getBatch().begin();
        //game.getFont().draw(game.getBatch(), "ROBORALLY", 300, 320);
        //game.getFont().draw(game.getBatch(), "Tap anywhere to begin!", 300, 300);
        game.getBatch().draw(background, 0, 0,640,640);
        game.getBatch().draw(logo, 70,340);
        int x = (int) camera.viewportWidth / 2 - button.getWidth() / 2;



        if (Gdx.input.getX() < x + button.getWidth() && Gdx.input.getX() > x && camera.viewportHeight - Gdx.input.getY() < 40 + 360 && camera.viewportHeight - Gdx.input.getY() > 40) {
            game.getBatch().draw(button, 180,40,270,360);
            if(Gdx.input.isTouched()) {
                game.setScreen(new GameScreen(game));
                dispose();
            }
        }
        else {
            game.getBatch().draw(button2, 180,40,270,360);
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
