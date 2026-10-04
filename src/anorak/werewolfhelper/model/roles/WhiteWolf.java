package anorak.werewolfhelper.model.roles;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.WhiteWolfWinCheckAction;
import anorak.werewolfhelper.model.effects.EEffect;
import anorak.werewolfhelper.model.base.Role;
import anorak.werewolfhelper.model.structure.GameStructure;

public class WhiteWolf extends Role {

    public WhiteWolf(String name) {
        super(name);
    }

    @Override
    public void setup(Player player, GameStructure structure) {
        super.setup(player, structure);

        player.addEffect(EEffect.IS_WEREWOLF);
        structure.addAction(this, new WhiteWolfWinCheckAction());
    }
}
