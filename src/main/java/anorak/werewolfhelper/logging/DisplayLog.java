package anorak.werewolfhelper.logging;

public class DisplayLog extends Log{
    public DisplayLog(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "DISPLAY:\t" + message;
    }
}
