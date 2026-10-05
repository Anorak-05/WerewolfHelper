package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.effects.EEffect;

public class ClearVotesAction extends Action {
    public ClearVotesAction() {
        super(EAction.CLEAR_VOTES);
    }

    @Override
    public void respondToGameEvent() {
        GlobalState.getInstance().getGame().getPlayers()
                .excludeDead()
                .includeHasEffect(EEffect.VOTED)
                .get()
                .forEach(player -> player.removeEffect(EEffect.VOTED));
    }
}
