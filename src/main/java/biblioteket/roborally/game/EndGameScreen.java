package biblioteket.roborally.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class EndGameScreen extends StandardScreen {

    private final OrthographicCamera camera;
    private final Texture quitPre;
    private final Texture quitPost;
    private final Texture mainMenuPre;
    private final Texture mainMenuPost;
    private final BitmapFont font;


    public EndGameScreen(final RoboRally game) {
        super(game);
        camera = getCamera();
        Assets assets = getAssets();

        font = new BitmapFont();
        font.getData().setScale(1.5f, 1.5f);

        quitPre = assets.getManager().get(Assets.QUIT_PRE, Texture.class);
        quitPost = assets.getManager().get(Assets.QUIT_POST, Texture.class);
        mainMenuPre = assets.getManager().get(Assets.MAIN_MENU_PRE, Texture.class);
        mainMenuPost = assets.getManager().get(Assets.MAIN_MENU_POST, Texture.class);

    }


    @Override
    public void render(float v) {
        super.render(v);
        buttonsSizeAndScreenPlacement();

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
        } else if (quitButtonTouched()) {
            game.getBatch().draw(quitPost, buttonCentered, exitY, buttonWidth, buttonHeight);
            if (Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
        }
        font.draw(game.getBatch(), "Player name here" + " has won the game!", camera.viewportWidth / 2f - (float) logo.getWidth() / 3.3f * camera.viewportHeight / 640f, camera.viewportHeight / 2f);
        game.getBatch().end();
    }


}
