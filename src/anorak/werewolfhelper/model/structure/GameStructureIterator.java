package anorak.werewolfhelper.model.structure;

import java.util.Iterator;

public class GameStructureIterator implements Iterator<GameEvent> {

    private final GameStructure structure;
    private GamePhase currentPhase;

    public GameStructureIterator(GameStructure structure) {
        this.structure = structure;
        this.currentPhase = GamePhase.getFirst();
    }
    
    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public GameEvent next() {
        GameEvent event = structure.getEvent(currentPhase);
        currentPhase = currentPhase.next();
        return event;
    }
}
