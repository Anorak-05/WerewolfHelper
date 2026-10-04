package anorak.werewolfhelper.model.effects;

public interface IEffect {
    void end();

    String getName();

    boolean allowAddOtherEffect(IEffect effect);

    EEffect getEffectEnum();
}
