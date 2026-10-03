package anorak.werewolfhelper.model.roles;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.PiedPiperAction;
import anorak.werewolfhelper.model.actions.PiedPiperWinCheckAction;
import anorak.werewolfhelper.model.base.Role;
import anorak.werewolfhelper.model.structure.GameStructure;

public class PiedPiper extends Role {

    public PiedPiper(String name) {
        super(name);
    }

    @Override
    public void setup(Player player, GameStructure structure) {
        super.setup(player, structure);

        structure.addAction(this, new PiedPiperAction(structure));
        structure.addAction(this, new PiedPiperWinCheckAction());
    }
}
