package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.structure.GamePhase;

import java.util.List;

public class StutteringJudgeChooseSignAction implements IGameEventAction {
    @Override
    public void respondToGameEvent() {
        new UIDisplayRequest("StutteringJudge", "Choose a sign for the Stuttering Judge").request();
    }

    @Override
    public int getPriority() {
        return 50;
    }

    @Override
    public List<GamePhase> getPhases() {
        return List.of(GamePhase.PRE_FIRST_NIGHT);
    }
}
