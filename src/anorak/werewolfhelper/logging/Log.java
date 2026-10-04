package anorak.werewolfhelper.logging;

public class Log {
    protected String message;

    public Log(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DEFAULT:\t" + message;
    }
}