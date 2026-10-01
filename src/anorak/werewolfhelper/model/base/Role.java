package anorak.werewolfhelper.model.base;

import anorak.werewolfhelper.model.structure.GameStructure;
import anorak.werewolfhelper.model.Player;

public abstract class Role {
    protected GameStructure structure;
    protected final String name;
    protected Player player;

    public Role(String name) {
        this.name = name;
    }

    public void setup(Player player, GameStructure structure) {
        this.structure = structure;
        this.player = player;
    }

    public void end() {
        structure.removeAllActions(this);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}