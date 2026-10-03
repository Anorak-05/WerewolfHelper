package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.effects.EffectVoted;
import anorak.werewolfhelper.model.structure.GamePhase;

import java.util.List;

public class ClearVotesAction implements IGameEventAction {
    @Override
    public void respondToGameEvent() {
        GlobalState.getInstance().getGame().getPlayersByEffect(EffectVoted.class)
                .forEach(player -> player.removeEffect(EffectVoted.class));
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public List<GamePhase> getPhases() {
        return List.of(GamePhase.PRE_NIGHT);
    }
}
