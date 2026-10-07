package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;

public class StutteringJudgeChooseSignAction extends Action {
    public StutteringJudgeChooseSignAction() {
        super(EAction.STUTTERING_JUDGE_CHOOSE_SIGN);
    }

    @Override
    public void respondToGameEvent() {
        new UIDisplayRequest("StutteringJudge", "Choose a sign for the Stuttering Judge").request();
    }
}
