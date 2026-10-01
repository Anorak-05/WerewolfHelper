package anorak.werewolfhelper.controller.base;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.base.ERole;
import anorak.werewolfhelper.model.base.Role;

import java.util.List;

// this is the only way for the model to communicate with whatever controls it - User through CMD or App or Test runner
public interface IUIController {
    Player requestPlayer(String styling, String message, List<Player> fromPlayers);
    Role requestRole(String styling, String message, List<ERole> fromRoles);
    boolean requestBoolean(String styling, String message);
    int requestInt(String styling, String message);
    void display(String styling, String message);
}