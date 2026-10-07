package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.effects.EEffect;

import java.util.List;

public class KillRemainingLoverAction extends Action {
    private final Player killedLovedOne;

    public KillRemainingLoverAction(Player killedLovedOne) {
        super(EAction.KILL_REMAINING_LOVER);

        this.killedLovedOne = killedLovedOne;
    }

    @Override
    public void respondToGameEvent() {
        List<Player> lovers = GlobalState.getInstance().getGame().getPlayers()
                .excludeDead()
                .includeHasEffect(EEffect.IN_LOVE)
                .get();

        for(Player lover : lovers) {
            new UIDisplayRequest("Amor", "Due to their undying love for " + killedLovedOne + ", " + lover + " will also die.").request();
            lover.die();
        }
    }
}