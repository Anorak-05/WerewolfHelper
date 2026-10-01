package anorak.werewolfhelper.logging;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Logger implements ILogger {
    List<Log> logs;

    public Logger() {
        logs = new ArrayList<>();
    }

    @Override
    public void addLog(String message) {
        logs.add(new Log(message, null));
    }

    @Override
    public void addLog(String message, Object result) {
        logs.add(new Log(message, result.toString()));
    }

    @Override
    public String getLogs() {
        return logs.stream().map(Log::toString).collect(Collectors.joining("\n"));
    }
}
