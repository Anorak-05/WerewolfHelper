package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIPlayerRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.effects.EffectIsWerewolf;
import anorak.werewolfhelper.model.structure.GamePhase;

import java.util.List;

public class BigBadWolfAction implements IGameEventAction {

    private int numWolfes;
    private boolean initialized = false;
    private boolean canKillAgain = true;

    private void init() {
        numWolfes= GlobalState.getInstance().getGame().getPlayersByEffect(EffectIsWerewolf.class).size();
        initialized = true;
    }

    private boolean canKillAgain() {
        return canKillAgain && (canKillAgain = numWolfes >= GlobalState.getInstance().getGame().getPlayersByEffect(EffectIsWerewolf.class).size());
    }

    @Override
    public void respondToGameEvent() {
        if (!initialized) init();

        if (canKillAgain()) {
            Player toKill = new UIPlayerRequest("BigBadWolf", "The Big Bad Wolf chooses another victim").request();
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
