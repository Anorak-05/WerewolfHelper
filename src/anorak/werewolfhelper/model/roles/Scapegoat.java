package anorak.werewolfhelper.model.roles;

import anorak.werewolfhelper.model.structure.GameStructure;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.ScapegoatAction;
import anorak.werewolfhelper.model.base.Role;

public class Scapegoat extends Role {

    public Scapegoat(String name) {
        super(name);
    }

    @Override
    public void setup(Player player, GameStructure structure) {
        super.setup(player, structure);

        structure.addAction(this, new ScapegoatAction(player, structure));
    }
}
