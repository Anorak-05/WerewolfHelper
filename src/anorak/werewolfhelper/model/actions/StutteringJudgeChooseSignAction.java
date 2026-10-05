package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.structure.GamePhase;

import java.util.List;

public class StutteringJudgeChooseSignAction extends Action {
    public StutteringJudgeChooseSignAction() {
        super(EAction.STUTTERING_JUDGE_CHOOSE_SIGN);
    }

    @Override
    public void respondToGameEvent() {
        new UIDisplayRequest("StutteringJudge", "Choose a sign for the Stuttering Judge").request();
    }
}
