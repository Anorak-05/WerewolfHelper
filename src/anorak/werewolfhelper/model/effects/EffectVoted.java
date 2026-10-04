package anorak.werewolfhelper.model.effects;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.structure.GameStructure;

public class EffectVoted extends Effect {
    private int votes;

    EffectVoted(EEffect effectEnum, Player player, GameStructure structure) {
        super(effectEnum, player, structure);
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public int getVotes() {
        return votes;
    }
}