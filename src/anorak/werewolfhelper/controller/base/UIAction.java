package anorak.werewolfhelper.controller.base;

public abstract class UIAction {
    protected String message;
    protected String styling;

    public UIAction(String styling, String message) {
        this.message = message;
        this.styling = styling;
    }
}