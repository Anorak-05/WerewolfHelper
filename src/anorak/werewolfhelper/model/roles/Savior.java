package anorak.werewolfhelper.model.roles;

import anorak.werewolfhelper.model.structure.GameStructure;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.SaviorAction;
import anorak.werewolfhelper.model.base.Role;

public class Savior extends Role {

    public Savior(String name) {
        super(name);
    }

    @Override
    public void setup(Player player, GameStructure structure) {
        super.setup(player, structure);

        structure.addAction(this, new SaviorAction(structure));
    }
}
