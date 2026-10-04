package anorak.werewolfhelper.model.effects;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.structure.GameStructure;

@FunctionalInterface
interface EffectConstructor {
    IEffect construct(EEffect effectEnum, Player player, GameStructure gameStructure);
}

public enum EEffect {
    ANGEL_ACTIVE(EffectAngelActive::new),
    ENCHANTED(EffectEnchanted::new),
    HATED_BY_BITTER_OLD_MAN(EffectHatedByBitterOldMan::new),
    IN_LOVE(EffectInLove::new),
    IS_ACTOR(EffectIsActor::new),
    IS_CAPTAIN(EffectIsCaptain::new),
    IS_WEREWOLF(EffectIsWerewolf::new),
    KILLED_BY_WEREWOLF(EffectKilledByWerewolf::new),
    NOT_HOME(EffectNotHome::new),
    ROLE_MODEL(EffectRoleModel::new),
    SAVED(EffectSaved::new),
    STAGED_FOR_LYNCHING(EffectStagedForLynching::new),
    STRUCK_BY_RUSTY_SWORD(EffectStruckByRustySword::new),
    VOTED(EffectVoted::new);

    private final EffectConstructor constructor;

    EEffect(EffectConstructor constructor) {
        this.constructor = constructor;
    }

    public IEffect construct(Player player, GameStructure structure) {
        return constructor.construct(this, player, structure);
    }
}