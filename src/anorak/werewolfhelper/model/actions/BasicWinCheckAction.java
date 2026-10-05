package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.effects.EEffect;

public class BasicWinCheckAction extends Action {
    public BasicWinCheckAction() {
        super(EAction.BASIC_WIN_CHECK);
    }

    @Override
    public void respondToGameEvent() {
        int numAliveWerewolves = GlobalState.getInstance().getGame().getPlayers()
                .excludeDead()
                .includeHasEffect(EEffect.IS_WEREWOLF)
                .get()
                .size();
        int numAlivePlayers = GlobalState.getInstance().getGame().getPlayers().excludeDead().get().size();

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
}