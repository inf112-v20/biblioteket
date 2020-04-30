package biblioteket.roborally.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class EndGameScreen extends StandardScreen {

    private final RoboRally game;
    private final OrthographicCamera camera;

    private final Texture quitPre;
    private final Texture quitPost;
    private final Texture mainMenuPre;
    private final Texture mainMenuPost;
    private final BitmapFont font;


    public EndGameScreen(final RoboRally game) {
        super(game);
        this.game = game;
        camera = getCamera();
        Assets assets = getAssets();

        font = new BitmapFont();
        font.getData().setScale(1.5f, 1.5f);

        quitPre = assets.getManager().get(Assets.quitPre, Texture.class);
        quitPost = assets.getManager().get(Assets.quitPost, Texture.class);
        mainMenuPre = assets.getManager().get(Assets.mainMenuPre, Texture.class);
        mainMenuPost = assets.getManager().get(Assets.mainMenuPost, Texture.class);

    }

    @Override
    public void show() {
        // Not used, but method must be overwritten
    }

    @Override
    public void render(float v) {
        super.render(v);
        buttonSizeAndLocation();

        float mainMenuY = camera.viewportHeight / (6f);

        game.getBatch().begin();
        game.getBatch().draw(mainMenuPre, buttonCentered, mainMenuY, buttonWidth, buttonHeight);
        game.getBatch().draw(quitPre, buttonCentered, exitY, buttonWidth, buttonHeight);

        if (Gdx.input.getX() < buttonCentered + buttonWidth && Gdx.input.getX() > buttonCentered && camera.viewportHeight - Gdx.input.getY() < mainMenuY + buttonHeight / 1.35f && camera.viewportHeight - Gdx.input.getY() > mainMenuY + buttonWidth / (1.5f)) {
            game.getBatch().draw(mainMenuPost, buttonCentered, mainMenuY, buttonWidth, buttonHeight);
            if (Gdx.input.isTouched()) {
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
        } else if (hoverOverQuit()) {
            game.getBatch().draw(quitPost, buttonCentered, exitY, buttonWidth, buttonHeight);
            if (Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
        }
        font.draw(game.getBatch(), "Player name here" + " has won the game!", camera.viewportWidth / 2f - (float) logo.getWidth() / 3.3f  * camera.viewportHeight / 640f, camera.viewportHeight / 2f);
        game.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width,height);
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
        super.dispose();
    }
}
