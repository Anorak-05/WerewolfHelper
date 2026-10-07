package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;

public class ExposedFoolAction extends Action {
    public ExposedFoolAction() {
        super(EAction.EXPOSED_FOOL);
    }

    @Override
    public void respondToGameEvent() {
        new UIDisplayRequest("Fool", "The fool may not vote anymore").request();
    }

}
