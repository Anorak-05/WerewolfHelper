package anorak.werewolfhelper.model;

import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.base.Effect;
import anorak.werewolfhelper.model.base.Role;
import anorak.werewolfhelper.model.structure.GamePhase;
import anorak.werewolfhelper.model.structure.GameStructure;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private Role role;
    private List<Effect> effects;
    private final String name;
    private boolean alive;

    private GameStructure structure;

    public Player(Role role, String name) {
        this.role = role;
        this.name = name;
        alive = true;

        effects = new ArrayList<>();
    }

    public void setup(GameStructure gameStructure) {
        role.setup(this, gameStructure);
        this.structure = gameStructure;
    }

    public void vote() {

    }

    public void addEffect(Effect effect) {
        if (effects.stream().anyMatch(e -> e.getClass() == effect.getClass()))
            return;
        effects.add(effect);
    }

    public <T extends Effect> T getEffect(Class<T> effectClass) {
        return (T) effects.stream().filter(effectClass::isInstance).findFirst().orElse(null);
    }

    public boolean hasEffect(Class<? extends Effect> effectClass) {
        return effects.stream().anyMatch(effectClass::isInstance);
    }

    public boolean removeEffect(Class<? extends Effect> effectClass) {
        Effect toRemove = effects.stream().filter(effectClass::isInstance).findFirst().orElse(null);
        if (toRemove != null) {
            toRemove.end();
            effects.remove(toRemove);
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

    public void die() {
        role.end();

        for (Effect effect : effects) {
            effect.end();
        }
        effects = new ArrayList<>();

        structure.removeAllActions(this);

        new UIDisplayRequest(role.getName(), name + " died.").request();

        structure.getEvent(GamePhase.PLAYER_KILLED).trigger();

        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }
}