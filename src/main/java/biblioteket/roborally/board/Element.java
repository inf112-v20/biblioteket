package biblioteket.roborally.board;

import biblioteket.roborally.elements.ArchiveMarkerElement;
import biblioteket.roborally.elements.IElement;
import biblioteket.roborally.elements.interacting.*;
import biblioteket.roborally.elements.interacting.cogs.LeftRotatingCogElement;
import biblioteket.roborally.elements.interacting.cogs.RightRotatingCogElement;
import biblioteket.roborally.elements.interacting.conveyorbelts.ConveyorBeltElement;
import biblioteket.roborally.elements.interacting.conveyorbelts.ExpressConveyorBeltElement;
import biblioteket.roborally.elements.walls.LaserWallElement;
import biblioteket.roborally.elements.walls.WallElement;

import java.util.HashMap;
import java.util.Map;

public enum Element {
    GROUND(5),
    HOLE(6),
    HOLE_BIG_1(105),
    HOLE_BIG_2(107),
    HOLE_BIG_3(113),
    HOLE_BIG_4(115),

    DOUBLE_REPAIR(7),
    SINGLE_REPAIR(15),

    RIGHT_WALL(23),
    BOTTOM_WALL(29),
    LEFT_WALL(30),
    TOP_WALL(31),

    RIGHT_LASER_WALL(46),
    BOTTOM_LASER_WALL(37),
    LEFT_LASER_WALL(38),
    TOP_LASER_WALL(45),

    WALL_LEFT_UP(8),
    WALL_RIGHT_DOWN(16),
    WALL_UP_RIGHT(24),
    WALL_DOWN_RIGHT(32),

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

    TURNING_CONVEYOR_BELT_RIGHT_DOWN(33),
    TURNING_CONVEYOR_BELT_DOWN_LEFT(34),
    TURNING_CONVEYOR_BELT_DOWN_RIGHT(35),
    TURNING_CONVEYOR_BELT_LEFT_DOWN(36),
    TURNING_CONVEYOR_BELT_UP_RIGHT(41),
    TURNING_CONVEYOR_BELT_LEFT_UP(42),
    TURNING_CONVEYOR_BELT_RIGHT_UP(43),
    TURNING_CONVEYOR_BELT_UP_LEFT(44),

    TURNING_EXPRESS_CONVEYOR_BELT_RIGHT_DOWN(17),
    TURNING_EXPRESS_CONVEYOR_BELT_DOWN_LEFT(18),
    TURNING_EXPRESS_CONVEYOR_BELT_DOWN_RIGHT(19),
    TURNING_EXPRESS_CONVEYOR_BELT_LEFT_DOWN(20),
    TURNING_EXPRESS_CONVEYOR_BELT_UP_RIGHT(25),
    TURNING_EXPRESS_CONVEYOR_BELT_LEFT_UP(26),
    TURNING_EXPRESS_CONVEYOR_BELT_RIGHT_UP(27),
    TURNING_EXPRESS_CONVEYOR_BELT_UP_LEFT(28),

    FLAG_1(55),
    FLAG_2(63),
    FLAG_3(71),
    FLAG_4(79),

    SPAWN_1(121),
    SPAWN_2(122),
    SPAWN_3(123),
    SPAWN_4(124),
    SPAWN_5(129),
    SPAWN_6(130),
    SPAWN_7(131),
    SPAWN_8(132),
    ;

    private static final Map<Integer, Element> map = new HashMap<>();

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


    public static InteractingElement getInteractiveElement(int id) {
        IElement element = factory(id);
        if (element instanceof InteractingElement)
            return (InteractingElement) element;
        return null;
    }

    public static WallElement getWallElement(int id) {
        IElement element = factory(id);
        if (element instanceof WallElement) {
            return (WallElement) element;
        }
        return null;
    }

    public static boolean isFlag(int id) {
        try {
            IElement element = factory(id);
            return element instanceof FlagElement;
        } catch (UnsupportedOperationException e) {
            return false;
        }
    }

    public static ArchiveMarkerElement getArchiveMarker(int id, int x, int y) {
        try {
            IElement element = factory(id);
            if (element instanceof ArchiveMarkerElement) {
                ArchiveMarkerElement archiveMarker = (ArchiveMarkerElement) element;
                archiveMarker.setPosition(new DirVector(x, y, null));
                return archiveMarker;
            }
        } catch (UnsupportedOperationException e) {
            // Ignored
        }
        return null;
    }

    private static IElement factory(int id) {
        switch (map.get(id)) {
            case HOLE:
            case HOLE_BIG_1:
            case HOLE_BIG_2:
            case HOLE_BIG_3:
            case HOLE_BIG_4:
                return new HoleElement();

            case SINGLE_REPAIR:
                return new SingleWrenchRepairElement();
            case DOUBLE_REPAIR:
                return new DoubleWrenchRepairElement();

            case WALL_LEFT_UP:
                return new WallElement(Direction.EAST, Direction.SOUTH);
            case WALL_RIGHT_DOWN:
                return new WallElement(Direction.EAST, Direction.NORTH);
            case WALL_UP_RIGHT:
                return new WallElement(Direction.WEST, Direction.NORTH);
            case WALL_DOWN_RIGHT:
                return new WallElement(Direction.WEST, Direction.SOUTH);

            case RIGHT_WALL:
                return new WallElement(Direction.EAST, null);
            case BOTTOM_WALL:
                return new WallElement(null, Direction.SOUTH);
            case LEFT_WALL:
                return new WallElement(Direction.WEST, null);
            case TOP_WALL:
                return new WallElement(null, Direction.NORTH);

            case RIGHT_LASER_WALL:
                return new LaserWallElement(Direction.EAST, null);
            case BOTTOM_LASER_WALL:
                return new LaserWallElement(null, Direction.SOUTH);
            case LEFT_LASER_WALL:
                return new LaserWallElement(Direction.WEST, null);
            case TOP_LASER_WALL:
                return new LaserWallElement(null, Direction.NORTH);

            case ROTATOR_RIGHT:
                return new RightRotatingCogElement();
            case ROTATOR_LEFT:
                return new LeftRotatingCogElement();

            case CONVEYOR_BELT_UP:
            case TURNING_CONVEYOR_BELT_LEFT_UP:
            case TURNING_CONVEYOR_BELT_RIGHT_UP:
                return new ConveyorBeltElement(Direction.NORTH);
            case CONVEYOR_BELT_DOWN:
            case TURNING_CONVEYOR_BELT_RIGHT_DOWN:
            case TURNING_CONVEYOR_BELT_LEFT_DOWN:
                return new ConveyorBeltElement(Direction.SOUTH);
            case CONVEYOR_BELT_LEFT:
            case TURNING_CONVEYOR_BELT_DOWN_LEFT:
            case TURNING_CONVEYOR_BELT_UP_LEFT:
                return new ConveyorBeltElement(Direction.WEST);
            case CONVEYOR_BELT_RIGHT:
            case TURNING_CONVEYOR_BELT_UP_RIGHT:
            case TURNING_CONVEYOR_BELT_DOWN_RIGHT:
                return new ConveyorBeltElement(Direction.EAST);

            case EXPRESS_CONVEYOR_BELT_UP:
            case TURNING_EXPRESS_CONVEYOR_BELT_LEFT_UP:
            case TURNING_EXPRESS_CONVEYOR_BELT_RIGHT_UP:
                return new ExpressConveyorBeltElement(Direction.NORTH);
            case EXPRESS_CONVEYOR_BELT_RIGHT:
            case TURNING_EXPRESS_CONVEYOR_BELT_DOWN_RIGHT:
            case TURNING_EXPRESS_CONVEYOR_BELT_UP_RIGHT:
                return new ExpressConveyorBeltElement(Direction.EAST);
            case EXPRESS_CONVEYOR_BELT_DOWN:
            case TURNING_EXPRESS_CONVEYOR_BELT_RIGHT_DOWN:
            case TURNING_EXPRESS_CONVEYOR_BELT_LEFT_DOWN:
                return new ExpressConveyorBeltElement(Direction.SOUTH);
            case EXPRESS_CONVEYOR_BELT_LEFT:
            case TURNING_EXPRESS_CONVEYOR_BELT_DOWN_LEFT:
            case TURNING_EXPRESS_CONVEYOR_BELT_UP_LEFT:
                return new ExpressConveyorBeltElement(Direction.WEST);

            case SPAWN_1:
                return new ArchiveMarkerElement(1);
            case SPAWN_2:
                return new ArchiveMarkerElement(2);
            case SPAWN_3:
                return new ArchiveMarkerElement(3);
            case SPAWN_4:
                return new ArchiveMarkerElement(4);
            case SPAWN_5:
                return new ArchiveMarkerElement(5);
            case SPAWN_6:
                return new ArchiveMarkerElement(6);
            case SPAWN_7:
                return new ArchiveMarkerElement(7);
            case SPAWN_8:
                return new ArchiveMarkerElement(8);

            // Flag
            case FLAG_1:
                return new FlagElement(1);
            case FLAG_2:
                return new FlagElement(2);
            case FLAG_3:
                return new FlagElement(3);
            case FLAG_4:
                return new FlagElement(4);

            default:
                throw new UnsupportedOperationException();
        }
    }

    public int getValue() {
        return value;
    }
}
