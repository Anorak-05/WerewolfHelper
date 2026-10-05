package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;

public class KillLynchCandidateAction extends Action {
    Player player;

    public KillLynchCandidateAction(Player player) {
        super(EAction.KILL_LYNCH_CANDIDATE);
    }

    @Override
    public void respondToGameEvent() {
        player.die();
    }
}
