package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.effects.EEffectType;

import java.util.List;

public class ExecuteDeathsOfNightAction extends Action {
    public ExecuteDeathsOfNightAction() {
        super(EAction.EXECUTE_DEATHS_OF_NIGHT);
    }

    @Override
    public void respondToGameEvent() {
        List<Player> toDie = GlobalState.getInstance().getGame().getPlayers()
                .excludeDead()
                .includeHasEffect(EEffectType.KILL_EFFECT)
                .get();

        if (toDie.isEmpty()) {
            new UIDisplayRequest("Game", "No one died tonight").request();
        } else {
            new UIDisplayRequest("Game", "These are the deaths of the night").request();
            toDie.forEach(Player::die);
        }
    }
}
