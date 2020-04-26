package biblioteket.roborally.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
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
    private final Texture quitPre;
    private final Texture quitPost;
    private final BitmapFont font;
    private final Texture selectMap;
    private float mapWidth;
    private float mapHeight;
    private float center;
    private float dizzyX;
    private float riskyX;
    private float mapY;
    private float buttonHeight;
    private float buttonWidth;
    private float exitY;
    private float buttonCenter;
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
        quitPre = new Texture("assets/buttons/quitPost.png");
        quitPost = new Texture("assets/buttons/quitPre.png");
        font = new BitmapFont();
        font.getData().setScale(1.5f,1.5f);

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
        center = camera.viewportWidth / 2f;
        buttonCenter = center - buttonWidth / 2f;
        dizzyX = center - camera.viewportWidth / 9f - mapWidth;
        riskyX = center + camera.viewportWidth / 9f;
        mapY = camera.viewportHeight / 3f;
        exitY = camera.viewportHeight / 30f;
        buttonHeight = camera.viewportHeight / ((256f / 100f));
        buttonWidth = camera.viewportHeight / (356f / 100f);
    }

    @Override
    public void render(float v) {
        buttonSize();
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);
        game.getBatch().draw(dizzyPre, dizzyX, mapY, mapWidth, mapHeight);
        game.getBatch().draw(riskyPre, riskyX, mapY, mapWidth, mapHeight);
        game.getBatch().draw(quitPre, buttonCenter, exitY, buttonWidth, buttonHeight);

        game.getBatch().draw(selectMap, camera.viewportWidth / 2f - (float)selectMap.getWidth() / 2f * camera.viewportHeight / 640f, camera.viewportHeight / 2f, selectMap.getWidth() * camera.viewportHeight / 640f, selectMap.getHeight() * camera.viewportHeight / 640f);
        font.draw(game.getBatch(), "Dizzy Dash", dizzyX +  mapWidth/3.8f, camera.viewportHeight / (310f / 100f));
        font.draw(game.getBatch(), "Risky Exchange", riskyX + mapWidth/5.5f, camera.viewportHeight / (310f / 100f));



        if (Gdx.input.getX() < dizzyX + mapWidth && Gdx.input.getX() > dizzyX && camera.viewportHeight - Gdx.input.getY() < mapY + mapHeight && camera.viewportHeight - Gdx.input.getY() > mapY) {
            game.getBatch().draw(dizzyPost, dizzyX, mapY, mapWidth, mapHeight);
            if (Gdx.input.isTouched()) {
                map = "assets/DizzyDash.tmx";
                game.setScreen(new DifficultySelect(game));
                dispose();

            }
        } else if (Gdx.input.getX() < riskyX + mapWidth && Gdx.input.getX() > riskyX && camera.viewportHeight - Gdx.input.getY() < mapY + mapHeight && camera.viewportHeight - Gdx.input.getY() > mapY) {
            game.getBatch().draw(riskyPost, riskyX, mapY, mapWidth, mapHeight);
            if (Gdx.input.isTouched()) {
                map = "assets/RiskyExchange.tmx";
                game.setScreen(new DifficultySelect(game));
                dispose();
            }
        }
        else if (Gdx.input.getX() < buttonCenter + buttonWidth && Gdx.input.getX() > buttonCenter && camera.viewportHeight - Gdx.input.getY() < exitY + buttonHeight / 1.35f && camera.viewportHeight - Gdx.input.getY() > exitY + buttonWidth / (1.5f)) {
            game.getBatch().draw(quitPost, buttonCenter, exitY, buttonWidth, buttonHeight);
            if (Gdx.input.isTouched()) {
                Gdx.app.exit();
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

