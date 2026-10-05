package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIPlayerRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.effects.EEffect;

import java.util.List;

public class SaviorAction extends Action {
    private Player lastHealed;

    public SaviorAction() {
        super(EAction.SAVIOR);
    }

    @Override
    public void respondToGameEvent() {
        List<Player> availableToHeal = GlobalState.getInstance().getGame().getPlayers()
                .excludeDead()
                .excludePlayer(lastHealed)
                .get();

        Player toHeal = new UIPlayerRequest("Healer",
                "The Healer chooses who to protect from Werewolves this night")
                .fromPlayers(availableToHeal)
                .request();
        toHeal.addEffect(EEffect.SAVED);
        lastHealed = toHeal;
    }
}
