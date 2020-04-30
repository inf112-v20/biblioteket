package biblioteket.roborally.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class MapSelect extends StandardScreen {

    private static String map;
    private final RoboRally game;
    private final Texture dizzyPre;
    private final Texture dizzyPost;
    private final Texture riskyPre;
    private final Texture riskyPost;
    private final Texture quitPre;
    private final Texture quitPost;
    private final Texture selectMap;
    private final BitmapFont font;
    private final OrthographicCamera camera;

    public MapSelect(final RoboRally game) {
        super(game);
        this.game = game;

        camera = getCamera();
        Assets assets = getAssets();

        dizzyPre = assets.getManager().get(Assets.dizzyPre, Texture.class);
        dizzyPost = assets.getManager().get(Assets.dizzyPost, Texture.class);
        riskyPre = assets.getManager().get(Assets.riskyPre, Texture.class);
        riskyPost = assets.getManager().get(Assets.riskyPost, Texture.class);
        quitPre = assets.getManager().get(Assets.quitPre, Texture.class);
        quitPost = assets.getManager().get(Assets.quitPost, Texture.class);
        selectMap = assets.getManager().get(Assets.selectMap, Texture.class);

        font = new BitmapFont();
        font.getData().setScale(1.5f, 1.5f);

    }

    public static String getMap() {
        return map;
    }

    @Override
    public void show() {
        //empty method
    }

    @Override
    public void render(float v) {
        super.render(v);
        buttonSizeAndLocation();

        float mapWidth = camera.viewportHeight / (3.2f);
        float mapHeight = camera.viewportHeight / (2.42f);
        float mapCentered = camera.viewportWidth / 2f - mapWidth / 2f;
        float dizzyX = mapCentered - mapWidth;
        float riskyX = mapCentered + mapWidth;
        float mapY = camera.viewportHeight / 3f;

        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);
        game.getBatch().draw(dizzyPre, dizzyX, mapY, mapWidth, mapHeight);
        game.getBatch().draw(riskyPre, riskyX, mapY, mapWidth, mapHeight);
        game.getBatch().draw(quitPre, buttonCentered, exitY, buttonWidth, buttonHeight);

        game.getBatch().draw(selectMap, camera.viewportWidth / 2f - (float) selectMap.getWidth() / 2f * camera.viewportHeight / 640f, camera.viewportHeight / 2f, selectMap.getWidth() * camera.viewportHeight / 640f, selectMap.getHeight() * camera.viewportHeight / 640f);
        font.draw(game.getBatch(), "Dizzy Dash", dizzyX + mapWidth / 3.5f, camera.viewportHeight / (310f / 100f));
        font.draw(game.getBatch(), "Risky Exchange", riskyX + mapWidth / 5.1f, camera.viewportHeight / (310f / 100f));


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
        } else if (Gdx.input.getX() < buttonCentered + buttonWidth && Gdx.input.getX() > buttonCentered && camera.viewportHeight - Gdx.input.getY() < exitY + buttonHeight / 1.35f && camera.viewportHeight - Gdx.input.getY() > exitY + buttonWidth / (1.5f)) {
            game.getBatch().draw(quitPost, buttonCentered, exitY, buttonWidth, buttonHeight);
            if (Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
        }
        game.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width,height);
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
        super.dispose();
    }
}

