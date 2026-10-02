package anorak.werewolfhelper.model.structure;

import anorak.werewolfhelper.model.base.customlist.DeleteWhileTraverseList;
import anorak.werewolfhelper.model.base.IGameEventAction;

import java.util.ArrayList;
import java.util.Comparator;

public class GameEvent {

    private DeleteWhileTraverseList<IGameEventAction> gameEventActions;
    private boolean sorted;

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

    public void trigger() {
        if (gameEventActions.isEmpty())
            return;

        ensureSorted();

        Class<? extends IGameEventAction> lastActionClass = null;
        IGameEventAction action;

        for (int index = 0; index < gameEventActions.size(); index++) {
            action = gameEventActions.get(index);

            if (lastActionClass == null || !lastActionClass.isInstance(action))
                action.respondToGameEvent();
            lastActionClass = action.getClass();
        }

        gameEventActions.compress();
    }
}