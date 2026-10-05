package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;

public class DieByRustySwordAction extends Action {
    private final Player player;

    public DieByRustySwordAction(Player player) {
        super(EAction.DIE_BY_RUSTY_SWORD);

        this.player = player;
    }

    @Override
    public void respondToGameEvent() {
        player.die();
    }
}
