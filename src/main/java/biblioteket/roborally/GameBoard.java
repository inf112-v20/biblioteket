package biblioteket.roborally;

import biblioteket.roborally.board.Direction;
import biblioteket.roborally.grid.Grid;
import biblioteket.roborally.grid.IGrid;
import biblioteket.roborally.grid.IPosition;

import java.util.List;

public class GameBoard implements IGameBoard {
    IGrid<IElement> grid;

    GameBoard(int width, int height) {
        grid = new Grid<>(width, height);
    }

    public int getWidth() {
        return this.grid.getWidth();
    }

    public int getHeight() {
        return this.grid.getHeight();
    }

    public boolean placeElement(int x, int y, IElement element) {
        return grid.placeElement(x, y, element);
    }

    public boolean placeElement(IPosition<IElement> position, IElement element) {
        return placeElement(position.getX(), position.getY(), element);
    }

    //Need this for testing, should be removed?
    public IGrid<IElement> getGrid() {
        return this.grid;
    }

    @Override
    public boolean containsImmovableObject(int x, int y, Direction direction) {
        IPosition<IElement> positionInDirection = grid.positionInDirection(x, y, direction);
        List<IElement> contents = positionInDirection.getContents();
        for (IElement element : contents) {
            if (element.immovable()) return true;
        }
        return false;
    }

    @Override
    public boolean containsImmovableObject(IPosition<IElement> currentPosition, Direction direction) {
        return containsImmovableObject(currentPosition.getX(), currentPosition.getY(), direction);
    }

    //TODO
    @Override
    public IPosition<IElement> firstCollisionInDirection(int x, int y, Direction direction) {
        return null;
    }

    @Override
    public IPosition<IElement> firstCollisionInDirection(IPosition<IElement> currentPosition, Direction direction) {
        return firstCollisionInDirection(currentPosition.getX(), currentPosition.getY(), direction);
    }
}
