package biblioteket.roborally.elements;

public class ArchiveMarkerElement implements IElement {
    private int archiveNum;
    private int x;
    private int y;

    public ArchiveMarkerElement(int archiveNum) {
        this.archiveNum = archiveNum;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getArchiveNum() {
        return archiveNum;
    }
}
