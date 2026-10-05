package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.structure.GamePhase;

import java.util.List;

public class WhiteWolfWinCheckAction extends Action {
    public WhiteWolfWinCheckAction() {
        super(EAction.WHITE_WOLF_WIN_CHECK);
    }

    @Override
    public void respondToGameEvent() {
        int numAlivePlayers = GlobalState.getInstance().getGame().getPlayers().excludeDead().get().size();

        if (numAlivePlayers == 1) {
            new UIDisplayRequest("WhiteWolf", "Game ended - White Wolf won").request();
            GlobalState.getInstance().getGame().endGame();
        }
    }
}
