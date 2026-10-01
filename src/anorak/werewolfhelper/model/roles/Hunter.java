package anorak.werewolfhelper.model.roles;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIPlayerRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.base.Role;

public class Hunter extends Role {

    public Hunter(String name) {
        super(name);
    }

    @Override
    public void end() {
        super.end();

        Player toKill = new UIPlayerRequest("Hunter", "Who does the Hunter want to pull into the Grave?").request();
        toKill.die();
    }
}