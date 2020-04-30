package biblioteket.roborally.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

/**
 * The landing screen once the user starts the game, this is where the player(s)
 * select how many players there are, multiplayer etc. Currently only a simple
 * canvas is displayed that the user can click and then the game launches.
 */
public class MainMenuScreen extends StandardScreen {
    private final RoboRally game;
    private final Texture playPre;
    private final Texture playPost;
    private final Texture quitPre;
    private final Texture quitPost;
    private final OrthographicCamera camera;

    public MainMenuScreen(final RoboRally game) {
        super(game);
        this.game = game;

        camera = getCamera();
        Assets assets = getAssets();

        playPre = assets.getManager().get(Assets.playPre, Texture.class);
        playPost = assets.getManager().get(Assets.playPost, Texture.class);
        quitPre = assets.getManager().get(Assets.quitPre, Texture.class);
        quitPost = assets.getManager().get(Assets.quitPost, Texture.class);

    }

    @Override
    public void show() {
        // Not used, but method must be overwritten
    }

    @Override
    public void render(float v) {
        super.render(v);
        buttonSizeAndLocation();

        float startY = camera.viewportHeight / 5f;

        game.getBatch().begin();
        game.getBatch().draw(playPre, buttonCentered, startY, buttonWidth, buttonHeight);
        game.getBatch().draw(quitPre, buttonCentered, exitY, buttonWidth, buttonHeight);

        if (Gdx.input.getX() < buttonCentered + buttonWidth && Gdx.input.getX() > buttonCentered && camera.viewportHeight - Gdx.input.getY() < startY + buttonHeight / 1.35f && camera.viewportHeight - Gdx.input.getY() > startY + buttonWidth / (1.5f)) {
            game.getBatch().draw(playPost, buttonCentered, startY, buttonWidth, buttonHeight);
            if (Gdx.input.isTouched()) {
                game.setScreen(new PlayerSelect(game));
                dispose();
            }
        } else if (hoverOverQuit()) {
            game.getBatch().draw(quitPost, buttonCentered, exitY, buttonWidth, buttonHeight);
            if (Gdx.input.isTouched()) {
                Gdx.app.exit();

            }
        }
        game.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
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
