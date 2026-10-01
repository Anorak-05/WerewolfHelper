package anorak.werewolfhelper.model.effects;

import anorak.werewolfhelper.model.structure.GameStructure;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.base.Effect;

public class EffectInLove extends Effect {
    Player otherLover;

    public EffectInLove(Player player, GameStructure structure, Player otherLover) {
        super(player, structure);

        this.otherLover = otherLover;
    }

    @Override
    public void end() {
        super.end();

        otherLover.die();
    }
}