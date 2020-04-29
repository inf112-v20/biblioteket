package biblioteket.roborally.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {
    private AssetManager manager;

    public static final String logo = "assets/logo.png";
    public static final String background = "background2.jpg";
    public static final String playPre = "assets/buttons/playPre.png";
    public static final String playPost = "assets/buttons/playPost.png";
    public static final String quitPre = "assets/buttons/quitPre.png";
    public static final String quitPost = "assets/buttons/quitPost.png";
    public static final String easyButtonPre = "assets/buttons/easyPre.png";
    public static final String easyButtonPost ="assets/buttons/easyPost.png";
    public static final String normalButtonPre = "assets/buttons/normalPre.png";
    public static final String normalButtonPost = "assets/buttons/normalPost.png";
    public static final String hardButtonPre = "assets/buttons/hardPre.png";
    public static final String hardButtonPost = "assets/buttons/hardPost.png";
    public static final String arrowRightPre = "assets/buttons/arrowRightPre.png";
    public static final String arrowRightPost = "assets/buttons/arrowRightPost.png";
    public static final String arrowLeftPre = "assets/buttons/arrowLeftPre.png";
    public static final String arrowLeftPost = "assets/buttons/arrowLeftPost.png";
    public static final String selectNumberOfPlayers = "assets/selectPlayers.png";
    public static final String selectPre = "assets/buttons/selectPre.png";
    public static final String selectPost = "assets/buttons/selectPost.png";
    public static final String dizzyPre = "assets/gamemaps/dizzyPre.png";
    public static final String dizzyPost = "assets/gamemaps/dizzyPost.png";
    public static final String riskyPre = "assets/gamemaps/riskyPre.png";
    public static final String riskyPost = "assets/gamemaps/riskyPost.png";
    public static final String selectMap = "assets/selectMap.png";

    public void load() {
        if (manager == null) {
            manager = new AssetManager();
        }
        manager.load("bb_idle_spritesheet.pack", TextureAtlas.class);
        manager.load(logo, Texture.class);
        manager.load(background, Texture.class);
        manager.load(playPre, Texture.class);
        manager.load(playPost, Texture.class);
        manager.load(quitPre, Texture.class);
        manager.load(quitPost, Texture.class);
        manager.load(easyButtonPre, Texture.class);
        manager.load(easyButtonPost, Texture.class);
        manager.load(normalButtonPre, Texture.class);
        manager.load(normalButtonPost, Texture.class);
        manager.load(hardButtonPre, Texture.class);
        manager.load(hardButtonPost, Texture.class);
        manager.load(arrowRightPre, Texture.class);
        manager.load(arrowRightPost, Texture.class);
        manager.load(arrowLeftPre, Texture.class);
        manager.load(arrowLeftPost, Texture.class);
        manager.load(selectNumberOfPlayers, Texture.class);
        manager.load(selectPre, Texture.class);
        manager.load(selectPost, Texture.class);
        manager.load(dizzyPre, Texture.class);
        manager.load(dizzyPost, Texture.class);
        manager.load(riskyPre, Texture.class);
        manager.load(riskyPost, Texture.class);
        manager.load(selectMap, Texture.class);
    }

    public AssetManager getManager() {
        return manager;
    }

    public void setManager(AssetManager manager) {
        this.manager = manager;
    }

    public void dispose() { manager.dispose();
    }
}
