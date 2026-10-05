package anorak.werewolfhelper.model.effects;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.KillLynchCandidateAction;
import anorak.werewolfhelper.model.structure.GameStructure;

public class EffectStagedForLynching extends Effect {
    EffectStagedForLynching(EEffect effectEnum, Player player, GameStructure structure) {
        super(effectEnum, player, structure);

        structure.addAction(this, new KillLynchCandidateAction(player));
    }
}
