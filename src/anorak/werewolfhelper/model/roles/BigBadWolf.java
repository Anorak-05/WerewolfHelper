package anorak.werewolfhelper.model.roles;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.BigBadWolfAction;
import anorak.werewolfhelper.model.effects.EEffect;
import anorak.werewolfhelper.model.base.Role;
import anorak.werewolfhelper.model.structure.GameStructure;

public class BigBadWolf extends Role {

    public BigBadWolf(String name) {
        super(name);
    }

    @Override
    public void setup(Player player, GameStructure structure) {
        super.setup(player, structure);

        structure.addAction(this, new BigBadWolfAction());

        player.addEffect(EEffect.IS_WEREWOLF);
    }
}
