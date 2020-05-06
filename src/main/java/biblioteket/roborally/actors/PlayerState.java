package biblioteket.roborally.actors;

public enum PlayerState {
    PLAYING {
        @Override
        public PlayerState nextTurn() {
            return PLAYING;
        }
    },
    IMMOBILE {
        @Override
        public PlayerState nextTurn() {
            return PLAYING;
        }
    },
    ANNOUNCED_POWER_DOWN {
        @Override
        public PlayerState nextTurn() {
            return POWERED_DOWN;
        }
    },
    POWERED_DOWN {
        @Override
        public PlayerState nextTurn() {
            return PLAYING;
        }
    },
    DESTROYED {
        @Override
        public PlayerState nextTurn() {
            return DESTROYED;
        }
    },
    ;

    /**
     * @return true if this is instance of POWERED_DOWN
     */
    public boolean isPoweredDown() {
        return this == POWERED_DOWN;
    }

    /**
     * @return true if this is instance of DESTROYED
     */
    public boolean isDestroyed() {
        return this == DESTROYED;
    }

    /**
     * @return true if this is instance of playing or announced powered down
     */
    public boolean canMove() {
        return this == PLAYING || this == ANNOUNCED_POWER_DOWN;
    }

    /**
     * Returns instance of ANNOUNCED_POWER_DOWN unless instance is already DESTROYED
     *
     * @return instance ANNOUNCED_POWERED_DOWN
     */
    public PlayerState announcePowerDown() {
        if (this == DESTROYED) return this;
        return ANNOUNCED_POWER_DOWN;
    }

    /**
     * @return instance of the next turn
     */
    public abstract PlayerState nextTurn();
}
