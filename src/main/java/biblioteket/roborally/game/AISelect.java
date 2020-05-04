package biblioteket.roborally.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class AISelect extends StandardScreen {

    private final Texture selectNumberOfAI;
    private final OrthographicCamera camera;
    private int counter;

    public AISelect(RoboRally game) {
        super(game);
        camera = getCamera();

        Assets assets = getAssets();
        selectNumberOfAI = assets.getManager().get(Assets.SELECT_NUMBER_OF_AI, Texture.class);

    }
    @Override
    public void render(float v) {
        buttonsSizeAndScreenPlacement();

        super.render(v);
        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);
        game.getBatch().draw(selectNumberOfAI, centerOfScreenX - arrowWidth * 3.3f, camera.viewportHeight / 2.2f, selectNumberOfAI.getWidth() * camera.viewportHeight / 540f, selectNumberOfAI.getHeight() * camera.viewportHeight / 400f);
        drawArrow(arrowLeftPre, arrowLeftX);
        drawArrow(arrowRightPre, arrowRightX);
        game.getBatch().draw(selectPre, centerOfScreenX - buttonWidth / 2, selectY, buttonWidth, buttonHeight);
        game.getBatch().draw(convertIntToTexture(counter), numberCenter, arrowY * 0.87f, numberWidth, numberHeight);

        //arrow left
        if (arrowTouched(arrowLeftX)) {
            drawArrow(arrowLeftPost, arrowLeftX);
            if (Gdx.input.isButtonJustPressed(0) && counter != 0) {
                counter--;
            }
        }
        //arrow right
        else if (arrowTouched(arrowRightX)) {
            drawArrow(arrowRightPost, arrowRightX);
            if (Gdx.input.isButtonJustPressed(0) && counter != 7) {
                counter++;
            }
        }
        //select button
        else if (Gdx.input.getX() < buttonCentered + buttonWidth && Gdx.input.getX() > buttonCentered && camera.viewportHeight - Gdx.input.getY() < selectY + buttonHeight / 1.35 && camera.viewportHeight - Gdx.input.getY() > selectY + buttonWidth / (1.35)) {
            game.getBatch().draw(selectPost, buttonCentered, selectY, buttonWidth, buttonHeight);
            if (Gdx.input.isTouched()) {
                game.setScreen(new MapSelect(game));
                dispose();
            }
        }
        game.getBatch().end();
    }
}


