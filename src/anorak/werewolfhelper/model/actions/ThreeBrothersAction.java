package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.structure.GamePhase;

import java.util.List;

public class ThreeBrothersAction extends Action {
    private boolean wakeTonight = true;

    public ThreeBrothersAction() {
        super(EAction.THREE_BROTHERS);
    }

    @Override
    public void respondToGameEvent() {
        if (wakeTonight) {
            new UIDisplayRequest("ThreeBrothers", "The three Brothers may commune").request();
        }
        wakeTonight = !wakeTonight;
    }
}