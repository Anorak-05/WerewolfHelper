package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIBooleanRequest;
import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.structure.GamePhase;
import anorak.werewolfhelper.model.structure.GameStructure;

import java.util.List;

public class StutteringJudgeAction implements IGameEventAction {
    private GameStructure structure;

    private boolean usedAction = false;

    public StutteringJudgeAction(GameStructure structure) {
        this.structure = structure;

        structure.addAction(this, new StutteringJudgeReminderAction());
    }

    @Override
    public void respondToGameEvent() {
        if (usedAction) return;

        if (new UIBooleanRequest("StutteringJudge", "Did the Stuttering Judge request another Vote?").request()) {
            new UIDisplayRequest("StutteringJudge", "The Stuttering Judge requests an immediate Vote").request();
            GlobalState.getInstance().getGame().OverrideGamePhase(GamePhase.VOTE);
            usedAction = true;
            structure.removeAllActions(this);
        }
    }

    @Override
    public int getPriority() {
        return 2000;
    }

    @Override
    public List<GamePhase> getPhases() {
        return List.of(GamePhase.POST_VOTE);
    }
}
