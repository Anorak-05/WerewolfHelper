package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIBooleanRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.effects.EEffect;
import anorak.werewolfhelper.model.base.ERole;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.structure.GamePhase;

import java.util.List;

public class DevotedServantAction implements IGameEventAction {
    private Player player;

    public DevotedServantAction(Player player) {
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

    @Override
    public int getPriority() {
        return 10000;
    }

    @Override
    public List<GamePhase> getPhases() {
        return List.of(GamePhase.POST_VOTE);
    }
}
