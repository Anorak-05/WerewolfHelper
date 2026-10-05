package anorak.werewolfhelper.model.structure;

import anorak.werewolfhelper.model.base.game_event_action_list.GameEventActionList;
import anorak.werewolfhelper.model.base.IGameEventAction;

import java.util.ArrayList;

public class GameEvent {

    private final GameEventActionList gameEventActions;

    private boolean stopIterating = false;

    public GameEvent() {
        gameEventActions = new GameEventActionList(new ArrayList<>());
    }

    public void addAction(IGameEventAction action) {
        gameEventActions.add(action);
    }

    public void removeAction(IGameEventAction action) {
        gameEventActions.remove(action);
    }

    public void interruptTrigger() {
        stopIterating = true;
    }

    public void trigger() {
        if (gameEventActions.isEmpty())
            return;

        Class<? extends IGameEventAction> lastActionClass = null;

        for (IGameEventAction action : gameEventActions) {
            if (stopIterating) break;
            if (!action.isGroupAction() || lastActionClass == null || !lastActionClass.isInstance(action))
                action.respondToGameEvent();
            lastActionClass = action.getClass();
        }
        stopIterating = false;
    }
}