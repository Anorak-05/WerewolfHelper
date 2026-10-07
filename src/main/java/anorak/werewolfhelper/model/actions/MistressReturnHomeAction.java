package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.base.PlayerList;
import anorak.werewolfhelper.model.effects.EEffect;
import anorak.werewolfhelper.model.effects.EEffectType;

public class MistressReturnHomeAction extends Action {
    private final Player player;

    public MistressReturnHomeAction(Player player) {
        super(EAction.MISTRESS_RETURN_HOME);

        this.player = player;
    }

    @Override
    public void respondToGameEvent() {
        player.removeEffect(EEffect.NOT_HOME);

//        Player sleepingWithMistress = GlobalState.getInstance().getGame().getPlayers()
//                .excludeDead()
//                .includeHasEffect(EEffect.SLEEPING_WITH_MISTRESS)
//                .get().getFirst();
//
//        if (sleepingWithMistress.hasEffect(EEffectType.KILL_EFFECT)
//                || sleepingWithMistress.hasEffect(EEffect.IS_WEREWOLF)) {
//            player.addEffect(EEffect.KILLED_BY_SLEEPING_PARTNER);
//      }
    }
}