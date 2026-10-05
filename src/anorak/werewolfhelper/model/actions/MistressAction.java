package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIPlayerRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.effects.EEffect;

public class MistressAction extends Action {
    private final Player player;

    private Player previouslySleptWith;

    public MistressAction(Player player) {
        super(EAction.MISTRESS);

        this.player = player;
    }

    @Override
    public void respondToGameEvent() {
        Player sleepWith = new UIPlayerRequest("Mistress", "Where does the Mistress sleep tonight?")
                .fromPlayers(GlobalState.getInstance().getGame().getPlayers()
                        .excludeDead()
                        .excludePlayer(previouslySleptWith)
                        .get())
                .request();

        if (sleepWith.hasEffect(EEffect.IS_WEREWOLF)) {
            player.addEffect(EEffect.KILLED_BY_WEREWOLF);
        }

        if (sleepWith != player) {
            player.addEffect(EEffect.NOT_HOME);
        }
    }
}
