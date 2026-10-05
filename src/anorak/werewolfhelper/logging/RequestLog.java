package anorak.werewolfhelper.logging;

public class RequestLog extends Log{
    public final ILoggableResult result;

    public RequestLog(String message, ILoggableResult result) {
        super(message);

        this.result = result;
    }

    @Override
    public String toString() {
        return "REQUEST:\t" + message + "\n\tRESULT:\t" + result.getPrettyString();
    }

    public String getInputString() {
        return result.getInputString();
    }
}