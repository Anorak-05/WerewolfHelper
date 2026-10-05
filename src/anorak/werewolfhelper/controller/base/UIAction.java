package anorak.werewolfhelper.controller.base;

public abstract class UIAction {
    protected final String message;
    protected final String styling;

    public UIAction(String styling, String message) {
        this.message = message;
        this.styling = styling;
    }
}