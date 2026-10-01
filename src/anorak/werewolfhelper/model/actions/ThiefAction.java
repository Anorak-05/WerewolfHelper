package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.controller.base.requests.UIRoleRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.base.Role;
import anorak.werewolfhelper.model.structure.GamePhase;

import java.util.List;

public class ThiefAction implements IGameEventAction {
    Player player;

    public ThiefAction(Player player) {
        this.player = player;
    }

    @Override
    public void respondToGameEvent() {
        Role newRole = new UIRoleRequest("Thief", "Which role does the Thief choose for himself?").request();
        player.changeRole(newRole);
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public List<GamePhase> getPhases() {
        return List.of(GamePhase.PRE_FIRST_NIGHT);
    }
}
