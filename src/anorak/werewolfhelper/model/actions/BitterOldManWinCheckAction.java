package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.effects.EEffect;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.structure.GamePhase;

import java.util.List;

public class BitterOldManWinCheckAction extends Action {
    public BitterOldManWinCheckAction() {
        super(EAction.BITTER_OLD_MAN_WIN_CHECK);
    }

    @Override
    public void respondToGameEvent() {
        int numHated = GlobalState.getInstance().getGame().getPlayers()
                .excludeDead()
                .includeHasEffect(EEffect.HATED_BY_BITTER_OLD_MAN)
                .get().size();

        if (numHated == 0) {
            new UIDisplayRequest("BitterOldMan", "Every person in the village that the Bitter Old Man hates is dead.").request();
            new UIDisplayRequest("BitterOldMan", "Game ended - Bitter Old Man won").request();

            GlobalState.getInstance().getGame().endGame();
        }
    }
}
