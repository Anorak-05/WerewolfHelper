package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIBooleanRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.base.ERole;
import anorak.werewolfhelper.model.effects.EEffect;

import java.util.List;

public class DevotedServantAction extends Action {
    private final Player player;

    public DevotedServantAction(Player player) {
        super(EAction.DEVOTED_SERVANT);

        this.player = player;
    }

    @Override
    public void respondToGameEvent() {
        List<Player> playersToBeLynched = GlobalState.getInstance().getGame().getPlayers()
                .excludeDead()
                .includeHasEffect(EEffect.STAGED_FOR_LYNCHING)
                .get();

        if (playersToBeLynched.isEmpty()) return;
        Player toBeLynched = playersToBeLynched.getFirst();

        if (new UIBooleanRequest("DevotedServant", "Does the DevotedServant take the Role of the Executed Player?").request()) {
            player.changeRole(ERole.getRoleByName(toBeLynched.getRole().getName()));
            toBeLynched.die();
        }
    }
}
