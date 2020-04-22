package biblioteket.roborally.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class DifficultySelect implements Screen {

    private final RoboRally game;
    private OrthographicCamera camera;

    private float buttonHeight;
    private float buttonWidth;
    private float center;
    private float startY;
    private float exitY;

    private final Texture background;
    private final Texture logo;
    private final Texture easyPre;
    private final Texture easyPost;
    private final Texture normalPre;
    private final Texture normalPost;
    private final Texture hardPre;
    private final Texture hardPost;


    public DifficultySelect(final RoboRally game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, 640, 640);

        background = new Texture("assets/background2.jpg");
        logo = new Texture("assets/logo.png");
        easyPre = new Texture("assets/buttons/easyPre.jpg");
        easyPost = new Texture("assets/buttons/easyPost.jpg");
        normalPre = new Texture("assets/buttons/normalPre.jpg");
        normalPost = new Texture("assets/buttons/normalPost.jpg");
        hardPre = new Texture("assets/buttons/hardPre.jpg");
        hardPost = new Texture("assets/buttons/hardPost.jpg");

    }


    @Override
    public void show() {

    }

    public void buttonSize() {
        center = camera.viewportWidth / 2 - buttonWidth / 2;
        buttonHeight = camera.viewportHeight / (256 / 100);
        buttonWidth = camera.viewportHeight / (356 / 100);
        startY = camera.viewportHeight / (6);
        exitY = camera.viewportHeight / 130;
    }

    @Override
    public void render(float v) {

        buttonSize();

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);
        game.getBatch().draw(logo, camera.viewportWidth / 2 - logo.getWidth() / 2 * camera.viewportHeight / 640, camera.viewportHeight / 2, logo.getWidth() * camera.viewportHeight / 640, logo.getHeight() * camera.viewportHeight / 640);


        game.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {
        this.camera = new OrthographicCamera();
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

