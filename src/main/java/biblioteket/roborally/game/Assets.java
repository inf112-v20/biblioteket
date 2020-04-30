package biblioteket.roborally.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {
    private AssetManager manager;

    public static final String logo = "logo.png";
    public static final String background = "background2.jpg";
    public static final String playPre = "buttons/playPre.png";
    public static final String playPost = "buttons/playPost.png";
    public static final String quitPre = "buttons/quitPre.png";
    public static final String quitPost = "buttons/quitPost.png";
    public static final String easyButtonPre = "buttons/easyPre.png";
    public static final String easyButtonPost ="buttons/easyPost.png";
    public static final String normalButtonPre = "buttons/normalPre.png";
    public static final String normalButtonPost = "buttons/normalPost.png";
    public static final String hardButtonPre = "buttons/hardPre.png";
    public static final String hardButtonPost = "buttons/hardPost.png";
    public static final String arrowRightPre = "buttons/arrowRightPre.png";
    public static final String arrowRightPost = "buttons/arrowRightPost.png";
    public static final String arrowLeftPre = "buttons/arrowLeftPre.png";
    public static final String arrowLeftPost = "buttons/arrowLeftPost.png";
    public static final String selectNumberOfPlayers = "selectPlayers.png";
    public static final String selectPre = "buttons/selectPre.png";
    public static final String selectPost = "buttons/selectPost.png";
    public static final String dizzyPre = "gamemaps/dizzyPre.png";
    public static final String dizzyPost = "gamemaps/dizzyPost.png";
    public static final String riskyPre = "gamemaps/riskyPre.png";
    public static final String riskyPost = "gamemaps/riskyPost.png";
    public static final String selectMap = "selectMap.png";
    public static final String hp = "hp.png";
    public static final String flag = "flag.png";
    public static final String emptyCard = "programCards/cards.png";
    public static final String moveOneCard = "programCards/move1.png";
    public static final String moveTwoCard = "programCards/move2.png";
    public static final String moveThreeCard = "programCards/move3.png";
    public static final String backUpCard ="programCards/backUp.png";
    public static final String rotateRightCard = "programCards/rotateRight.png";
    public static final String rotateLeftCard = "programCards/rotateLeft.png";
    public static final String uTurnCard = "programCards/uTurn.png";
    public static final String damageToken = "damageToken.png";
    public static final String powerDownButtonPre = "buttons/powerdownPre.png";
    public static final String powerDownButtonPost = "buttons/powerdownPost.png";
    public static final String mainMenuPre = "buttons/mainMenuPre.png";
    public static final String mainMenuPost = "assets/buttons/mainMenuPost.png";


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
        manager.load(hp, Texture.class);
        manager.load(flag, Texture.class);
        manager.load(emptyCard, Texture.class);
        manager.load(moveOneCard, Texture.class);
        manager.load(moveTwoCard, Texture.class);
        manager.load(moveThreeCard, Texture.class);
        manager.load(backUpCard, Texture.class);
        manager.load(rotateRightCard, Texture.class);
        manager.load(rotateLeftCard, Texture.class);
        manager.load(uTurnCard, Texture.class);
        manager.load(damageToken, Texture.class);
        manager.load(powerDownButtonPre, Texture.class);
        manager.load(powerDownButtonPost, Texture.class);
        manager.load(mainMenuPre, Texture.class);
        manager.load(mainMenuPost, Texture.class);



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
