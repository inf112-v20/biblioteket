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
    public boolean poweredDown(){
        return this == POWERED_DOWN;
    };

    /**
     * @return true if this is instance of playing or announced powered down
     */
    public boolean canMove(){
        return this == PLAYING || this == ANNOUNCED_POWER_DOWN;
    }

    public abstract PlayState nextTurn();

}
