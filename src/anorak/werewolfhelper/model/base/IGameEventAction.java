package anorak.werewolfhelper.model.base;

import anorak.werewolfhelper.model.structure.GamePhase;

import java.util.List;

public interface IGameEventAction {
    void respondToGameEvent();
    int getPriority();
    List<GamePhase> getPhases();

    default boolean isGroupAction() {
        return false;
    }
}