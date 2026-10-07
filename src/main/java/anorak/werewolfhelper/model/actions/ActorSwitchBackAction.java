package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.base.ERole;

public class ActorSwitchBackAction extends Action {
    private final Player player;

    public ActorSwitchBackAction(Player player) {
        super(EAction.ACTOR_SWITCH_BACK);

        this.player = player;
    }

    @Override
    public void respondToGameEvent() {
        player.changeRole(ERole.ACTOR.create());
    }
}
