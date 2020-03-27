package biblioteket.roborally.actors;

import biblioteket.roborally.elements.ArchiveMarkerElement;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.List;

public class BarrelBot extends Robot {
    public static final int IDLE = 0;
    private static final int FRAME_COLS = 5;
    private static final int FRAME_ROWS = 1;
    private final List<Animation<TextureRegion>> animations;
    private float stateTime = 0;
    private int stepIndex = 0;
    private int currentAnimation;

    public BarrelBot(TextureRegion textureRegionIdle) {
        super(new ArchiveMarkerElement(0));
        TextureRegion[][] tmp = textureRegionIdle.split(textureRegionIdle.getRegionWidth() / FRAME_COLS, textureRegionIdle.getRegionHeight() / FRAME_ROWS);
        TextureRegion[] idleFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                idleFrames[index++] = tmp[i][j];
            }
        }
        Animation<TextureRegion> idleAnimation = new Animation<>(0.3f, idleFrames);
        idleAnimation.setPlayMode(Animation.PlayMode.LOOP);

        animations = new ArrayList<>();
        animations.add(0, idleAnimation);

        setCurrentAnimation(IDLE);
    }

    public int getCurrentAnimation() {
        return currentAnimation;
    }

    public void setCurrentAnimation(int currentAnimation) {
        this.currentAnimation = currentAnimation;
        stateTime = 0;
        stepIndex = 0;
    }

    public void update(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();

        TextureRegion currentFrame = animations.get(currentAnimation).getKeyFrame(stateTime, true);

        batch.begin();
        batch.draw(currentFrame, (float) Gdx.graphics.getWidth() / 2 - (float) currentFrame.getRegionWidth() / 2, (float) Gdx.graphics.getHeight() / 2 - (float) currentFrame.getRegionHeight() / 2);
        batch.end();
    }

    public int getStepIndex() {
        return stepIndex;
    }

    public void setStepIndex(int stepIndex) {
        this.stepIndex = stepIndex;
    }
}
