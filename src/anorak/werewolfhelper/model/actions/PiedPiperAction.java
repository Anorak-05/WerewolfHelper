package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.controller.base.requests.UIMultiplePlayersRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.effects.EEffect;

import java.util.List;

public class PiedPiperAction extends Action {
    public PiedPiperAction() {
        super(EAction.PIED_PIPER);
    }

    @Override
    public void respondToGameEvent() {
        List<Player> notYetEnchanted = GlobalState.getInstance().getGame().getPlayers()
                .excludeDead()
                .excludeHasEffect(EEffect.ENCHANTED)
                .get();
        List<Player> toEnchant = new UIMultiplePlayersRequest("PiedPiper", "Who does the Pied Piper enchant tonight?")
                .fromPlayers(notYetEnchanted)
                .setMin(2)
                .setMax(2)
                .request();

        new UIDisplayRequest("PiedPiper", "Touch the enchanted players " + toEnchant).request();

        toEnchant.forEach(p -> p.addEffect(EEffect.ENCHANTED));
    }
}
