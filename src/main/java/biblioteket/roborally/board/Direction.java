package biblioteket.roborally.board;

public enum Direction {
    NORTH {
        @Override
        public Direction left() {
            return Direction.WEST;
        }

        @Override
        public Direction right() {
            return Direction.EAST;
        }

        @Override
        public Direction opposite() {
            return Direction.SOUTH;
        }
    }, EAST {
        @Override
        public Direction left() {
            return Direction.NORTH;
        }

        @Override
        public Direction right() {
            return Direction.SOUTH;
        }

        @Override
        public Direction opposite() {
            return Direction.WEST;
        }
    }, SOUTH {
        @Override
        public Direction left() {
            return Direction.EAST;
        }

        @Override
        public Direction right() {
            return Direction.WEST;
        }

        @Override
        public Direction opposite() {
            return Direction.NORTH;
        }
    }, WEST {
        @Override
        public Direction left() {
            return Direction.SOUTH;
        }

        @Override
        public Direction right() {
            return Direction.NORTH;
        }

        @Override
        public Direction opposite() {
            return Direction.EAST;
        }
    };

    /**
     * Will return the direction 90 degrees to the left.<p>
     * {@link #NORTH} -> {@link #WEST} <p>
     * {@link #SOUTH} -> {@link #EAST} <p>
     * {@link #EAST} -> {@link #NORTH} <p>
     * {@link #WEST} -> {@link #SOUTH} <p>
     *
     * @return direction 90 degrees to left.
     */
    public abstract Direction left();

    /**
     * Will return the direction 90 degrees to the right.<p>
     * {@link #NORTH} -> {@link #EAST} <p>
     * {@link #SOUTH} -> {@link #WEST} <p>
     * {@link #EAST} -> {@link #SOUTH} <p>
     * {@link #WEST} -> {@link #NORTH} <p>
     *
     * @return direction 90 degrees to right.
     */
    public abstract Direction right();

    /**
     * Will return the opposite direction 180 degrees away.<p>
     * {@link #NORTH} -> {@link #SOUTH} <p>
     * {@link #SOUTH} -> {@link #NORTH} <p>
     * {@link #EAST} -> {@link #WEST} <p>
     * {@link #WEST} -> {@link #EAST} <p>
     *
     * @return opposite direction.
     */
    public abstract Direction opposite();
}
