package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.structure.GamePhase;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.effects.EffectIsWerewolf;

import java.util.List;

public class BasicWinCheckAction implements IGameEventAction {
    @Override
    public void respondToGameEvent() {
        int numAliveWerewolves = GlobalState.getInstance().getGame().getPlayersByEffect(EffectIsWerewolf.class).size();
        int numAlivePlayers = GlobalState.getInstance().getGame().getAlivePlayers().size();

        // did good win?
        if (numAliveWerewolves == 0) {
            // win for good TODO
           new UIDisplayRequest("endGameGood", "Game ended - GOOD won").request();
            GlobalState.getInstance().getGame().endGame();
        }

        // did evil win?
        if (numAlivePlayers - numAliveWerewolves <= 1) {
            // win for evil TODO
           new UIDisplayRequest("endGameEvil", "Game ended - EVIL won").request();
            GlobalState.getInstance().getGame().endGame();
        }
    }

    @Override
    public int getPriority() {
        return 10;
    }

    @Override
    public List<GamePhase> getPhases() {
        return List.of(GamePhase.PLAYER_KILLED);
    }
}