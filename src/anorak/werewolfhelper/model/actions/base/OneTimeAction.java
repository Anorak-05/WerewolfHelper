package anorak.werewolfhelper.model.actions.base;

import anorak.werewolfhelper.model.structure.GamePhase;
import anorak.werewolfhelper.model.structure.GameStructure;

import java.util.List;

public class OneTimeAction implements IGameEventAction {
    private final IGameEventAction action;
    private final GameStructure structure;

    private OneTimeAction(GameStructure structure, IGameEventAction action) {
        this.action = action;
        this.structure = structure;

        structure.addAction(this, action);
    }

    public static OneTimeAction createAndAdd(GameStructure structure, IGameEventAction action) {
        return new OneTimeAction(structure, action);
    }

    @Override
    public void respondToGameEvent() {
        action.respondToGameEvent();
        structure.removeAllActions(this);
    }

    @Override
    public int getPriority() {
        return action.getPriority();
    }

    @Override
    public List<GamePhase> getPhases() {
        return action.getPhases();
    }
}
