package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;

public class EnchantedAction extends Action {

    public EnchantedAction() {
        super(EAction.ENCHANTED);
    }

    @Override
    public void respondToGameEvent() {
        new UIDisplayRequest("PiedPiper", "All enchanted players wake up and see each other")
                .request();
    }
}
