package biblioteket.roborally.elements;

import biblioteket.roborally.board.DirVector;

public class ArchiveMarkerElement implements IElement {
    private int archiveNum;
    private DirVector position;

    public ArchiveMarkerElement(int archiveNum) {
        this.archiveNum = archiveNum;
        position = new DirVector(0, 0, null);
    }

    public int getX() {
        return position.getX();
    }

    public void setX(int x) {
        position.setX(x);
    }

    public int getY() {
        return position.getY();
    }

    public void setY(int y) {
        position.setY(y);
    }

    public int getArchiveNum() {
        return archiveNum;
    }
}
