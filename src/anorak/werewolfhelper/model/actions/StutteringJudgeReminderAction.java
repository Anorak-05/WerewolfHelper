package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.structure.GamePhase;

import java.util.List;

public class StutteringJudgeReminderAction extends Action {
    public StutteringJudgeReminderAction() {
        super(EAction.STUTTERING_JUDGE_REMINDER);
    }

    @Override
    public void respondToGameEvent() {
        new UIDisplayRequest("StutteringJudge", "Remember to notice the Stuttering Judge's sign").request();
    }
}
