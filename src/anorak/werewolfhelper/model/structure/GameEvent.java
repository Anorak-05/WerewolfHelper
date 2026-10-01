package anorak.werewolfhelper.model.structure;

import anorak.werewolfhelper.model.base.IGameEventAction;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GameEvent {

    private List<IGameEventAction> gameEventActions;
    private boolean sorted;

    public GameEvent() {
        gameEventActions = new ArrayList<>();
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
        int i = 0;

        while (i < gameEventActions.size()) {
            action = gameEventActions.get(i);
            if (lastActionClass == null || !lastActionClass.isInstance(action))
                action.respondToGameEvent();
            lastActionClass = action.getClass();
            i++;
        }
    }
}