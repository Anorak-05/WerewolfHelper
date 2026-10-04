package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIPlayerRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.effects.EEffect;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.structure.GamePhase;

import java.util.List;

public class BigBadWolfAction implements IGameEventAction {

    private int numWolfes;
    private boolean canKillAgain = true;

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

    @Override
    public int getPriority() {
        return 30;
    }

    @Override
    public List<GamePhase> getPhases() {
        return List.of(GamePhase.FIRST_NIGHT, GamePhase.NIGHT);
    }
}
