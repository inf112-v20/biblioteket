package biblioteket.roborally;

import biblioteket.roborally.game.RoboRally;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Roborally";
        cfg.width = WIDTH;
        cfg.height = HEIGHT;

        new LwjglApplication(new RoboRally(WIDTH, HEIGHT), cfg);
    }
}