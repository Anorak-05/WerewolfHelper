package anorak.werewolfhelper.model.base;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.structure.GameStructure;

public class Effect {
    protected String name;
    protected Player player;
    protected GameStructure structure;

    public Effect(Player player, GameStructure structure) {
        this.player = player;
        this.structure = structure;
    }

    public void end() {
        structure.removeAllActions(this);
    }

    public String getName() {
        return name;
    }
}
