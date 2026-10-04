package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.structure.GamePhase;

import java.util.List;

public class TwoSistersAction implements IGameEventAction {
    private boolean wakeTonight = true;

    @Override
    public void respondToGameEvent() {
        if (wakeTonight) {
            new UIDisplayRequest("TwoSisters", "The two Sisters may commune").request();
        }
        wakeTonight = !wakeTonight;
    }

    @Override
    public int getPriority() {
        return 60;
    }

    @Override
    public List<GamePhase> getPhases() {
        return List.of(GamePhase.PRE_FIRST_NIGHT, GamePhase.PRE_NIGHT);
    }
}
