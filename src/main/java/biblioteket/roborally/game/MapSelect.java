package biblioteket.roborally.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class MapSelect implements Screen {


    private final RoboRally game;
    OrthographicCamera camera;
    private Texture background;
    private Texture dizzyPre;
    private Texture dizzyPost;
    private Texture riskyPre;
    private Texture riskyPost;
    private BitmapFont font;
    private Texture selectMap;

    public MapSelect(final RoboRally game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, 640, 640);

        background = new Texture("assets/background2.jpg");
        dizzyPre = new Texture("assets/gamemaps/dizzyPre.png");
        dizzyPost = new Texture("assets/gamemaps/dizzyPost.png");
        riskyPre = new Texture("assets/gamemaps/riskyPre.png");
        riskyPost = new Texture("assets/gamemaps/riskyPost.png");
        font = new BitmapFont();
        selectMap = new Texture("assets/selectMap.png");
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

        camera.update();

        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0, 700, 700);
        //game.getBatch().draw(logo, 70, 340);
        game.getBatch().draw(dizzyPre, camera.viewportWidth / 7, camera.viewportHeight / 3, 200, 265);
        game.getBatch().draw(riskyPre, camera.viewportWidth - (camera.viewportWidth / 7) * 3, camera.viewportHeight / 3, 200, 265);
        game.getBatch().draw(selectMap, camera.viewportWidth/2 - selectMap.getWidth()/2, camera.viewportHeight/2);
        font.draw(game.getBatch(), "Dizzy Dash", camera.viewportWidth / 4 , camera.viewportHeight / (390/100) );
        font.draw(game.getBatch(), "Risky Exchange", camera.viewportWidth - (camera.viewportWidth / 3), camera.viewportHeight / (390/100) );


        if (Gdx.input.getX() < camera.viewportWidth / 7 + 200 && Gdx.input.getX() > camera.viewportWidth / 7 && camera.viewportHeight - Gdx.input.getY() < camera.viewportHeight / 3 + 265 && camera.viewportHeight - Gdx.input.getY() > camera.viewportHeight / 3 ) {
            game.getBatch().draw(dizzyPost, camera.viewportWidth / 7, camera.viewportHeight / 3, 200, 265);
            if (Gdx.input.isTouched()) {
                GameScreen.map = "assets/DizzyDash.tmx";
                game.setScreen(new GameScreen(game));
                dispose();

            }
        }
        else if (Gdx.input.getX() < camera.viewportWidth - (camera.viewportWidth / 7) * 3 + 200 && Gdx.input.getX() > camera.viewportWidth - (camera.viewportWidth / 7) * 3 && camera.viewportHeight - Gdx.input.getY() < camera.viewportHeight / 3 + 265 && camera.viewportHeight - Gdx.input.getY() > camera.viewportHeight / 3 ) {
            game.getBatch().draw(riskyPost, camera.viewportWidth - (camera.viewportWidth / 7) * 3, camera.viewportHeight / 3, 200, 265);
            if (Gdx.input.isTouched()) {
                GameScreen.map = "assets/risky_exchange.tmx";
                game.setScreen(new GameScreen(game));
                dispose();
            }
        }
        game.getBatch().end();
    }

    @Override
    public void resize(int i, int i1) {

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
