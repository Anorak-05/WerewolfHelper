package anorak.werewolfhelper.model.effects;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.structure.GameStructure;

public class EffectSleepingWithMistress extends Effect{
    private Player mistress;

    EffectSleepingWithMistress(EEffect effectEnum, Player player, GameStructure structure) {
        super(effectEnum, player, structure);
    }

    public void setMistress(Player mistress) {
        this.mistress = mistress;
    }

    @Override
    public boolean allowAddOtherEffect(IEffect effect) {
        if (mistress == null)
            throw new IllegalStateException("Mistress Player was not set on EffectSleepingWithMistress");

        if (effect.is(EEffect.IS_WEREWOLF)) {
            mistress.addEffectGuaranteed(EEffect.KILLED_BY_WEREWOLF);
        } else if (effect.is(EEffect.KILLED_BY_WEREWOLF) || effect.is(EEffect.KILLED_BY_WITCH_POISON)) {
            mistress.addEffectGuaranteed(effect.getEffectEnum());
        }

        return true;
    }
}