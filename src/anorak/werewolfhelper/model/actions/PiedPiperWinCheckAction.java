package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.base.PlayerList;
import anorak.werewolfhelper.model.effects.EEffect;

public class PiedPiperWinCheckAction extends Action {
    public PiedPiperWinCheckAction() {
        super(EAction.PIED_PIPER_WIN_CHECK);
    }

    @Override
    public void respondToGameEvent() {
        PlayerList alivePlayers = GlobalState.getInstance().getGame().getPlayers()
                .excludeDead();

        int numEnchanted = alivePlayers.includeHasEffect(EEffect.ENCHANTED).get().size();
        int numAlive = alivePlayers.get().size();

        if (numEnchanted + 1 >= numAlive) {
            new UIDisplayRequest("PiedPiper", "Every person in the village is enchanted.").request();
            new UIDisplayRequest("PiedPiper", "Game ended - Pied Piper won").request();

            GlobalState.getInstance().getGame().endGame();
        }
    }
}