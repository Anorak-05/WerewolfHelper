package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.effects.EEffect;

public class AngelActivateAction extends Action {
    private final Player player;
    private boolean firstMorning = true;

    public AngelActivateAction(Player player) {
        super(EAction.ANGEL_ACTIVATE);

        this.player = player;
    }

    @Override
    public void respondToGameEvent() {
        if (!firstMorning) return;
        firstMorning = false;

        player.addEffect(EEffect.ANGEL_ACTIVE);
    }
}