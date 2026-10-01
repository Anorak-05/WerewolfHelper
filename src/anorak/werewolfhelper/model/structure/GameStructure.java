package anorak.werewolfhelper.model.structure;

import anorak.werewolfhelper.model.base.IGameEventAction;

import java.util.*;

public class GameStructure implements Iterable<GameEvent> {

    private final Dictionary<GamePhase, GameEvent> events;
    private final Dictionary<Object, List<IGameEventAction>> actions;

    public GameStructure() {
        events = new Hashtable<>();
        for(GamePhase phase : GamePhase.values()) {
            events.put(phase, new GameEvent());
        }
        actions = new Hashtable<>();
    }

    public void addAction(IGameEventAction action) {
        addAction(action, action);
    }

    public void addAction(Object owner, IGameEventAction action) {
        for (GamePhase phase : action.getPhases()) {
            events.get(phase).addAction(action);
        }

        addActionToDict(owner, action);
    }

    private void addActionToDict(Object owner, IGameEventAction action) {
        if (actions.get(owner) == null) {
            actions.put(owner, new ArrayList<>(List.of(action)));
        } else {
            actions.get(owner).add(action);
        }
    }

    public void removeAllActions(Object owner) {
        if (actions.get(owner) == null) return;

        for (IGameEventAction action : actions.get(owner)) {
            for (GamePhase phase : action.getPhases()) {
                events.get(phase).removeAction(action);
            }
        }

        actions.remove(owner);
    }

    public GameEvent getEvent(GamePhase phase) {
        return events.get(phase);
    }

    @Override
    public Iterator<GameEvent> iterator() {
        return new GameStructureIterator(this);
    }
}