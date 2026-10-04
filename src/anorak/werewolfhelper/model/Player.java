package anorak.werewolfhelper.model;

import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.effects.EEffect;
import anorak.werewolfhelper.model.effects.Effect;
import anorak.werewolfhelper.model.effects.IEffect;
import anorak.werewolfhelper.model.base.Role;
import anorak.werewolfhelper.model.structure.GamePhase;
import anorak.werewolfhelper.model.structure.GameStructure;

import java.util.HashMap;
import java.util.Map;

public class Player {
    private Role role;
    private Map<EEffect, IEffect> effects;
    private final String name;
    private boolean alive;

    private GameStructure structure;

    public Player(Role role, String name) {
        this.role = role;
        this.name = name;
        alive = true;

        effects = new HashMap<>();
    }

    public void setup(GameStructure gameStructure) {
        role.setup(this, gameStructure);
        this.structure = gameStructure;
    }

    public void vote() {

    }

    public IEffect addEffect(EEffect effectEnum) {
        if (effects.containsKey(effectEnum))
            return null;

        IEffect effect = effectEnum.construct(this, structure);

        if (!effects.entrySet().stream().allMatch(e -> e.getValue().allowAddOtherEffect(effect)))
            return null;

        return effects.put(effect.getEffectEnum(), effect);
    }

    public IEffect getEffect(EEffect effectEnum) {
        return effects.get(effectEnum);
    }

    public <T extends Effect> T getEffect(EEffect effectEnum, Class<T> type) {
        return type.cast(effects.get(effectEnum));
    }

    public boolean hasEffect(EEffect effectEnum) {
        return effects.containsKey(effectEnum);
    }

    public boolean removeEffect(EEffect effectEnum) {
        IEffect removed = effects.remove(effectEnum);
        if (removed != null) {
            removed.end();
            return true;
        } else return false;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public void changeRole(Role newRole) {
        structure.removeAllActions(role);
        role = newRole;
        role.setup(this, structure);
    }

    @Override
    public String toString() {
        return name;
    }

    // The order here is really tricky to get right for all Effects and Actions to work properly
    // Signs of a badly designed system? Perhaps.
    public void die() {
        if (!alive) return;
        role.end();

        for (IEffect effect : effects.values()) {
            effect.end();
        }
        effects.clear();

        structure.removeAllActions(this);

        new UIDisplayRequest(role.getName(), name + " died.").request();

        alive = false;

        structure.getEvent(GamePhase.PLAYER_KILLED).trigger();
    }

    public boolean isAlive() {
        return alive;
    }
}