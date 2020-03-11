package biblioteket.roborally.elements;

import biblioteket.roborally.board.Direction;

public interface IElement {
    //TODO
    // Hvis dette er nødvendig for å stoppe lasere (Tror ikke det er det), så burde det
    // få nytt navn imo. Er kun vegger som stopper roboter så vidt jeg vet?
    /**
     * Some items in the game does not allow a robot to pass through or stand
     * on the same tile as them, therefore we need a way to check whether an
     * immovable objects meets the unstoppable robot.
     *
     * @return true if immovable, false otherwise.
     */
    default boolean immovable(){ return false; }

    boolean blockingExit(Direction direction);

    boolean blockingEntry(Direction direction);
}
