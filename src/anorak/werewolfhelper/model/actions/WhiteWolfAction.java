package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIBooleanRequest;
import anorak.werewolfhelper.controller.base.requests.UIPlayerRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.effects.EEffect;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.structure.GamePhase;

import java.util.List;

public class WhiteWolfAction implements IGameEventAction {
    boolean mayKillTwice = true;

    @Override
    public void respondToGameEvent() {
        if (!mayKillTwice) return;

        if (new UIBooleanRequest("WhiteWolf", "Does the White Wolf kill one of his own tonight?").request()) {
            Player toKill = new UIPlayerRequest("WhiteWolf", "The White Wolf may choose a victim")
                    .fromPlayers(
                            GlobalState.getInstance().getGame().getPlayers()
                                    .excludeDead()
                                    .includeHasEffect(EEffect.IS_WEREWOLF)
                                    .get())
                    .request();
            toKill.die();
        }

        mayKillTwice = !mayKillTwice;
    }

    @Override
    public int getPriority() {
        return 15;
    }

    @Override
    public List<GamePhase> getPhases() {
        return List.of(GamePhase.NIGHT);
    }
}
