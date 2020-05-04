package biblioteket.roborally.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class AISelect extends StandardScreen {

    private final Texture selectNumberOfAI;
    private final OrthographicCamera camera;

    public AISelect(RoboRally game) {
        super(game);
        camera = getCamera();

        Assets assets = getAssets();
        selectNumberOfAI = assets.getManager().get(Assets.SELECT_NUMBER_OF_AI, Texture.class);

    }
    @Override
    public void render(float v) {
        buttonsSizeAndScreenPlacement();
        game.getBatch().begin();
        drawArrowScreen();
        game.getBatch().draw(convertIntToTexture(counter), numberCenter, arrowY * 0.87f, numberWidth, numberHeight);
        game.getBatch().draw(selectNumberOfAI, centerOfScreenX - arrowWidth * 3.3f, camera.viewportHeight / 2.2f, selectNumberOfAI.getWidth() * camera.viewportHeight / 540f, selectNumberOfAI.getHeight() * camera.viewportHeight / 400f);
        //select button
        if (Gdx.input.getX() < buttonCentered + buttonWidth && Gdx.input.getX() > buttonCentered && camera.viewportHeight - Gdx.input.getY() < selectY + buttonHeight / 1.35 && camera.viewportHeight - Gdx.input.getY() > selectY + buttonWidth / (1.35)) {
            game.getBatch().draw(selectPost, buttonCentered, selectY, buttonWidth, buttonHeight);
            if (Gdx.input.isTouched()) {
                game.setScreen(new MapSelect(game));
                dispose();
            }
        }
        game.getBatch().end();

    }
}


