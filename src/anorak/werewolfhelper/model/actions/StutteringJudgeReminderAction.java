package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.structure.GamePhase;

import java.util.List;

public class StutteringJudgeReminderAction implements IGameEventAction {
    @Override
    public void respondToGameEvent() {
        new UIDisplayRequest("StutteringJudge", "Remember to notice the Stuttering Judge's sign").request();
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public List<GamePhase> getPhases() {
        return List.of(GamePhase.PRE_VOTE);
    }
}
