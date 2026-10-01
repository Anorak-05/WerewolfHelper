package anorak.werewolfhelper.model.roles;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.TwoSistersAction;
import anorak.werewolfhelper.model.base.Role;
import anorak.werewolfhelper.model.structure.GameStructure;

public class TwoSisters extends Role {

    public TwoSisters(String name) {
        super(name);
    }

    @Override
    public void setup(Player player, GameStructure structure) {
        super.setup(player, structure);

        structure.addAction(this, new TwoSistersAction());
    }
}