package biblioteket.roborally.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class DifficultySelect implements Screen {

    private final RoboRally game;
    private final Texture background;
    private final Texture logo;
    private final Texture easyButtonPre;
    private final Texture easyButtonPost;
    private final Texture normalButtonPre;
    private final Texture normalButtonPost;
    private final Texture hardButtonPre;
    private final Texture hardButtonPost;
    private float buttonWidth;
    private float buttonHeight;
    private float center;
    private float easyY;
    private float normalY;
    private float hardY;
    private OrthographicCamera camera;
    private final Assets assets;


    public DifficultySelect(final RoboRally game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, 640, 640);

        assets = new Assets();
        assets.load();
        assets.getManager().finishLoading();

        background = assets.getManager().get(assets.background, Texture.class);
        logo = assets.getManager().get(assets.logo, Texture.class);
        easyButtonPre = assets.getManager().get(assets.easyButtonPre, Texture.class);
        easyButtonPost = assets.getManager().get(assets.easyButtonPost, Texture.class);
        normalButtonPre = assets.getManager().get(assets.normalButtonPre, Texture.class);
        normalButtonPost = assets.getManager().get(assets.normalButtonPost, Texture.class);
        hardButtonPre = assets.getManager().get(assets.hardButtonPre, Texture.class);
        hardButtonPost = assets.getManager().get(assets.hardButtonPost, Texture.class);
    }
    @Override
    public void show() {
        //empty method
    }

    public void buttonSize() {
        center = camera.viewportWidth / 2f - buttonWidth / 2f;
        buttonHeight = camera.viewportHeight / ((256f / 100f));
        buttonWidth = camera.viewportHeight / (356f / 100f);
        easyY = camera.viewportHeight / 4f;
        normalY = camera.viewportHeight / 8f;
        hardY = camera.viewportHeight / 130f;

    }

    @Override
    public void render(float v) {
        buttonSize();
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);
        game.getBatch().draw(logo, camera.viewportWidth / 2 - (float) logo.getWidth() / 2 * camera.viewportHeight / 640f, camera.viewportHeight / 2, logo.getWidth() * camera.viewportHeight / 640f, logo.getHeight() * camera.viewportHeight / 640f);
        game.getBatch().draw(easyButtonPre, center, camera.viewportHeight / 4f, buttonWidth, buttonHeight);
        game.getBatch().draw(normalButtonPre, center, camera.viewportHeight / 8f, buttonWidth, buttonHeight);
        game.getBatch().draw(hardButtonPre, center, camera.viewportHeight / 130f, buttonWidth, buttonHeight);

        if (Gdx.input.getX() < center + buttonWidth && Gdx.input.getX() > center && camera.viewportHeight - Gdx.input.getY() < easyY + buttonHeight / 1.35 && camera.viewportHeight - Gdx.input.getY() > easyY + buttonWidth / (1.35)) {
            game.getBatch().draw(easyButtonPost, center, easyY, buttonWidth, buttonHeight);
            if (Gdx.input.isTouched()) {
                game.setScreen(new GameScreen());
                dispose();
            }
        } else if (Gdx.input.getX() < center + buttonWidth && Gdx.input.getX() > center && camera.viewportHeight - Gdx.input.getY() < normalY + buttonHeight / 1.35 && camera.viewportHeight - Gdx.input.getY() > normalY + buttonWidth / (1.35)) {
            game.getBatch().draw(normalButtonPost, center, normalY, buttonWidth, buttonHeight);
            if (Gdx.input.isTouched()) {
                game.setScreen(new GameScreen());
            }
        } else if (Gdx.input.getX() < center + buttonWidth && Gdx.input.getX() > center && camera.viewportHeight - Gdx.input.getY() < hardY + buttonHeight / 1.35 && camera.viewportHeight - Gdx.input.getY() > hardY + buttonWidth / (1.35)) {
            game.getBatch().draw(hardButtonPost, center, hardY, buttonWidth, buttonHeight);
            if (Gdx.input.isTouched()) {
                game.setScreen(new GameScreen());
            }
        }
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
        //empty method
    }

    @Override
    public void resume() {
        //empty method
    }

    @Override
    public void hide() {
        //empty method
    }

    @Override
    public void dispose() {
        assets.getManager().dispose();
    }
}
