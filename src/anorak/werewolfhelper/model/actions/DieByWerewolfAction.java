package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;

public class DieByWerewolfAction extends Action {
    private final Player player;

    public DieByWerewolfAction(Player player) {
        super(EAction.DIE_BY_WEREWOLF);

        this.player = player;
    }

    @Override
    public void respondToGameEvent() {
        player.die();
    }
}
