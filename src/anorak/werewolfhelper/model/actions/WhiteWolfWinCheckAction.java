package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.structure.GamePhase;

import java.util.List;

public class WhiteWolfWinCheckAction implements IGameEventAction {
    @Override
    public void respondToGameEvent() {
        int numAlivePlayers = GlobalState.getInstance().getGame().getAlivePlayers().size();

        if (numAlivePlayers == 1) {
            new UIDisplayRequest("endGameGood", "Game ended - GOOD won").request();
            GlobalState.getInstance().getGame().endGame();
        }
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public List<GamePhase> getPhases() {
        return List.of(GamePhase.PLAYER_KILLED);
    }
}
