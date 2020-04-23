package biblioteket.roborally.elements;

import biblioteket.roborally.board.DirVector;

public class ArchiveMarkerElement implements IElement {
    private final int archiveNum;
    private DirVector position;

    public ArchiveMarkerElement(int archiveNum) {
        this.archiveNum = archiveNum;
        position = new DirVector(0, 0, null);
    }

    public DirVector getPosition() {
        return this.position;
    }

    public void setPosition(DirVector position) {
        this.position = position;
    }

    public int getArchiveNum() {
        return archiveNum;
    }
}
