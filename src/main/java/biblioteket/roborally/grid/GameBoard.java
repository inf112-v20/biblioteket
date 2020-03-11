// package biblioteket.roborally.grid;
//
// import biblioteket.roborally.elements.IElement;
// import biblioteket.roborally.elements.InteractingElement;
// import biblioteket.roborally.elements.WallElement;
//
// import java.util.List;
//
// public class GameBoard implements IGameBoard{
//
//     public GameBoard(int width, int height){
//         grid = new Grid<>(width, height);
//     }
//
//     public int getWidth(){
//         return this.grid.getWidth();
//     }
//
//     public int getHeight(){
//         return this.grid.getHeight();
//     }
//
//     public boolean placeElement(int x, int y, IElement element){
//         return this.grid.placeElement(x,y,element);
//     }
//
//     public boolean placeElement(IPosition<IElement> position, IElement element){
//         return placeElement(position.getX(), position.getY(), element);
//     }
//
//     public IPosition<IElement> positionInDirection(int x, int y, DirVector direction){
//         return this.grid.positionInDirection(x,y,direction);
//     }
//
//     public IPosition<IElement> positionInDirection(IPosition<IElement> position, DirVector direction){
//         return this.grid.positionInDirection(position.getX(), position.getY(), direction);
//     }
//
//     public IPosition<IElement> getPosition(int x, int y){
//         return this.grid.getPosition(x,y);
//     }
//
//     @Override
//     public boolean containsImmovableObject(int x, int y, DirVector direction) {
//         IPosition<IElement> positionInDirection = positionInDirection(x,y,direction);
//         if(positionInDirection == null) return false;
//
//         List<IElement> contents = positionInDirection.getContents();
//         for (IElement element : contents) {
//             if(element.immovable()) return true;
//         }
//         return false;
//     }
//
//     @Override
//     public boolean containsImmovableObject(IPosition<IElement> currentPosition, DirVector direction) {
//         return containsImmovableObject(currentPosition.getX(), currentPosition.getY(), direction);
//     }
//
//     @Override
//     public boolean containsImmovableObject(int x, int y) {
//         IPosition<IElement> currentPosition = getPosition(x,y);
//         return containsImmovableObject(currentPosition);
//     }
//
//     @Override
//     public boolean containsImmovableObject(IPosition<IElement> currentPosition) {
//         if(currentPosition == null) return false;
//         List<IElement> contents = currentPosition.getContents();
//         for (IElement element : contents) {
//             if(element.immovable()) return true;
//         }
//         return false;
//     }
//
//
//     @Override
//     public IPosition<IElement> firstCollisionInDirection(int x, int y, DirVector direction) {
//         return firstCollisionInDirection(getPosition(x,y), direction);
//     }
//
//     // This can not be dependant on canMove if lasers rely on it to know, but might be able to know where lasers shoot
//     // from mapreader
//     @Override
//     public IPosition<IElement> firstCollisionInDirection(IPosition<IElement> currentPosition, DirVector direction) {
//         while(canMove(currentPosition,direction)){
//             currentPosition = positionInDirection(currentPosition,direction);
//         }
//         return positionInDirection(currentPosition,direction);
//     }
//
//     @Override
//     public boolean canMove(IPosition<IElement> from, DirVector direction) {
//         IPosition<IElement> to = positionInDirection(from, direction);
//         //TODO
//         // out of bounds is legal and kills you i believe
//         // Not sure atm what to do with containsImmovableObject
//         if(to == null) return false; // Out of bounds
//         return !moveBlockedByWall(from,direction);
//
// //        if(containsImmovableObject(to)) return false;
//     }
//
//     @Override
//     public boolean canMove(int x, int y, DirVector direction) {
//         return canMove(getPosition(x,y), direction);
//     }
//
//
//     /**
//      * @param from position robot is moving from
//      * @param direction robot is moving
//      * @return true if move is blocked by wall
//      */
//     private boolean moveBlockedByWall(IPosition<IElement> from, DirVector direction){
//         IPosition<IElement> to = positionInDirection(from, direction);
//         for(IElement element: from.getContents()){
//             if (element instanceof WallElement){
//                 WallElement wall = (WallElement) element;
//                 if (wall.blockingExit(direction)) return true;
//             }
//         }
//         for (IElement element : to.getContents()) {
//             if (element instanceof WallElement){
//                 WallElement wall = (WallElement) element;
//                 if (wall.blockingEntry(direction)) return true;
//             }
//         }
//         return false;
//     }
//
//     // This could be handled by robot, player, gamescreen and gameboard, unsure which is best
//     public boolean interact(SkellyRobot robot) {
//         List<IElement> elements = robot.getPosition().getContents();
//         for (IElement element : elements) {
//             if (element instanceof InteractingElement){
//                 InteractingElement interactingElement = (InteractingElement)element;
//                 interactingElement.interact(robot);
//                 return true;
//             }
//         }
//     return false;
//     }
//
// }
