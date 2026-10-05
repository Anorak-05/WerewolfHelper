package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.effects.EEffect;

public class AngelDeactiveAction extends Action {
    private final Player player;
    private boolean firstMorning = true;

    public AngelDeactiveAction(Player player) {
        super(EAction.ANGEL_DEACTIVATE);

        this.player = player;
    }

    @Override
    public void respondToGameEvent() {
        if (firstMorning) {
            firstMorning = false;
            return;
        }
        player.removeEffect(EEffect.ANGEL_ACTIVE);
    }
}
