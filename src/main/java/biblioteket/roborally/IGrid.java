package biblioteket.roborally;

public interface IGrid<T> extends Iterable<IPosition> {
    int getWidth();
    int getHeight();


    /**
     * Places an element in an IPosition in a given x,y location
     * @param x x position
     * @param y y position
     */
    void placeElement(int x, int y, T element);
    void placeElement(IPosition position, T element);

    IPosition getPosition(int x, int y);


}
