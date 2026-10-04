package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.controller.base.requests.UIBooleanRequest;
import anorak.werewolfhelper.controller.base.requests.UIRoleRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.OneTimeAction;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.base.Role;
import anorak.werewolfhelper.model.effects.EffectIsActor;
import anorak.werewolfhelper.model.structure.GamePhase;
import anorak.werewolfhelper.model.structure.GameStructure;

import java.util.List;

public class ActorSwitchAction implements IGameEventAction {
    Player player;
    EffectIsActor effect;
    GameStructure structure;

    public ActorSwitchAction(Player player, EffectIsActor effect, GameStructure structure) {
        this.player = player;
        this.structure = structure;

        this.effect = effect;
    }

    @Override
    public void respondToGameEvent() {
        if (!effect.canSwitchRole()) return;

        if (new UIBooleanRequest("Actor", "Does the Actor want to imitate a role tonight?").request()) {
            Role role = new UIRoleRequest("Actor", "Who does the Actor want to imitate?").request();

            player.changeRole(role);
            effect.useRoleSwitch();

            OneTimeAction.createAndAdd(structure, new ActorSwitchBackAction(player, structure));
        }
    }

    @Override
    public int getPriority() {
        return 10;
    }

    @Override
    public List<GamePhase> getPhases() {
        return List.of(GamePhase.PRE_FIRST_NIGHT, GamePhase.PRE_NIGHT);
    }
}
