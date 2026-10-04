package anorak.werewolfhelper.controller.base.requests;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.IUIRequest;
import anorak.werewolfhelper.controller.base.UIAction;
import anorak.werewolfhelper.logging.Loggables;

public class UIBooleanRequest extends UIAction implements IUIRequest<Boolean> {
    public UIBooleanRequest(String styling, String message) {
        super(styling, message);
    }

    @Override
    public Boolean request() {
        boolean result = GlobalState.getInstance().getUiController().requestBoolean(styling, message);
        GlobalState.getInstance().getLogger().addLog(message, Loggables.fromBoolean(result));
        return result;
    }
}