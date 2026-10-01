package anorak.werewolfhelper.model.roles;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.base.Role;
import anorak.werewolfhelper.model.effects.EffectIsActor;
import anorak.werewolfhelper.model.structure.GameStructure;

public class Actor extends Role {
    public Actor(String name) {
        super(name);
    }

    @Override
    public void setup(Player player, GameStructure structure) {
        super.setup(player, structure);

        player.addEffect(new EffectIsActor(player, structure));
    }
}