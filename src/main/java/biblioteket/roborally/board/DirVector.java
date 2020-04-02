package biblioteket.roborally.board;

import com.badlogic.gdx.math.Vector2;

import java.util.Objects;

/**
 * A small wrapper class around Vector2 that adds a direction.
 */
public class DirVector {
    private Vector2 vector;
    private Direction direction;

    public DirVector(int x, int y, Direction direction) {
        this.vector = new Vector2(x, y);
        this.direction = direction;
    }

    /**
     * Return a vector of (x,y) coordinates
     *
     * @return vector
     */
    public Vector2 getVector() {
        return vector;
    }

    /**
     * Updates the (x,y) coordinates
     *
     * @param vector a new vector
     */
    public void setVector(Vector2 vector) {
        this.vector = vector;
    }

    /**
     * Updates the (x,y) coordinates.
     *
     * @param x x-position
     * @param y y-position
     */
    public void setVector(int x, int y) {
        this.setVector(new Vector2(x, y));
    }

    /**
     * Returns the current x-position on the board;
     *
     * @return x-position
     */
    public int getX() {
        return (int) this.vector.x;
    }

    /**
     * Updates the x-position of the vector.
     *
     * @param x position
     */
    public void setX(int x) {
        this.vector = new Vector2(x, this.vector.y);
    }

    /**
     * Returns the current y-position on the board;
     *
     * @return y-position
     */
    public int getY() {
        return (int) this.vector.y;
    }

    /**
     * Updates the y-position of the vector.
     *
     * @param y position
     */
    public void setY(int y) {
        this.vector = new Vector2(this.vector.x, y);
    }

    /**
     * Returns the director that it is pointing.
     *
     * @return direction of vector.
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Set a new direction for the vector.
     *
     * @param direction new direction
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Changes the direction to whatever direction is to the left.
     */
    public void left() {
        this.direction = this.direction.left();
    }

    /**
     * Changes the direction to whatever direction is to the right.
     */
    public void right() {
        this.direction = this.direction.right();
    }

    /**
     * Sets the location n-steps ahead.
     *
     * @param moves how many spaces to move
     */
    public void forward(int moves) {
        switch (this.direction) {
            case NORTH:
                this.setY(this.getY() + moves);
                break;
            case SOUTH:
                this.setY(this.getY() - moves);
                break;
            case WEST:
                this.setX(this.getX() - moves);
                break;
            case EAST:
                this.setX(this.getX() + moves);
                break;
            default:
                throw new UnsupportedOperationException();
        }
    }

    /**
     * Sets the location n-steps backwards.
     *
     * @param moves how many spaces to move
     */
    public void backward(int moves) {
        switch (this.direction) {
            case NORTH:
                this.setY(this.getY() - moves);
                break;
            case SOUTH:
                this.setY(this.getY() + moves);
                break;
            case WEST:
                this.setX(this.getX() + moves);
                break;
            case EAST:
                this.setX(this.getX() - moves);
                break;
            default:
                throw new UnsupportedOperationException();
        }
    }

    /**
     * Returns a new location if you move in the direction given as a parameter.
     *
     * @param direction direction to move
     * @return new location in direction
     */
    public DirVector dirVectorInDirection(Direction direction) {
        switch (direction) {
            case NORTH:
                return new DirVector(this.getX(), this.getY() + 1, direction);
            case SOUTH:
                return new DirVector(this.getX(), this.getY() - 1, direction);
            case WEST:
                return new DirVector(this.getX() - 1, this.getY(), direction);
            case EAST:
                return new DirVector(this.getX() + 1, this.getY(), direction);
            default:
                throw new UnsupportedOperationException();
        }
    }

    public DirVector copy(){
        DirVector copy = new DirVector(getX(), getY(), getDirection());
        return copy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DirVector dirVector = (DirVector) o;

        if (!Objects.equals(vector, dirVector.vector)) return false;
        return direction == dirVector.direction;
    }

    @Override
    public int hashCode() {
        int result = vector != null ? vector.hashCode() : 0;
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DirVector{" +
                "vector=" + vector +
                ", direction=" + direction +
                '}';
    }
}
