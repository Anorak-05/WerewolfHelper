package anorak.werewolfhelper.model.effects;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.EnchantedAction;
import anorak.werewolfhelper.model.structure.GameStructure;

public class EffectEnchanted extends Effect {
    EffectEnchanted(EEffect effectEnum, Player player, GameStructure structure) {
        super(effectEnum, player, structure);

        structure.addAction(player, new EnchantedAction());
    }
}
