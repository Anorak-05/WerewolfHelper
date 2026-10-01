package anorak.werewolfhelper.logging;

public class Log {
    private String message;
    private String result;

    public Log(String message, String result) {
        this.message = message;
        this.result = result;
    }

    @Override
    public String toString() {
        if (result == null) {
            return "DISPLAY:\t" + message;
        } else {
            return "REQUEST:\t" + message + "\n\tRESULT:\t" + result;
        }
    }
}
