package biblioteket.roborally.board;

import java.util.HashMap;
import java.util.Map;

public enum Tile {
    GROUND(5),
    HOLE(6),

    DOUBLE_REPAIR(7),
    SINGLE_REPAIR(15),

    TOP_WALL(31),
    RIGHT_WALL(23),
    BOTTOM_WALL(29),
    LEFT_WALL(30),

    WALL_LEFT_UP(8),
    WALL_RIGHT_DOWN(16),
    WALL_UP_RIGHT(24),
    WALL_DOWN_RIGHT(32),

    TOP_LASER(44),
    RIGHT_LASER(38),
    BOTTOM_LASER(37),
    LEFT_LASER(46),

    ROTATOR_RIGHT(53),
    ROTATOR_LEFT(54),

    FLAG_1(55),
    FLAG_2(63),
    FLAG_3(71),
    FLAG_4(78),

    SPAWN_1(121),
    SPAWN_2(122),
    SPAWN_3(123),
    SPAWN_4(124),
    SPAWN_5(129),
    SPAWN_6(130),
    SPAWN_7(131),
    SPAWN_8(132),
    ;


    private final int value;
    private final static Map<Integer, Tile> map = new HashMap<>();

    Tile(int value) {
        this.value = value;
    }

    static {
        for (Tile pageType : Tile.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static Tile valueOf(int pageType) {
        return map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
