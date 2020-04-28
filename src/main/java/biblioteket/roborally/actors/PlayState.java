package biblioteket.roborally.actors;

public enum PlayState {
    PLAYING{
        @Override
        public PlayState nextTurn() {
            return PLAYING;
        }
    },
    IMMOBILE{
        @Override
        public PlayState nextTurn() {
            return PLAYING;
        }
    },
    ANNOUNCED_POWER_DOWN{
        @Override
        public PlayState nextTurn() {
            return POWERED_DOWN;
        }
    },
    POWERED_DOWN{
        @Override
        public PlayState nextTurn() {
            return PLAYING;
        }
    },
    DESTROYED{
        @Override
        public PlayState nextTurn() {
            return DESTROYED;
        }
    },
    ;


    /**
     * @return true if this is instance of POWERED_DOWN
     */
    public boolean hasAnnouncedPowerDown(){
        return this == ANNOUNCED_POWER_DOWN;
    }

    public boolean isPoweredDown() {
        return this == POWERED_DOWN;
    }

    public boolean isDestroyed(){
        return this == DESTROYED;
    }

    /**
     * @return true if this is instance of playing or announced powered down
     */
    public boolean canMove(){
        return this == PLAYING || this == ANNOUNCED_POWER_DOWN;
    }

    public PlayState announcePowerDown() {
        if (this == DESTROYED || this == ANNOUNCED_POWER_DOWN) return this;
        return ANNOUNCED_POWER_DOWN;
    }

    public abstract PlayState nextTurn();
}
