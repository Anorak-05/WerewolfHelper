package anorak.werewolfhelper.controller.base.requests;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.IUIRequest;
import anorak.werewolfhelper.controller.base.UIAction;
import anorak.werewolfhelper.logging.Loggables;

public class UIStringRequest extends UIAction implements IUIRequest<String> {
    public UIStringRequest(String styling, String message) {
        super(styling, message);
    }

    @Override
    public String request() {
        String result = GlobalState.getInstance().getUiController().requestString(styling, message);
        GlobalState.getInstance().getLogger().addLog(message, Loggables.fromString(result));
        return result;
    }
}
