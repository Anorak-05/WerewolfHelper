package anorak.werewolfhelper.model.effects;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.structure.GameStructure;

public class EffectNotHome extends Effect {
    EffectNotHome(EEffect effectEnum, Player player, GameStructure structure) {
        super(effectEnum, player, structure);
    }

    @Override
    public boolean allowAddOtherEffect(IEffect effect) {
        return effect.getEffectEnum() != EEffect.KILLED_BY_WEREWOLF;
    }
}