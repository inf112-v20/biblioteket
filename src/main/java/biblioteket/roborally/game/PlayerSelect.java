package biblioteket.roborally.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

import java.util.Arrays;
import java.util.List;

public class PlayerSelect extends StandardScreen {

    private final Texture arrowRightPre;
    private final Texture arrowRightPost;
    private final Texture arrowLeftPre;
    private final Texture arrowLeftPost;
    private final Texture selectNumberOfPlayers;
    private final Texture selectPre;
    private final Texture selectPost;
    private final OrthographicCamera camera;
    private final List<Texture> numberList;
    private int counter;


    public PlayerSelect(final RoboRally game) {
        super(game);

        camera = getCamera();
        Assets assets = getAssets();

        arrowRightPre = assets.getManager().get(Assets.ARROW_RIGHT_PRE, Texture.class);
        arrowRightPost = assets.getManager().get(Assets.ARROW_RIGHT_POST, Texture.class);
        arrowLeftPre = assets.getManager().get(Assets.ARROW_LEFT_PRE, Texture.class);
        arrowLeftPost = assets.getManager().get(Assets.ARROW_LEFT_POST, Texture.class);
        selectPre = assets.getManager().get(Assets.SELECT_PRE, Texture.class);
        selectPost = assets.getManager().get(Assets.SELECT_POST, Texture.class);
        selectNumberOfPlayers = assets.getManager().get(Assets.SELECT_NUMBER_OF_PLAYERS, Texture.class);

        Texture one = new Texture("assets/numbers/1.png");
        Texture two = new Texture("assets/numbers/2.png");
        Texture three = new Texture("assets/numbers/3.png");
        Texture four = new Texture("assets/numbers/4.png");
        Texture five = new Texture("assets/numbers/5.png");
        Texture six = new Texture("assets/numbers/6.png");
        Texture seven = new Texture("assets/numbers/7.png");
        Texture eight = new Texture("assets/numbers/8.png");
        numberList = Arrays.asList(one, two, three, four, five, six, seven, eight);
    }

    public Texture convertIntToTexture(int counter) {
        return numberList.get(counter);
    }

    @Override
    public void render(float v) {
        buttonsSizeAndScreenPlacement();

        float selectY = camera.viewportHeight / 15f;
        float numberWidth = camera.viewportHeight / (300 / 100f);
        float numberCenter = camera.viewportWidth / 2f - numberWidth / 2f;
        float numberHeight = camera.viewportHeight / (300 / 100f);
        float arrowY = camera.viewportHeight / 2.5f;
        float arrowWidth = camera.viewportHeight / (8f);
        float arrowHeight = camera.viewportHeight / (4.5f);
        float arrowLeftX = centerOfScreenX - arrowWidth * 2f;
        float arrowRightX = centerOfScreenX + arrowWidth;

        super.render(v);
        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);
        game.getBatch().draw(selectNumberOfPlayers, centerOfScreenX - arrowWidth * 3.9f, camera.viewportHeight / 2.5f, selectNumberOfPlayers.getWidth() * camera.viewportHeight / 640f, selectNumberOfPlayers.getHeight() * camera.viewportHeight / 640f);
        game.getBatch().draw(arrowLeftPre, arrowLeftX, arrowY, arrowWidth, arrowHeight);
        game.getBatch().draw(arrowRightPre, arrowRightX, arrowY, arrowWidth, arrowHeight);
        game.getBatch().draw(selectPre, centerOfScreenX - buttonWidth / 2, selectY, buttonWidth, buttonHeight);
        game.getBatch().draw(convertIntToTexture(counter), numberCenter, arrowY * 0.87f, numberWidth, numberHeight);

        //arrow left
        if (Gdx.input.getX() < arrowLeftX + arrowWidth && Gdx.input.getX() > arrowLeftX && camera.viewportHeight - Gdx.input.getY() < arrowY + arrowHeight && camera.viewportHeight - Gdx.input.getY() > arrowY) {
            game.getBatch().draw(arrowLeftPost, arrowLeftX, arrowY, arrowWidth, arrowHeight);
            if (Gdx.input.isButtonJustPressed(0) && counter != 0) {
                counter--;
            }
        }
        //arrow right
        else if (Gdx.input.getX() < arrowRightX + arrowWidth && Gdx.input.getX() > arrowRightX && camera.viewportHeight - Gdx.input.getY() < arrowY + arrowHeight && camera.viewportHeight - Gdx.input.getY() > arrowY) {
            game.getBatch().draw(arrowRightPost, arrowRightX, arrowY, arrowWidth, arrowHeight);
            if (Gdx.input.isButtonJustPressed(0) && counter != 7) {
                counter++;
            }
        }
        //select button
        else if (Gdx.input.getX() < buttonCentered + buttonWidth && Gdx.input.getX() > buttonCentered && camera.viewportHeight - Gdx.input.getY() < selectY + buttonHeight / 1.35 && camera.viewportHeight - Gdx.input.getY() > selectY + buttonWidth / (1.35)) {
            game.getBatch().draw(selectPost, buttonCentered, selectY, buttonWidth, buttonHeight);
            if (Gdx.input.isTouched()) {
                game.setPlayers(counter + 1);
                game.setScreen(new MapSelect(game));
                dispose();
            }
        }
        game.getBatch().end();
    }
}
