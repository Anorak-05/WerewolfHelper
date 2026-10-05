package anorak.werewolfhelper.model.roles;

import anorak.werewolfhelper.controller.base.requests.UIBooleanRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.WolfHoundAction;
import anorak.werewolfhelper.model.effects.EEffect;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.base.Role;
import anorak.werewolfhelper.model.structure.GamePhase;
import anorak.werewolfhelper.model.structure.GameStructure;

import java.util.List;

public class WolfHound extends Role {
    public WolfHound(String name) {
        super(name);
    }

    @Override
    public void setup(Player player, GameStructure structure) {
        super.setup(player, structure);

        structure.addAction(this, new WolfHoundAction(player));
    }
}
