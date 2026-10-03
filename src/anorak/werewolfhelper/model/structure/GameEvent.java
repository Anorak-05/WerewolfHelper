package anorak.werewolfhelper.model.structure;

import anorak.werewolfhelper.model.base.delete_while_traverse_list.DeleteWhileTraverseList;
import anorak.werewolfhelper.model.base.IGameEventAction;

import java.util.ArrayList;
import java.util.Comparator;

public class GameEvent {

    private DeleteWhileTraverseList<IGameEventAction> gameEventActions;
    private boolean sorted;

    private boolean stopIterating = false;

    public GameEvent() {
        gameEventActions = new DeleteWhileTraverseList<>(new ArrayList<>());
        sorted = true;
    }

    public void addAction(IGameEventAction action) {
        gameEventActions.add(action);
        sorted = false;
    }

    public void removeAction(IGameEventAction action) {
        gameEventActions.remove(action);
    }

    private void ensureSorted() {
        if (sorted) return;
        gameEventActions.sort(Comparator.comparingInt(IGameEventAction::getPriority));
        sorted = true;
    }

    public void interruptTrigger() {
        stopIterating = true;
    }

    public void trigger() {
        if (gameEventActions.isEmpty())
            return;

        ensureSorted();

        Class<? extends IGameEventAction> lastActionClass = null;

        for (IGameEventAction action : gameEventActions) {
            if (stopIterating) break;
            if (lastActionClass == null || !lastActionClass.isInstance(action))
                action.respondToGameEvent();
            lastActionClass = action.getClass();
        }
        stopIterating = false;

    }
}