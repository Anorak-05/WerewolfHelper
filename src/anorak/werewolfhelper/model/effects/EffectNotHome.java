package anorak.werewolfhelper.model.effects;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.MistressReturnHomeAction;
import anorak.werewolfhelper.model.actions.base.OneTimeAction;
import anorak.werewolfhelper.model.structure.GameStructure;

public class EffectNotHome extends Effect {
    EffectNotHome(EEffect effectEnum, Player player, GameStructure structure) {
        super(effectEnum, player, structure);

        OneTimeAction.createAndAdd(structure, new MistressReturnHomeAction(player));
    }

    @Override
    public boolean allowAddOtherEffect(IEffect effect) {
        return effect.is(EEffect.KILLED_BY_SLEEPING_PARTNER) || !effect.is(EEffectType.KILL_EFFECT);
    }
}