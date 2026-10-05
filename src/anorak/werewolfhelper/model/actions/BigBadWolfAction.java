package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIPlayerRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.effects.EEffect;

public class BigBadWolfAction extends Action {

    private int numWolfes;
    private boolean canKillAgain = true;

    public BigBadWolfAction() {
        super(EAction.BIG_BAD_WOLF);
    }

    private boolean canKillAgain() {
        return canKillAgain && (canKillAgain = numWolfes >= GlobalState.getInstance().getGame().getPlayers()
                .excludeDead()
                .includeHasEffect(EEffect.IS_WEREWOLF)
                .get().size());
    }

    @Override
    public void respondToGameEvent() {
        int currentNumWolfes = GlobalState.getInstance().getGame().getPlayers()
                .excludeDead()
                .includeHasEffect(EEffect.IS_WEREWOLF)
                .get().size();
        numWolfes = Math.max(numWolfes, currentNumWolfes);

        if (canKillAgain()) {
            Player toKill = new UIPlayerRequest("BigBadWolf", "The Big Bad Wolf chooses another victim")
                    .fromPlayers(
                            GlobalState.getInstance().getGame().getPlayers()
                                    .excludeDead()
                                    .excludeHasEffect(EEffect.IS_WEREWOLF)
                                    .get()
                    )
                    .request();
            toKill.die();
        }
    }
}
