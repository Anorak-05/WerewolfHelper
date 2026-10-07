package anorak.werewolfhelper.controller.base.requests;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.IUIRequest;
import anorak.werewolfhelper.controller.base.UIAction;

public class UIDisplayRequest extends UIAction implements IUIRequest<Void> {
    public UIDisplayRequest(String styling, String message) {
        super(styling, message);
    }

    @Override
    public Void request() {
        GlobalState.getInstance().getUiController().display(styling, message);
        GlobalState.getInstance().getLogger().addLog(message);
        return null;
    }
}