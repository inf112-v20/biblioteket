package biblioteket.roborally.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;


public class PlayerSelect extends StandardScreen {


    private final Texture selectNumberOfPlayers;
    private final OrthographicCamera camera;


    public PlayerSelect(final RoboRally game) {
        super(game);

        camera = getCamera();
        Assets assets = getAssets();

        selectNumberOfPlayers = assets.getManager().get(Assets.SELECT_NUMBER_OF_PLAYERS, Texture.class);

    }

    @Override
    public void render(float v) {
        buttonsSizeAndScreenPlacement();
        game.getBatch().begin();
        drawArrowScreen();
        game.getBatch().draw(convertIntToTexture(counter), numberCenter, arrowY * 0.87f, numberWidth, numberHeight);
        game.getBatch().draw(selectNumberOfPlayers, centerOfScreenX - arrowWidth * 3.9f, camera.viewportHeight / 2.5f, selectNumberOfPlayers.getWidth() * camera.viewportHeight / 640f, selectNumberOfPlayers.getHeight() * camera.viewportHeight / 640f);
        //select button
        if (Gdx.input.getX() < buttonCentered + buttonWidth && Gdx.input.getX() > buttonCentered && camera.viewportHeight - Gdx.input.getY() < selectY + buttonHeight / 1.35 && camera.viewportHeight - Gdx.input.getY() > selectY + buttonWidth / (1.35)) {
            game.getBatch().draw(selectPost, buttonCentered, selectY, buttonWidth, buttonHeight);
            if (Gdx.input.isTouched()) {
                game.setScreen(new AISelect(game));
                dispose();
            }
        }
        game.getBatch().end();
    }
}
