package biblioteket.roborally.elements;

// Maybe save x,y position?
public class RespawnPointElement implements IElement {

    private final int point;

    public RespawnPointElement(int point){
        this.point = point;
    }

    @Override
    public String toString(){
        return "Respawn";
    }

}
