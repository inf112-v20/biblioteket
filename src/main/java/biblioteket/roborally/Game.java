package biblioteket.roborally;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Game implements ApplicationListener {
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private MapProperties properties;

    @Override
    public void create() {
        map = new TmxMapLoader().load("assets/board.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        properties = map.getProperties();

        int tileWidth = properties.get("tilewidth", Integer.class);
        int mapWidth = properties.get("width", Integer.class);
        int mapHeight = properties.get("height", Integer.class);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, mapWidth, mapHeight);
        camera.update();

        renderer = new OrthogonalTiledMapRenderer(map, (float) 1 / tileWidth);
        renderer.setView(camera);
    }

    @Override
    public void dispose() {
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.renderer.render();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
