package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;

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
