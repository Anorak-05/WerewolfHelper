package anorak.werewolfhelper.model.actions.base;

import anorak.werewolfhelper.model.structure.GamePhase;

import java.util.List;

public abstract class Action implements IGameEventAction {
    private final EAction eAction;

    protected Action(EAction eAction) {
        this.eAction = eAction;
    }

    @Override
    public final int getPriority() {
        return eAction.getPriority();
    }

    @Override
    public final List<GamePhase> getPhases() {
        return eAction.getPhases();
    }
}
