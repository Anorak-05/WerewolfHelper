package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.controller.base.requests.UIRoleRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.base.Role;

public class ThiefAction extends Action {
    Player player;

    public ThiefAction(Player player) {
        super(EAction.THIEF);

        this.player = player;
    }

    @Override
    public void respondToGameEvent() {
        Role newRole = new UIRoleRequest("Thief", "Which role does the Thief choose for himself?").request();
        player.changeRole(newRole);
    }
}
