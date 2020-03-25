package biblioteket.roborally.game;

import biblioteket.roborally.board.IBoard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InterfaceRenderer {

    private Texture background;
    private Texture cards;
    private Texture playerOverview;
    private Texture flag;
    private Texture hp;
    private SpriteBatch batch;
    private BitmapFont font;
    private IBoard board;

    public InterfaceRenderer(IBoard board){
        batch = new SpriteBatch();
        background = new Texture("assets/background2.jpg");
        cards = new Texture("assets/cards.png");
        playerOverview = new Texture("assets/playerOverview.jpg");
        hp = new Texture("assets/hp.png");
        flag = new Texture("assets/flag.png");
        font = new BitmapFont();
        this.board = board;
    }

    public void render(){
        batch.begin();
        batch.draw(playerOverview, 0, Gdx.graphics.getHeight() - 90, Gdx.graphics.getWidth(), 90);
        batch.draw(background, board.getTileWidth(), 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(flag, 290, Gdx.graphics.getHeight() - 180, 40, 40);
        batch.draw(hp, 330, Gdx.graphics.getHeight() - 180, 40, 40);
        font.draw(batch, "Player 1", 300, Gdx.graphics.getHeight() - 120);
        font.draw(batch, "1", 310, Gdx.graphics.getHeight() - 165);
        font.draw(batch, "3", 360, Gdx.graphics.getHeight() - 165);

        batch.draw(cards, 350, 0, 100, 90);
        batch.draw(cards, 400, 0, 100, 90);
        batch.draw(cards, 450, 0, 100, 90);
        batch.draw(cards, 500, 0, 100, 90);
        batch.draw(cards, 550, 0, 100, 90);
        batch.end();
    }
}
