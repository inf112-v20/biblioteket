package biblioteket.roborally.elements;

// Should probably be named something with element
public enum BoardObject {
    HOLE, WALL, CONVEYOR_BELT, CONVEYOR_BELT_FAST, RESPAWN_POINT, FLAG, ROTATION_OBJECT;

    //Better way to do this?
    public BoardObject getBoardObject(int ID){
        if (ID == 6)
            return BoardObject.HOLE;
        else if (ID == 8 || ID == 15 || ID == 23|| ID == 24 || ID == 29 || ID == 30 || ID == 31 || ID == 32)
            return BoardObject.WALL;
        else if (ID == 49 || ID == 50 || ID == 51 || ID == 52)
            return BoardObject.CONVEYOR_BELT;
        else if (ID == 13 || ID == 14 || ID == 21 || ID == 22)
            return BoardObject.CONVEYOR_BELT_FAST;
        else if (ID == 121 || ID == 122 || ID == 123 || ID == 124 || ID == 129 || ID == 130 || ID == 131 || ID == 132)
            return BoardObject.RESPAWN_POINT;
        else if (ID == 55 || ID == 63 || ID == 71 || ID == 78)
            return BoardObject.FLAG;
        else if (ID == 33 || ID == 34 || ID == 35 || ID == 36 || ID == 41 || ID == 42 || ID == 43 || ID == 44)
            return BoardObject.ROTATION_OBJECT;
        else {
            return null;
        }
    }

}
