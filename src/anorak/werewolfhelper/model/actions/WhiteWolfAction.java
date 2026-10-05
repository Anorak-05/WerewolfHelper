package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIBooleanRequest;
import anorak.werewolfhelper.controller.base.requests.UIPlayerRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.effects.EEffect;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.structure.GamePhase;

import java.util.List;

public class WhiteWolfAction extends Action {
    boolean mayKillTwice = true;

    public WhiteWolfAction() {
        super(EAction.WHITE_WOLF);
    }

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
}
