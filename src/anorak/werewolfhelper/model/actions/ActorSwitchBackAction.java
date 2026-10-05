package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.base.ERole;
import anorak.werewolfhelper.model.structure.GameStructure;

public class ActorSwitchBackAction extends Action {
    GameStructure structure;
    Player player;

    public ActorSwitchBackAction(Player player, GameStructure structure) {
        super(EAction.ACTOR_SWITCH_BACK);

        this.structure = structure;
        this.player = player;
    }

    @Override
    public void respondToGameEvent() {
        player.changeRole(ERole.ACTOR.create());
    }
}
