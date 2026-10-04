package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.effects.EEffect;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.structure.GamePhase;

import java.util.List;

public class KillRemainingLoverAction implements IGameEventAction {
    private final Player killedLovedOne;

    public KillRemainingLoverAction(Player killedLovedOne) {
        this.killedLovedOne = killedLovedOne;
    }

    @Override
    public void respondToGameEvent() {
        List<Player> lovers = GlobalState.getInstance().getGame().getPlayers()
                .excludeDead()
                .includeHasEffect(EEffect.IN_LOVE)
                .get();

        for(Player lover : lovers) {
            new UIDisplayRequest("Amor", "Due to their undying love for " + killedLovedOne + ", " + lover + " will also die.").request();
            lover.die();
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