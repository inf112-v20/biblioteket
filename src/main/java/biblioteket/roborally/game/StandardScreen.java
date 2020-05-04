package biblioteket.roborally.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

import java.util.Arrays;
import java.util.List;

public class StandardScreen implements Screen {

    private static final OrthographicCamera camera = new OrthographicCamera();
    public final RoboRally game;
    public final Texture background;
    public final Texture logo;
    public final Texture arrowLeftPost;
    public final Texture arrowRightPost;
    public final Texture arrowRightPre;
    public final Texture arrowLeftPre;
    public final Texture selectPre;
    public final Texture selectPost;
    private final Assets assets = new Assets();
    public float buttonCentered;
    public float buttonWidth;
    public float buttonHeight;
    public float exitY;
    public float centerOfScreenX;
    public float arrowY;
    public float arrowWidth;
    public float arrowHeight;
    public float arrowLeftX;
    public float arrowRightX;
    public float selectY;
    public float numberWidth;
    public float numberCenter;
    public float numberHeight;
    public int counter;
    private final List<Texture> numberList;


    public StandardScreen(RoboRally game) {
        this.game = game;

        camera.setToOrtho(false, 640, 640);

        assets.load();
        assets.getManager().finishLoading();
        background = assets.getManager().get(Assets.BACKGROUND, Texture.class);
        logo = assets.getManager().get(Assets.LOGO, Texture.class);
        arrowLeftPost = assets.getManager().get(Assets.ARROW_LEFT_POST, Texture.class);
        arrowRightPost = assets.getManager().get(Assets.ARROW_RIGHT_POST, Texture.class);
        arrowLeftPre = assets.getManager().get(Assets.ARROW_LEFT_PRE, Texture.class);
        arrowRightPre = assets.getManager().get(Assets.ARROW_RIGHT_PRE, Texture.class);
        selectPre = assets.getManager().get(Assets.SELECT_PRE, Texture.class);
        selectPost = assets.getManager().get(Assets.SELECT_POST, Texture.class);
        Texture one = assets.getManager().get(Assets.ONE, Texture.class);
        Texture two = assets.getManager().get(Assets.TWO, Texture.class);
        Texture three = assets.getManager().get(Assets.THREE, Texture.class);
        Texture four = assets.getManager().get(Assets.FOUR, Texture.class);
        Texture five = assets.getManager().get(Assets.FIVE, Texture.class);
        Texture six = assets.getManager().get(Assets.SIX, Texture.class);
        Texture seven = assets.getManager().get(Assets.SEVEN, Texture.class);
        Texture eight = assets.getManager().get(Assets.EIGHT, Texture.class);
        numberList = Arrays.asList(one, two, three, four, five, six, seven, eight);
    }

    public static OrthographicCamera getCamera() {
        return camera;
    }

    //Resizing does not work if made in constructor.
    public void buttonsSizeAndScreenPlacement() {
        centerOfScreenX = camera.viewportWidth / 2f;
        buttonWidth = camera.viewportHeight / (356f / 100f);
        buttonCentered = camera.viewportWidth / 2f - buttonWidth / 2f;
        buttonHeight = camera.viewportHeight / (256f / 100f);
        exitY = camera.viewportHeight / 20f;
        arrowY = camera.viewportHeight / 2.5f;
        arrowWidth = camera.viewportHeight / (8f);
        arrowHeight = camera.viewportHeight / (4.5f);
        arrowLeftX = centerOfScreenX - arrowWidth * 2f;
        arrowRightX = centerOfScreenX + arrowWidth;
        selectY = camera.viewportHeight / 15f;
        numberWidth = camera.viewportHeight / (300 / 100f);
        numberCenter = camera.viewportWidth / 2f - numberWidth / 2f;
        numberHeight = camera.viewportHeight / (300 / 100f);
    }

    public boolean quitButtonTouched() {
        return Gdx.input.getX() < buttonCentered + buttonWidth && Gdx.input.getX() > buttonCentered && camera.viewportHeight - Gdx.input.getY() < exitY + buttonHeight / 1.35f && camera.viewportHeight - Gdx.input.getY() > exitY + buttonWidth / (1.5f);
    }

    public boolean arrowTouched(float x) {
        return Gdx.input.getX() < x + arrowWidth && Gdx.input.getX() > x && camera.viewportHeight - Gdx.input.getY() < arrowY + arrowHeight && camera.viewportHeight - Gdx.input.getY() > arrowY;
    }

    public void drawArrow(Texture arrow, float x) {
        game.getBatch().draw(arrow, x, arrowY, arrowWidth, arrowHeight);
    }

    public Texture convertIntToTexture(int counter) {
        return numberList.get(counter);
    }

    public void drawArrowScreen() {
        game.getBatch().draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);
        drawArrow(arrowLeftPre, arrowLeftX);
        drawArrow(arrowRightPre, arrowRightX);
        game.getBatch().draw(selectPre, centerOfScreenX - buttonWidth / 2, selectY, buttonWidth, buttonHeight);
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
    }

    public Assets getAssets() {
        return assets;
    }

    @Override
    public void show() {
        // Not used, but method must be overwritten
    }

    @Override
    public void render(float v) {

        Gdx.gl.glClearColor(v, v, v, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.getBatch().begin();
        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);
        game.getBatch().draw(logo, camera.viewportWidth / 2f - (float) logo.getWidth() / 2f * camera.viewportHeight / 640f, camera.viewportHeight / 2f, logo.getWidth() * camera.viewportHeight / 640f, logo.getHeight() * camera.viewportHeight / 640f);
        game.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        game.getBatch().setProjectionMatrix(camera.combined);
        camera.update();
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
        assets.dispose();
    }
}
