package anorak.werewolfhelper.model.roles;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.BitterOldManAction;
import anorak.werewolfhelper.model.actions.BitterOldManWinCheckAction;
import anorak.werewolfhelper.model.base.Role;
import anorak.werewolfhelper.model.structure.GameStructure;

public class BitterOldMan extends Role {
    public BitterOldMan(String name) {
        super(name);
    }

    @Override
    public void setup(Player player, GameStructure structure) {
        super.setup(player, structure);

        structure.addAction(this, new BitterOldManAction());
        structure.addAction(this, new BitterOldManWinCheckAction());
    }
}
