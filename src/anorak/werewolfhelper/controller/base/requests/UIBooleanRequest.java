package anorak.werewolfhelper.controller.base.requests;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.IUIRequest;
import anorak.werewolfhelper.controller.base.UIAction;

public class UIBooleanRequest extends UIAction implements IUIRequest<Boolean> {
    public UIBooleanRequest(String styling, String message) {
        super(styling, message);
    }

    @Override
    public Boolean request() {
        Boolean result = GlobalState.getInstance().getUiController().requestBoolean(styling, message);
        GlobalState.getInstance().getLogger().addLog(message, result);
        return result;
    }
}