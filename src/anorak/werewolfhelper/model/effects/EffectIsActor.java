package anorak.werewolfhelper.model.effects;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.ActorSwitchAction;
import anorak.werewolfhelper.model.base.Effect;
import anorak.werewolfhelper.model.structure.GameStructure;

public class EffectIsActor extends Effect {
    int rolesRemaining = 3;

    public EffectIsActor(Player player, GameStructure structure) {

        super(player, structure);
        structure.addAction(this, new ActorSwitchAction(player, this, structure));
    }

    public boolean canSwitchRole() {
        return rolesRemaining > 0;
    }

    public void useRoleSwitch() {
        rolesRemaining--;
    }
}
