package anorak.werewolfhelper.model.effects;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.base.Effect;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.structure.GamePhase;
import anorak.werewolfhelper.model.structure.GameStructure;

import java.util.List;

public class EffectVoted extends Effect {
    private final int votes;

    public EffectVoted(Player player, GameStructure structure, int votes) {
        super(player, structure);
        name = "Effect Voted";

        this.votes = votes;

        structure.addAction(this, new IGameEventAction() {
            @Override
            public void respondToGameEvent() {
                end();
            }

            @Override
            public int getPriority() {
                return Integer.MAX_VALUE;
            }

            @Override
            public List<GamePhase> getPhases() {
                return List.of(GamePhase.POST_VOTE);
            }
        });
    }

    public int getVotes() {
        return votes;
    }
}