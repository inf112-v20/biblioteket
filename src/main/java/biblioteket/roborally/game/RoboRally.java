package biblioteket.roborally.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * A small wrapper around both the main menu and the game screen, by extending Game
 * our game can now have multiple screens. We start the game in the main menu and
 * then we can move between various screens.
 */
public class RoboRally extends Game {
    private SpriteBatch batch;
    private BitmapFont font;

    @Override
    public void create() {
        // https://github.com/libgdx/libgdx/wiki/Continuous-%26-non-continuous-rendering
        // This should be a performance increase for our game
        Gdx.graphics.setContinuousRendering(false);
        Gdx.graphics.requestRendering();

        this.setBatch(new SpriteBatch());
        this.setFont(new BitmapFont());
        this.setScreen(new MainMenuScreen(this));
    }

    @Override
    public void dispose() {
        getBatch().dispose();
        getFont().dispose();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public BitmapFont getFont() {
        return font;
    }

    public void setFont(BitmapFont font) {
        this.font = font;
    }
}
