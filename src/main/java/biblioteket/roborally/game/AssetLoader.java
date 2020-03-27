package biblioteket.roborally.game;

import biblioteket.roborally.actors.BarrelBot;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetLoader extends ApplicationAdapter {
    SpriteBatch batch;
    private Skin skin;

    private Assets assets;
    private BarrelBot player;

    @Override
    public void create() {
        batch = new SpriteBatch();
        Gdx.gl.glClearColor(1f, 1f, 1f, 1);

        assets = new Assets();
        assets.load();
        assets.getManager().finishLoading();

        skin = new Skin();
        skin.addRegions(assets.getManager().get("bb_idle_spritesheet.pack", TextureAtlas.class));

        player = new BarrelBot(skin.getRegion("bb_idle"));

    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        player.update(batch);

    }

    @Override
    public void dispose() {
        skin.dispose();
        batch.dispose();
    }
}
