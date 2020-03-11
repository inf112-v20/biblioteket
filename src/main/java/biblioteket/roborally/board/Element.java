package biblioteket.roborally.board;

import biblioteket.roborally.elements.*;

import java.util.HashMap;
import java.util.Map;

public enum Element {
    GROUND(5),
    HOLE(6),

    DOUBLE_REPAIR(7),
    SINGLE_REPAIR(15),

    RIGHT_WALL(23),
    BOTTOM_WALL(29),
    LEFT_WALL(30),
    TOP_WALL(31),

    WALL_LEFT_UP(8),
    WALL_RIGHT_DOWN(16),
    WALL_UP_RIGHT(24),
    WALL_DOWN_RIGHT(32),

    BOTTOM_LASER(37),
    RIGHT_LASER(38),
    TOP_LASER(44),
    LEFT_LASER(46),

    ROTATOR_RIGHT(53),
    ROTATOR_LEFT(54),

    CONVEYOR_BELT_UP(49),
    CONVEYOR_BELT_DOWN(50),
    CONVEYOR_BELT_LEFT(51),
    CONVEYOR_BELT_RIGHT(52),

    EXPRESS_CONVEYOR_BELT_UP(13),
    EXPRESS_CONVEYOR_BELT_RIGHT(14),
    EXPRESS_CONVEYOR_BELT_DOWN(21),
    EXPRESS_CONVEYOR_BELT_LEFT(22),

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

    private final static Map<Integer, Element> map = new HashMap<>();

    static {
        for (Element pageType : Element.values()) {
            map.put(pageType.value, pageType);
        }
    }

    private final int value;

    Element(int value) {
        this.value = value;
    }

    public static Element valueOf(int pageType) {
        return map.get(pageType);
    }

    public int getValue() {
        return value;
    }

    public static boolean isWall(int id) {
        switch (map.get(id)) {
            case WALL_LEFT_UP:
            case TOP_WALL:
            case LEFT_WALL:
            case BOTTOM_WALL:
            case RIGHT_WALL:
            case WALL_DOWN_RIGHT:
            case WALL_UP_RIGHT:
            case WALL_RIGHT_DOWN:
                return true;
            default: return false;
        }
    }

    public static boolean isInteractive(int id) {
        switch (map.get(id)) {
            case HOLE:
            case CONVEYOR_BELT_UP:
            case CONVEYOR_BELT_DOWN:
            case CONVEYOR_BELT_LEFT:
            case CONVEYOR_BELT_RIGHT:
            case EXPRESS_CONVEYOR_BELT_UP:
            case EXPRESS_CONVEYOR_BELT_DOWN:
            case EXPRESS_CONVEYOR_BELT_RIGHT:
            case EXPRESS_CONVEYOR_BELT_LEFT:
            case FLAG_1:
            case FLAG_2:
            case FLAG_3:
            case FLAG_4:
                return true;
            default: return false;
        }
    }

    public static IElement factory(int ID) {
        switch (map.get(ID)) {
            case HOLE: return new HoleElement();

            case WALL_LEFT_UP: return new WallElement(Direction.EAST, Direction.SOUTH);
            case WALL_RIGHT_DOWN: return new WallElement(Direction.EAST, Direction.NORTH);
            case WALL_UP_RIGHT: return new WallElement(Direction.WEST, Direction.NORTH);
            case WALL_DOWN_RIGHT: return new WallElement(Direction.WEST, Direction.SOUTH);

            case RIGHT_WALL: return new WallElement(Direction.EAST, null);
            case BOTTOM_WALL: return new WallElement(null, Direction.SOUTH);
            case LEFT_WALL: return new WallElement(Direction.WEST, null);
            case TOP_WALL: return new WallElement(null, Direction.NORTH);

            case CONVEYOR_BELT_UP: return new ConveyorBeltElement(Direction.NORTH);
            case CONVEYOR_BELT_DOWN: return new ConveyorBeltElement(Direction.SOUTH);
            case CONVEYOR_BELT_LEFT: return new ConveyorBeltElement(Direction.WEST);
            case CONVEYOR_BELT_RIGHT: return new ConveyorBeltElement(Direction.EAST);

            case EXPRESS_CONVEYOR_BELT_UP: return new ExpressConveyorBeltElement(Direction.NORTH);
            case EXPRESS_CONVEYOR_BELT_RIGHT: return new ExpressConveyorBeltElement(Direction.EAST);
            case EXPRESS_CONVEYOR_BELT_DOWN: return new ExpressConveyorBeltElement(Direction.SOUTH);
            case EXPRESS_CONVEYOR_BELT_LEFT: return new ExpressConveyorBeltElement(Direction.WEST);

            case SPAWN_1: return new RespawnPointElement(1);
            case SPAWN_2: return new RespawnPointElement(2);
            case SPAWN_3: return new RespawnPointElement(3);
            case SPAWN_4: return new RespawnPointElement(4);
            case SPAWN_5: return new RespawnPointElement(5);
            case SPAWN_6: return new RespawnPointElement(6);
            case SPAWN_7: return new RespawnPointElement(7);
            case SPAWN_8: return new RespawnPointElement(8);

            // Flag
            case FLAG_1: return new FlagElement(1);
            case FLAG_2: return new FlagElement(2);
            case FLAG_3: return new FlagElement(3);
            case FLAG_4: return new FlagElement(4);

            default: throw new UnsupportedOperationException();
        }
    }
}
