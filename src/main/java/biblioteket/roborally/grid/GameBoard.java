package biblioteket.roborally.grid;

import biblioteket.roborally.elements.IElement;
import biblioteket.roborally.elements.WallElement;

import java.util.List;

public class GameBoard implements IGameBoard{
    IGrid<IElement> grid;

    public GameBoard(int width, int height){
        grid = new Grid<>(width, height);
    }

    public int getWidth(){
        return this.grid.getWidth();
    }

    public int getHeight(){
        return this.grid.getHeight();
    }

    public boolean placeElement(int x, int y, IElement element){
        return this.grid.placeElement(x,y,element);
    }

    public boolean placeElement(IPosition<IElement> position, IElement element){
        return placeElement(position.getX(), position.getY(), element);
    }

    public IPosition<IElement> positionInDirection(int x, int y, Direction direction){
        return this.grid.positionInDirection(x,y,direction);
    }

    public IPosition<IElement> positionInDirection(IPosition<IElement> position, Direction direction){
        return this.grid.positionInDirection(position.getX(), position.getY(), direction);
    }

    public IPosition<IElement> getPosition(int x, int y){
        return this.grid.getPosition(x,y);
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
        return firstCollisionInDirection(getPosition(x,y), direction);
    }

    @Override
    public IPosition<IElement> firstCollisionInDirection(IPosition<IElement> currentPosition, Direction direction) {
        while(canMove(currentPosition,direction)){
            currentPosition = positionInDirection(currentPosition,direction);
        }
        return positionInDirection(currentPosition,direction);
    }

    @Override
    public boolean canMove(IPosition<IElement> from, Direction direction) {
        IPosition<IElement> to = positionInDirection(from, direction);
        //TODO
        // out of bounds is legal and kills you i believe
        // Not sure atm what to do with containsImmovableObject
        if(to == null) return false; // Out of bounds
        return !moveBlockedByWall(from,direction);

//        if(containsImmovableObject(to)) return false;
    }

    @Override
    public boolean canMove(int x, int y, Direction direction) {
        return canMove(getPosition(x,y), direction);
    }


    /**
     * @param from position robot is moving from
     * @param direction robot is moving
     * @return true if move is blocked by wall
     */
    private boolean moveBlockedByWall(IPosition<IElement> from, Direction direction){
        IPosition<IElement> to = positionInDirection(from, direction);
        for(IElement element: from.getContents()){
            if (element instanceof WallElement){
                WallElement wall = (WallElement) element;
                if (wall.blockingExit(direction)) return true;
            }
        }
        for (IElement element : to.getContents()) {
            if (element instanceof WallElement){
                WallElement wall = (WallElement) element;
                if (wall.blockingEntry(direction)) return true;
            }
        }
        return false;
    }
}
