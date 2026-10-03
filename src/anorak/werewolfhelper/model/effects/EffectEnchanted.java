package anorak.werewolfhelper.model.effects;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.EnchantedAction;
import anorak.werewolfhelper.model.base.Effect;
import anorak.werewolfhelper.model.structure.GameStructure;

public class EffectEnchanted extends Effect {
    public EffectEnchanted(Player player, GameStructure structure) {
        super(player, structure);

        structure.addAction(player, new EnchantedAction());
    }
}
