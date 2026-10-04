package anorak.werewolfhelper.model.effects;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.ActorSwitchAction;
import anorak.werewolfhelper.model.structure.GameStructure;

public class EffectIsActor extends Effect {
    int rolesRemaining = 3;

    EffectIsActor(EEffect effectEnum, Player player, GameStructure structure) {
        super(effectEnum, player, structure);

        structure.addAction(this, new ActorSwitchAction(player, this, structure));
    }

    public boolean canSwitchRole() {
        return rolesRemaining > 0;
    }

    public void useRoleSwitch() {
        rolesRemaining--;
    }
}
