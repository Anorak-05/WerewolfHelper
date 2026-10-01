package anorak.werewolfhelper.model.roles;

import anorak.werewolfhelper.model.structure.GameStructure;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.base.Role;
import anorak.werewolfhelper.model.effects.EffectIsWerewolf;

public class Werewolf extends Role {

    public Werewolf(String name) {
        super(name);
    }

    @Override
    public void setup(Player player, GameStructure structure) {
        super.setup(player, structure);
        player.addEffect(new EffectIsWerewolf(player, structure));
    }
}