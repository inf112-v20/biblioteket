package biblioteket.roborally.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.concurrent.ThreadLocalRandom;

/**
 * A small wrapper around both the main menu and the game screen, by extending Game
 * our game can now have multiple screens. We start the game in the main menu and
 * then we can move between various screens.
 */
public class RoboRally extends Game {
    private SpriteBatch batch;
    private BitmapFont font;
    private int numPlayers;

    @Override
    public void create() {
        this.setBatch(new SpriteBatch());
        this.setFont(new BitmapFont());
        this.setScreen(new MainMenuScreen(this));
    }

    @Override
    public void dispose() {
        getBatch().dispose();
        getFont().dispose();
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

    public int getPlayers() {
        return numPlayers;
    }

    public void setPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public int getAI() {
        return ThreadLocalRandom.current().nextInt(1, 9 - getPlayers());
    }
}
