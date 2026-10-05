package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIPlayerRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.effects.EEffect;
import anorak.werewolfhelper.model.effects.EEffectType;
import anorak.werewolfhelper.model.effects.EffectSleepingWithMistress;
import anorak.werewolfhelper.model.effects.IEffect;

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
        previouslySleptWith = sleepWith;

        if (sleepWith != player) {
            if (sleepWith.hasEffect(EEffect.IS_WEREWOLF)) {
                player.addEffect(EEffect.KILLED_BY_WEREWOLF);
            }
            player.addEffect(EEffect.NOT_HOME);

            previouslySleptWith.addEffect(EEffect.SLEEPING_WITH_MISTRESS);
            EffectSleepingWithMistress effect = (EffectSleepingWithMistress) previouslySleptWith.getEffect(EEffect.SLEEPING_WITH_MISTRESS);
            effect.setMistress(player);
        }
    }
}