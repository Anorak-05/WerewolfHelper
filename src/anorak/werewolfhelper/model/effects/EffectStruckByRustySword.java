package anorak.werewolfhelper.model.effects;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.DieByRustySwordAction;
import anorak.werewolfhelper.model.actions.base.OneTimeAction;
import anorak.werewolfhelper.model.structure.GameStructure;

public class EffectStruckByRustySword extends Effect {
    EffectStruckByRustySword(EEffect effectEnum, Player player, GameStructure structure) {
        super(effectEnum, player, structure);

        OneTimeAction.createAndAdd(structure, new DieByRustySwordAction(player));
    }
}