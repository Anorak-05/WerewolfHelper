package anorak.werewolfhelper.logging;

public class Log {
    protected final String message;

    public Log(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DEFAULT:\t" + message;
    }
}