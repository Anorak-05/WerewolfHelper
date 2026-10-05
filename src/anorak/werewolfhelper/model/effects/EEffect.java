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
    KILLED_BY_RUSTY_SWORD(EffectKilledByRustySword::new, EEffectType.KILL_EFFECT),
    KILLED_BY_SLEEPING_PARTNER(EffectKilledBySleepingPartner::new, EEffectType.KILL_EFFECT),
    KILLED_BY_WEREWOLF(EffectKilledByWerewolf::new, EEffectType.KILL_EFFECT),
    KILLED_BY_WITCH_POISON(EffectKilledByWitchPoison::new, EEffectType.KILL_EFFECT),
    NOT_HOME(EffectNotHome::new),
    ROLE_MODEL(EffectRoleModel::new),
    SAVED(EffectSaved::new),
    SLEEPING_WITH_MISTRESS(EffectSleepingWithMistress::new),
    STAGED_FOR_LYNCHING(EffectStagedForLynching::new),
    STRUCK_BY_RUSTY_SWORD(EffectStruckByRustySword::new),
    VOTED(EffectVoted::new);

    private final EffectConstructor constructor;
    private final EEffectType type;

    EEffect(EffectConstructor constructor) {
        this.constructor = constructor;
        this.type = EEffectType.DEFAULT;
    }

    EEffect(EffectConstructor constructor, EEffectType type) {
        this.constructor = constructor;
        this.type = type;
    }

    public IEffect construct(Player player, GameStructure structure) {
        return constructor.construct(this, player, structure);
    }

    public EEffectType getType() {
        return type;
    }
}