package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.effects.EffectHatedByBitterOldMan;
import anorak.werewolfhelper.model.effects.EffectKilledByWerewolf;
import anorak.werewolfhelper.model.structure.GamePhase;

import java.util.List;

public class BitterOldManWinCheckAction implements IGameEventAction {
    @Override
    public void respondToGameEvent() {
        int numHated = GlobalState.getInstance().getGame().getPlayers()
                .excludeDead()
                .includeHasEffect(EffectHatedByBitterOldMan.class)
                .get().size();

        if (numHated == 0) {
            new UIDisplayRequest("BitterOldMan", "Every person in the village that the Bitter Old Man hates is dead.").request();
            new UIDisplayRequest("BitterOldMan", "Game ended - Bitter Old Man won").request();

            GlobalState.getInstance().getGame().endGame();
        }
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public List<GamePhase> getPhases() {
        return List.of(GamePhase.PLAYER_KILLED);
    }
}
