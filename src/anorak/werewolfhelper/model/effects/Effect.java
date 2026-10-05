package anorak.werewolfhelper.model.effects;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.structure.GameStructure;

public class Effect implements IEffect {
    private final EEffect effectEnum;

    protected String name;
    protected Player player;
    protected GameStructure structure;

    Effect(EEffect effectEnum, Player player, GameStructure structure) {
        this.effectEnum = effectEnum;

        this.player = player;
        this.structure = structure;
    }

    @Override
    public void end() {
        structure.removeAllActions(this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean allowAddOtherEffect(IEffect effect) { return true; }

    public EEffect getEffectEnum() {
        return effectEnum;
    }

    @Override
    public boolean is(EEffect other) {
        return other == effectEnum;
    }

    @Override
    public boolean is(Effect other) {
        return other.getEffectEnum() == effectEnum;
    }

    @Override
    public boolean is(EEffectType other) {
        return other == effectEnum.getType();
    }
}