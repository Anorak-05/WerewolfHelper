package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.structure.GamePhase;

import java.util.List;

public class TwoSistersAction extends Action {
    private boolean wakeTonight = true;

    public TwoSistersAction() {
        super(EAction.TWO_SISTERS);
    }

    @Override
    public void respondToGameEvent() {
        if (wakeTonight) {
            new UIDisplayRequest("TwoSisters", "The two Sisters may commune").request();
        }
        wakeTonight = !wakeTonight;
    }
}
