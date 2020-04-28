package biblioteket.roborally.game;

import biblioteket.roborally.actors.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

import java.util.Arrays;
import java.util.List;

public class PlayerSelect implements Screen {

    private float center;
    private float arrowY;
    private float arrowWidth;
    private float arrowHeight;
    private float numberCenter;
    private float numberWidth;
    private float numberHeight;
    private float arrowLeftX;
    private float arrowRightX;
    private float buttonHeight;
    private float buttonWidth;
    private float selectY;
    private float buttonCenter;
    private int counter;
    private final Texture background;
    private final Texture logo;
    private final Texture arrowRightPre;
    private final Texture arrowRightPost;
    private final Texture arrowLeftPre;
    private final Texture arrowLeftPost;
    private final Texture selectNumberOfPlayers;
    private final Texture one;
    private final Texture two;
    private final Texture three;
    private final Texture four;
    private final Texture five;
    private final Texture six;
    private final Texture seven;
    private final Texture eight;
    private final Texture selectPre;
    private final Texture selectPost;
    private final RoboRally game;
    private final OrthographicCamera camera;
    List<Texture> numberList;

    public PlayerSelect(final RoboRally game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, 640, 640);

        background = new Texture("assets/background2.jpg");
        logo = new Texture("assets/logo.png");
        arrowRightPre = new Texture("assets/buttons/arrowRightPre.png");
        arrowRightPost = new Texture("assets/buttons/arrowRightPost.png");
        arrowLeftPre = new Texture("assets/buttons/arrowLeftPre.png");
        arrowLeftPost = new Texture("assets/buttons/arrowLeftPost.png");
        selectPre = new Texture("assets/buttons/selectPre.png");
        selectPost = new Texture("assets/buttons/selectPost.png");
        selectNumberOfPlayers = new Texture("assets/selectPlayers.png");

        one = new Texture("assets/numbers/1.png");
        two = new Texture("assets/numbers/2.png");
        three = new Texture("assets/numbers/3.png");
        four = new Texture("assets/numbers/4.png");
        five = new Texture("assets/numbers/5.png");
        six = new Texture("assets/numbers/6.png");
        seven = new Texture("assets/numbers/7.png");
        eight = new Texture("assets/numbers/8.png");

        numberList = Arrays.asList(one,two,three,four,five,six,seven,eight);

    }

    @Override
    public void show() {

    }
    public void buttonSize() {
        center = camera.viewportWidth / 2f;
        buttonCenter = camera.viewportWidth / 2f - buttonWidth/2;
        buttonHeight = camera.viewportHeight / ((256f / 100f));
        buttonWidth = camera.viewportHeight / (356f / 100f);
        selectY = camera.viewportHeight/15f;
        numberCenter = camera.viewportWidth/2f - numberWidth / 2f;
        numberWidth = camera.viewportHeight/(300/100f);
        numberHeight = camera.viewportHeight/(300/100f);
        arrowY = camera.viewportHeight/2.5f;
        arrowLeftX = center - arrowWidth*2f;
        arrowRightX = center + arrowWidth;
        arrowWidth = camera.viewportHeight/(8f);
        arrowHeight = camera.viewportHeight/(4.5f);
    }

    public Texture convertIntToTexture(int counter) {
        return numberList.get(counter);
    }

    @Override
    public void render(float v) {
        buttonSize();

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);
        game.getBatch().draw(selectNumberOfPlayers,center - arrowWidth*3.9f,camera.viewportHeight / 2.5f, selectNumberOfPlayers.getWidth()*camera.viewportHeight/640f,selectNumberOfPlayers.getHeight()*camera.viewportHeight/640f);
        game.getBatch().draw(arrowLeftPre, arrowLeftX, arrowY, arrowWidth, arrowHeight);
        game.getBatch().draw(arrowRightPre,arrowRightX, arrowY, arrowWidth, arrowHeight);
        game.getBatch().draw(selectPre, center - buttonWidth/2,selectY,buttonWidth,buttonHeight);
        game.getBatch().draw(convertIntToTexture(counter), numberCenter, arrowY*0.87f, numberWidth,numberHeight);


        if (Gdx.input.getX() < arrowLeftX + arrowWidth && Gdx.input.getX() > arrowLeftX && camera.viewportHeight - Gdx.input.getY() < arrowY + arrowHeight && camera.viewportHeight - Gdx.input.getY() > arrowY) {
            game.getBatch().draw(arrowLeftPost, arrowLeftX, arrowY, arrowWidth, arrowHeight);
            if (Gdx.input.isButtonJustPressed(0)) {
                if(counter != 0) {
                    counter--;
                }
                dispose();
            }
        }

        else if (Gdx.input.getX() < arrowRightX + arrowWidth && Gdx.input.getX() > arrowRightX && camera.viewportHeight - Gdx.input.getY() < arrowY + arrowHeight && camera.viewportHeight - Gdx.input.getY() > arrowY) {
            game.getBatch().draw(arrowRightPost, arrowRightX, arrowY, arrowWidth, arrowHeight);
            if (Gdx.input.isButtonJustPressed(0)) {
                if(counter != 7) {
                    counter++;
                }
            }
        }
        else if (Gdx.input.getX() < buttonCenter + buttonWidth && Gdx.input.getX() > buttonCenter && camera.viewportHeight - Gdx.input.getY() < selectY + buttonHeight / 1.35 && camera.viewportHeight - Gdx.input.getY() > selectY + buttonWidth / (1.35)) {
        game.getBatch().draw(selectPost, buttonCenter, selectY, buttonWidth, buttonHeight);
        if (Gdx.input.isTouched()) {
            game.setScreen(new MapSelect(game));
            dispose();
        }
        }
        game.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
