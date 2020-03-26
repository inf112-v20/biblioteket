package biblioteket.roborally;

import biblioteket.roborally.actors.IPlayer;
import biblioteket.roborally.actors.IRobot;
import biblioteket.roborally.grid.IPosition;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.UUID;

public class Barrelbot<T> implements IRobot<T> {

    private IPosition<T> position;
    private IPosition<T> archiveMarker;
    private Direction direction;

    private IPlayer player;

    private boolean destroyed = false;
    private int damageTokens = 0;

    private static final int FRAME_COLS = 5;
    private static final int FRAME_ROWS = 1;

    private float stateTime = 0;
    private int stepIndex = 0;

    public static final int IDLE = 0;
    private int currentAnimation;

    private Animation<TextureRegion> idleAnimation;

    private TextureRegion[] idleFrames;

    private TextureRegion currentFrame;
    private Animation [] animations;

    public Barrelbot(TextureRegion textureRegionIdle) {
        TextureRegion[] [] tmp = textureRegionIdle.split(textureRegionIdle.getRegionWidth() / FRAME_COLS, textureRegionIdle.getRegionHeight() / FRAME_ROWS);
        idleFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                idleFrames[index++] = tmp[i][j];
            }
        }
        idleAnimation = new Animation(0.3f, idleFrames);
        idleAnimation.setPlayMode(Animation.PlayMode.LOOP);

        animations = new Animation[2];
        animations[0] = idleAnimation;

        setCurrentAnimation(IDLE);
    }
    public void setCurrentAnimation(int currentAnimation) {
        this.currentAnimation = currentAnimation;
        stateTime = 0;
        stepIndex = 0;
    }
    public int getCurrentAnimation() {
        return currentAnimation;
    }
    public void update(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();

        currentFrame = (TextureRegion) animations[currentAnimation].getKeyFrame(stateTime, true);

        batch.begin();
        batch.draw(currentFrame, Gdx.graphics.getWidth() / 2 - currentFrame.getRegionWidth() / 2, Gdx.graphics.getHeight() / 2 - currentFrame.getRegionHeight() / 2);
        batch.end();
    }

    public Barrelbot(IPosition<T> position, IPosition<T> archiveMarker, Direction direction) {
        this.position = position;
        this.archiveMarker = archiveMarker;
        this.direction = direction;
    }

    @Override
    public IPlayer getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(IPlayer player) {
        this.player = player;
    }

    @Override
    public int getNumberOfDamageTokens() {
        return damageTokens;
    }

    @Override
    public void removeDamageTokens(int damageTokens) {

        this.damageTokens = this.damageTokens - damageTokens;
    }

    @Override
    public void addDamageTokens(int damageTokens) {

        this.damageTokens = this.damageTokens + damageTokens;
    }

    @Override
    public void removeAllDamageTokens() {

        this.damageTokens = 0;
    }

    @Override
    public boolean isDestroyed() {

        return damageTokens > 9;
    }

    @Override
    public IPosition<T> getArchiveMarker() {
        return archiveMarker;
    }

    @Override
    public void setArchiveMarker(IPosition<T> location) {
        this.archiveMarker = location;
    }

    @Override
    public IPosition<T> getPosition() {
        return position;
    }

    @Override
    public void setPosition(IPosition<T> location) {
        this.position = location;
    }

    @Override
    public IPosition getPos() {
        return null;
    }

    @Override
    public void setPos(IPosition pos) {

    }


    public void setPos(int x, int y) {

    }

    @Override
    public boolean immovable() {
        return false;
    }


    public UUID getID() {
        return null;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void turnLeft() {
        this.direction = this.direction.direction90DegreesToTheLeft();
    }

    @Override
    public void turnRight() {
        this.direction = this.direction.direction90DegreesToTheRight();
    }

    // TODO
    @Override
    public void moveForward() {
        // this.setPosition(position.locationInDirection(direction));
    }

    // TODO
    @Override
    public void moveBackward() {
        // this.setPosition(position.locationInDirection(direction.oppositeDirection()));
    }

    //TODO
    @Override
    public void pushRobotInDirection(Direction direction) {

    }

    //TODO
    @Override
    public boolean canMoveInDirection(Direction direction) {
        return false;
    }

}
