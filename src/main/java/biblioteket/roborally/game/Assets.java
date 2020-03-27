package biblioteket.roborally.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {
    private AssetManager manager;

    public void load() {
        if (manager == null) {
            manager = new AssetManager();
        }
        manager.load("bb_idle_spritesheet.pack", TextureAtlas.class);
    }

    public AssetManager getManager() {
        return manager;
    }

    public void setManager(AssetManager manager) {
        this.manager = manager;
    }
}
