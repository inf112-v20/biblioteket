package biblioteket.roborally;

import biblioteket.roborally.grid.Grid;
import biblioteket.roborally.grid.IGrid;
import biblioteket.roborally.grid.IPosition;

import java.util.List;

public class GameBoard implements IGameBoard{
    IGrid<IElement> grid;

    GameBoard(int width, int height){
        grid = new Grid<>(width, height);
    }

    public int getWidth(){
        return getGrid().getWidth();
    }

    public int getHeight(){
        return getGrid().getHeight();
    }

    public boolean placeElement(int x, int y, IElement element){
        return getGrid().placeElement(x,y,element);
    }

    public boolean placeElement(IPosition<IElement> position, IElement element){
        return placeElement(position.getX(), position.getY(), element);
    }

    //Need this for testing, should be removed?
    public IGrid<IElement> getGrid(){
        return this.grid;
    }

    public IPosition<IElement> positionInDirection(int x, int y, Direction direction){
        return getGrid().positionInDirection(x,y,direction);
    }

    public IPosition<IElement> positionInDirection(IPosition<IElement> position, Direction direction){
        return getGrid().positionInDirection(position.getX(), position.getY(), direction);
    }

    public IPosition<IElement> getPosition(int x, int y){
        return getGrid().getPosition(x,y);
    }

    @Override
    public boolean containsImmovableObject(int x, int y, Direction direction) {
        IPosition<IElement> positionInDirection = positionInDirection(x,y,direction);
        if(positionInDirection == null) return false;

        List<IElement> contents = positionInDirection.getContents();
        for (IElement element : contents) {
            if(element.immovable()) return true;
        }
        return false;
    }

    @Override
    public boolean containsImmovableObject(IPosition<IElement> currentPosition, Direction direction) {
        return containsImmovableObject(currentPosition.getX(), currentPosition.getY(), direction);
    }

    @Override
    public boolean containsImmovableObject(int x, int y) {
        IPosition<IElement> currentPosition = getPosition(x,y);
        return containsImmovableObject(currentPosition);
    }

    @Override
    public boolean containsImmovableObject(IPosition<IElement> currentPosition) {
        if(currentPosition == null) return false;
        List<IElement> contents = currentPosition.getContents();
        for (IElement element : contents) {
            if(element.immovable()) return true;
        }
        return false;
    }

    //TODO
    @Override
    public IPosition<IElement> firstCollisionInDirection(int x, int y, Direction direction) {
        IPosition<IElement> posInDirection = positionInDirection(x,y, direction);
        while(!(posInDirection == null) && !containsImmovableObject(posInDirection)){
            posInDirection = positionInDirection(posInDirection,direction);
        }

        return posInDirection;
    }

    @Override
    public IPosition<IElement> firstCollisionInDirection(IPosition<IElement> currentPosition, Direction direction) {
        return firstCollisionInDirection(currentPosition.getX(), currentPosition.getY(), direction);
    }
}
