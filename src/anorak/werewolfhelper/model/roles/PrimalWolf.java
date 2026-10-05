package anorak.werewolfhelper.model.roles;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.PrimalWolfAction;
import anorak.werewolfhelper.model.base.Role;
import anorak.werewolfhelper.model.effects.EEffect;
import anorak.werewolfhelper.model.structure.GameStructure;

public class PrimalWolf extends Role {

    public PrimalWolf(String name) {
        super(name);
    }

    @Override
    public void setup(Player player, GameStructure structure) {
        super.setup(player, structure);

        structure.addAction(this, new PrimalWolfAction());

        player.addEffect(EEffect.IS_WEREWOLF);
    }
}