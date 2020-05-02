package biblioteket.roborally.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class DifficultySelect extends StandardScreen {

    private final Texture easyButtonPre;
    private final Texture easyButtonPost;
    private final Texture normalButtonPre;
    private final Texture normalButtonPost;
    private final Texture hardButtonPre;
    private final Texture hardButtonPost;
    private final OrthographicCamera camera;


    public DifficultySelect(final RoboRally game) {
        super(game);

        camera = getCamera();
        Assets assets = getAssets();

        easyButtonPre = assets.getManager().get(Assets.EASY_BUTTON_PRE, Texture.class);
        easyButtonPost = assets.getManager().get(Assets.EASY_BUTTON_POST, Texture.class);
        normalButtonPre = assets.getManager().get(Assets.NORMAL_BUTTON_PRE, Texture.class);
        normalButtonPost = assets.getManager().get(Assets.NORMAL_BUTTON_POST, Texture.class);
        hardButtonPre = assets.getManager().get(Assets.HARD_BUTTON_PRE, Texture.class);
        hardButtonPost = assets.getManager().get(Assets.HARD_BUTTON_POST, Texture.class);
    }

    @Override
    public void render(float v) {
        super.render(v);
        buttonsSizeAndScreenPlacement();

        float easyY = camera.viewportHeight / 4f;
        float normalY = camera.viewportHeight / 8f;
        float hardY = camera.viewportHeight / 130f;

        game.getBatch().begin();
        game.getBatch().draw(easyButtonPre, buttonCentered, camera.viewportHeight / 4f, buttonWidth, buttonHeight);
        game.getBatch().draw(normalButtonPre, buttonCentered, camera.viewportHeight / 8f, buttonWidth, buttonHeight);
        game.getBatch().draw(hardButtonPre, buttonCentered, camera.viewportHeight / 130f, buttonWidth, buttonHeight);

        if (Gdx.input.getX() < buttonCentered + buttonWidth && Gdx.input.getX() > buttonCentered && camera.viewportHeight - Gdx.input.getY() < easyY + buttonHeight / 1.35 && camera.viewportHeight - Gdx.input.getY() > easyY + buttonWidth / (1.35)) {
            game.getBatch().draw(easyButtonPost, buttonCentered, easyY, buttonWidth, buttonHeight);
            if (Gdx.input.isTouched()) {
                game.setScreen(new GameScreen(game));
                dispose();
            }
        } else if (Gdx.input.getX() < buttonCentered + buttonWidth && Gdx.input.getX() > buttonCentered && camera.viewportHeight - Gdx.input.getY() < normalY + buttonHeight / 1.35 && camera.viewportHeight - Gdx.input.getY() > normalY + buttonWidth / (1.35)) {
            game.getBatch().draw(normalButtonPost, buttonCentered, normalY, buttonWidth, buttonHeight);
            if (Gdx.input.isTouched()) {
                game.setScreen(new GameScreen(game));
            }
        } else if (Gdx.input.getX() < buttonCentered + buttonWidth && Gdx.input.getX() > buttonCentered && camera.viewportHeight - Gdx.input.getY() < hardY + buttonHeight / 1.35 && camera.viewportHeight - Gdx.input.getY() > hardY + buttonWidth / (1.35)) {
            game.getBatch().draw(hardButtonPost, buttonCentered, hardY, buttonWidth, buttonHeight);
            if (Gdx.input.isTouched()) {
                game.setScreen(new EndGameScreen(game));
            }
        }
        game.getBatch().end();
    }


}
