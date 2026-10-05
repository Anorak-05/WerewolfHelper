package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIBooleanRequest;
import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.structure.GamePhase;
import anorak.werewolfhelper.model.structure.GameStructure;

public class StutteringJudgeAction extends Action {
    private final GameStructure structure;

    private boolean usedAction = false;

    public StutteringJudgeAction(GameStructure structure) {
        super(EAction.STUTTERING_JUDGE);

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
}
