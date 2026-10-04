package anorak.werewolfhelper.controller.base.requests;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.IUIRequest;
import anorak.werewolfhelper.controller.base.UIAction;
import anorak.werewolfhelper.logging.Loggables;

public class UIIntRequest extends UIAction implements IUIRequest<Integer> {

    public UIIntRequest(String styling, String message) {
        super(styling, message);
    }

    @Override
    public Integer request() {
        int result = GlobalState.getInstance().getUiController().requestInt(styling, message);
        GlobalState.getInstance().getLogger().addLog(message, Loggables.fromInt(result));
        return result;
    }
}
