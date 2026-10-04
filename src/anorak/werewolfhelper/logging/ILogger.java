package anorak.werewolfhelper.logging;

public interface ILogger {
    void addLog(String message);
    void addLog(String message, ILoggableResult result);

    String getLogs();
    void saveLogs();
}