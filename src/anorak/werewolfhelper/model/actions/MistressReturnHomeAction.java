package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.effects.EEffect;

public class MistressReturnHomeAction extends Action {
    Player player;

    public MistressReturnHomeAction(Player player) {
        super(EAction.MISTRESS_RETURN_HOME);

        this.player = player;
    }

    @Override
    public void respondToGameEvent() {
        player.removeEffect(EEffect.NOT_HOME);
    }
}