package anorak.werewolfhelper.model.effects;

public interface IEffect {
    void end();

    boolean allowAddOtherEffect(IEffect effect);

    EEffect getEffectEnum();

    boolean is(EEffect other);
    boolean is(Effect other);
    boolean is(EEffectType other);
}