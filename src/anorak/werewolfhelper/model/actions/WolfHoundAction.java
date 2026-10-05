package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.controller.base.requests.UIBooleanRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.effects.EEffect;

public class WolfHoundAction extends Action {
    private final Player player;

    public WolfHoundAction(Player player) {
        super(EAction.WOLF_HOUND);

        this.player = player;
    }

    @Override
    public void respondToGameEvent() {
        boolean isWolfhoundEvil = new UIBooleanRequest("Wolfhound", "Did the wolfhound wake up together with the Werewolves?").request();

        if (isWolfhoundEvil) {
            player.addEffect(EEffect.IS_WEREWOLF);
        }
    }
}
