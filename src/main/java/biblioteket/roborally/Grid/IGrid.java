package biblioteket.roborally.Grid;

import biblioteket.roborally.Elements.IElement;
import biblioteket.roborally.ILocation;

public interface IGrid extends Iterable<ILocation> {
    int getWidth();
    int getHeight();


    /**
     * Places an element in an ILocation in a given x,y location
     * @param x x position
     * @param y y position
     */
    void placeElement(int x, int y, IElement element);

    IPosition getPosition(int x, int y);
}
