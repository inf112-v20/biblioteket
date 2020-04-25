package biblioteket.roborally.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class MapSelect implements Screen {


    private static String map;
    private final RoboRally game;
    private final Texture background;
    private final Texture dizzyPre;
    private final Texture dizzyPost;
    private final Texture riskyPre;
    private final Texture riskyPost;
    private final BitmapFont font;
    private final Texture selectMap;
    private float mapWidth;
    private float mapHeight;
    private float center;
    private float dizzyX;
    private float riskyX;
    private float mapY;
    private OrthographicCamera camera;

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

    public static String getMap() {
        return map;
    }

    @Override
    public void show() {
        //empty method
    }

    public void buttonSize() {
        mapWidth = camera.viewportHeight / (3.2f);
        mapHeight = camera.viewportHeight / (2.42f);
        center = camera.viewportWidth / 2;
        dizzyX = center - camera.viewportWidth / 9 - mapWidth;
        riskyX = center + camera.viewportWidth / 9;
        mapY = camera.viewportHeight / 3;
    }

    @Override
    public void render(float v) {
        buttonSize();
        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);
        game.getBatch().draw(dizzyPre, dizzyX, mapY, mapWidth, mapHeight);
        game.getBatch().draw(riskyPre, riskyX, mapY, mapWidth, mapHeight);

        game.getBatch().draw(selectMap, camera.viewportWidth / 2 - (float)selectMap.getWidth() / 2 * camera.viewportHeight / 640, camera.viewportHeight / 2, selectMap.getWidth() * camera.viewportHeight / 640, selectMap.getHeight() * camera.viewportHeight / 640);
        font.draw(game.getBatch(), "Dizzy Dash", camera.viewportWidth / 7 + 70, camera.viewportHeight / (390f / 100f));
        font.draw(game.getBatch(), "Risky Exchange", camera.viewportWidth / 2 + 100, camera.viewportHeight / (390f / 100f));



        if (Gdx.input.getX() < dizzyX + mapWidth && Gdx.input.getX() > dizzyX && camera.viewportHeight - Gdx.input.getY() < mapY + mapHeight && camera.viewportHeight - Gdx.input.getY() > mapY) {
            game.getBatch().draw(dizzyPost, dizzyX, mapY, mapWidth, mapHeight);
            if (Gdx.input.isTouched()) {
                map = "assets/DizzyDash.tmx";
                game.setScreen(new GameScreen());
                dispose();

            }
        } else if (Gdx.input.getX() < riskyX + mapWidth && Gdx.input.getX() > riskyX && camera.viewportHeight - Gdx.input.getY() < mapY + mapHeight && camera.viewportHeight - Gdx.input.getY() > mapY) {
            game.getBatch().draw(riskyPost, riskyX, mapY, mapWidth, mapHeight);
            if (Gdx.input.isTouched()) {
                map = "assets/RiskyExchange.tmx";
                game.setScreen(new GameScreen());
                dispose();
            }
        }
        game.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
        camera.update();
    }

    @Override
    public void pause() {
        //empty method
    }

    @Override
    public void resume() {
        //empty method
    }

    @Override
    public void hide() {
        //empty method
    }

    @Override
    public void dispose() {
        //empty method
    }
}

