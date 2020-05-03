package biblioteket.roborally.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {
    public static final String LOGO = "logo.png";
    public static final String BACKGROUND = "background2.jpg";
    public static final String PLAY_PRE = "buttons/playPre.png";
    public static final String PLAY_POST = "buttons/playPost.png";
    public static final String QUIT_PRE = "buttons/quitPre.png";
    public static final String QUIT_POST = "buttons/quitPost.png";
    public static final String EASY_BUTTON_PRE = "buttons/easyPre.png";
    public static final String EASY_BUTTON_POST = "buttons/easyPost.png";
    public static final String NORMAL_BUTTON_PRE = "buttons/normalPre.png";
    public static final String NORMAL_BUTTON_POST = "buttons/normalPost.png";
    public static final String HARD_BUTTON_PRE = "buttons/hardPre.png";
    public static final String HARD_BUTTON_POST = "buttons/hardPost.png";
    public static final String ARROW_RIGHT_PRE = "buttons/arrowRightPre.png";
    public static final String ARROW_RIGHT_POST = "buttons/arrowRightPost.png";
    public static final String ARROW_LEFT_PRE = "buttons/arrowLeftPre.png";
    public static final String ARROW_LEFT_POST = "buttons/arrowLeftPost.png";
    public static final String SELECT_NUMBER_OF_PLAYERS = "selectPlayers.png";
    public static final String SELECT_PRE = "buttons/selectPre.png";
    public static final String SELECT_POST = "buttons/selectPost.png";
    public static final String DIZZY_PRE = "gamemaps/dizzyPre.png";
    public static final String DIZZY_POST = "gamemaps/dizzyPost.png";
    public static final String RISKY_PRE = "gamemaps/riskyPre.png";
    public static final String RISKY_POST = "gamemaps/riskyPost.png";
    public static final String SELECT_MAP = "selectMap.png";
    public static final String HP = "hp.png";
    public static final String FLAG = "flag.png";
    public static final String EMPTY_CARD = "programCards/cards.png";
    public static final String MOVE_ONE_CARD = "programCards/move1.png";
    public static final String MOVE_TWO_CARD = "programCards/move2.png";
    public static final String MOVE_THREE_CARD = "programCards/move3.png";
    public static final String BACK_UP_CARD = "programCards/backUp.png";
    public static final String ROTATE_RIGHT_CARD = "programCards/rotateRight.png";
    public static final String ROTATE_LEFT_CARD = "programCards/rotateLeft.png";
    public static final String U_TURN_CARD = "programCards/uTurn.png";
    public static final String DAMAGE_TOKEN = "damageToken.png";
    public static final String POWER_DOWN_BUTTON_PRE = "buttons/powerdownPre.png";
    public static final String POWER_DOWN_BUTTON_POST = "buttons/powerdownPost.png";
    public static final String MAIN_MENU_PRE = "buttons/mainMenuPre.png";
    public static final String MAIN_MENU_POST = "assets/buttons/mainMenuPost.png";

    public static final String BarrelBot = "playermodels/barrelbot.png";
    public static final String BoxBot = "playermodels/boxbot.png";
    public static final String HammerBot = "playermodels/hammerbot.png";
    public static final String PinBot = "playermodels/pinbot.png";
    public static final String SafeBot = "playermodels/safebot.png";
    public static final String SaucerBot = "playermodels/saucerbot.png";
    public static final String SpinBot = "playermodels/spinbot.png";
    public static final String OwlBot = "playermodels/owlbot.png";
    private AssetManager manager;

    /**
     * Loads all the necessary textures for the game.
     */
    @SuppressWarnings("DuplicatedCode")
    public void load() {
        if (manager == null) {
            manager = new AssetManager();
        }
        manager.load("playermodels/bb_idle_spritesheet.pack", TextureAtlas.class);
        manager.load(LOGO, Texture.class);
        manager.load(BACKGROUND, Texture.class);
        manager.load(PLAY_PRE, Texture.class);
        manager.load(PLAY_POST, Texture.class);
        manager.load(QUIT_PRE, Texture.class);
        manager.load(QUIT_POST, Texture.class);
        manager.load(EASY_BUTTON_PRE, Texture.class);
        manager.load(EASY_BUTTON_POST, Texture.class);
        manager.load(NORMAL_BUTTON_PRE, Texture.class);
        manager.load(NORMAL_BUTTON_POST, Texture.class);
        manager.load(HARD_BUTTON_PRE, Texture.class);
        manager.load(HARD_BUTTON_POST, Texture.class);
        manager.load(ARROW_RIGHT_PRE, Texture.class);
        manager.load(ARROW_RIGHT_POST, Texture.class);
        manager.load(ARROW_LEFT_PRE, Texture.class);
        manager.load(ARROW_LEFT_POST, Texture.class);
        manager.load(SELECT_NUMBER_OF_PLAYERS, Texture.class);
        manager.load(SELECT_PRE, Texture.class);
        manager.load(SELECT_POST, Texture.class);
        manager.load(DIZZY_PRE, Texture.class);
        manager.load(DIZZY_POST, Texture.class);
        manager.load(RISKY_PRE, Texture.class);
        manager.load(RISKY_POST, Texture.class);
        manager.load(SELECT_MAP, Texture.class);
        manager.load(HP, Texture.class);
        manager.load(FLAG, Texture.class);
        manager.load(EMPTY_CARD, Texture.class);
        manager.load(MOVE_ONE_CARD, Texture.class);
        manager.load(MOVE_TWO_CARD, Texture.class);
        manager.load(MOVE_THREE_CARD, Texture.class);
        manager.load(BACK_UP_CARD, Texture.class);
        manager.load(ROTATE_RIGHT_CARD, Texture.class);
        manager.load(ROTATE_LEFT_CARD, Texture.class);
        manager.load(U_TURN_CARD, Texture.class);
        manager.load(DAMAGE_TOKEN, Texture.class);
        manager.load(POWER_DOWN_BUTTON_PRE, Texture.class);
        manager.load(POWER_DOWN_BUTTON_POST, Texture.class);
        manager.load(MAIN_MENU_PRE, Texture.class);
        manager.load(MAIN_MENU_POST, Texture.class);

        manager.load(BarrelBot, Texture.class);
        manager.load(BoxBot, Texture.class);
        manager.load(HammerBot, Texture.class);
        manager.load(PinBot, Texture.class);
        manager.load(SafeBot, Texture.class);
        manager.load(SaucerBot, Texture.class);
        manager.load(SpinBot, Texture.class);
        manager.load(OwlBot, Texture.class);
    }

    /**
     * @return the current asset manager
     */
    public AssetManager getManager() {
        return manager;
    }

    /**
     * Destroy the asset manager.
     */
    public void dispose() {
        manager.dispose();
    }
}
