package anorak.werewolfhelper.model.roles;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.ThiefAction;
import anorak.werewolfhelper.model.base.Role;
import anorak.werewolfhelper.model.structure.GameStructure;

public class Thief extends Role {
    public Thief(String name) {
        super(name);
    }

    @Override
    public void setup(Player player, GameStructure structure) {
        super.setup(player, structure);

        structure.addAction(this, new ThiefAction(player));
    }
}