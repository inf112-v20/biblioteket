package biblioteket.roborally;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {
    public AssetManager manager;

    public void load() {
        if (manager==null) {
            manager = new AssetManager();
        }
        manager.load("bb_idle_spritesheet.pack", TextureAtlas.class);
    }
}
