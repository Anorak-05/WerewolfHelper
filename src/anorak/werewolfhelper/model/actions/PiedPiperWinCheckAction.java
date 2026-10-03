package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.controller.base.requests.UIPlayerRequest;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.base.PlayerList;
import anorak.werewolfhelper.model.effects.EffectEnchanted;
import anorak.werewolfhelper.model.structure.GamePhase;

import java.util.List;

public class PiedPiperWinCheckAction implements IGameEventAction {

    @Override
    public void respondToGameEvent() {
        PlayerList alivePlayers = GlobalState.getInstance().getGame().getPlayers()
                .excludeDead();

        int numEnchanted = alivePlayers.includeHasEffect(EffectEnchanted.class).get().size();
        int numAlive = alivePlayers.get().size();

        if (numEnchanted + 1 >= numAlive) {
            new UIDisplayRequest("PiedPiper", "Every person in the village is enchanted.").request();
            new UIDisplayRequest("PiedPiper", "Game ended - Pied Piper won").request();

            GlobalState.getInstance().getGame().endGame();
        }
    }

    @Override
    public int getPriority() {
        return 10200;
    }

    @Override
    public List<GamePhase> getPhases() {
        return List.of(GamePhase.FIRST_NIGHT, GamePhase.NIGHT, GamePhase.PLAYER_KILLED);
    }
}