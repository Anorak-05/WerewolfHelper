package anorak.werewolfhelper.controller.base.requests;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.IUIRequest;
import anorak.werewolfhelper.controller.base.UIAction;
import anorak.werewolfhelper.model.base.ERole;
import anorak.werewolfhelper.model.base.Role;

import java.util.List;

public class UIRoleRequest extends UIAction implements IUIRequest<Role> {
    List<ERole> fromRoles;

    public UIRoleRequest(String styling, String message) {
        super(styling, message);
    }

    public UIRoleRequest(String styling, String message, List<ERole> fromRoles) {
        super(styling, message);

        this.fromRoles = fromRoles;
    }

    @Override
    public Role request() {
        Role result = GlobalState.getInstance().getUiController().requestRole(styling, message, fromRoles);
        GlobalState.getInstance().getLogger().addLog(message, result);
        return result;
    }
}