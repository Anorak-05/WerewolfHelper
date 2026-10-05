package anorak.werewolfhelper.logging;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Logger implements ILogger {
    List<Log> logs;
    List<ILoggableResult> inputs;

    public Logger() {
        logs = new ArrayList<>();
        inputs = new ArrayList<>();
    }

    @Override
    public void addLog(String message) {
        logs.add(new DisplayLog(message));
    }

    @Override
    public void addLog(String message, ILoggableResult result) {
        RequestLog log = new RequestLog(message, result);
        inputs.add(result);
        logs.add(log);
    }

    @Override
    public String getLogs() {
        return logs.stream().map(Log::toString).collect(Collectors.joining("\n"));
    }

    @Override
    public void saveLogs() {
        StringBuilder logText = new StringBuilder();

        logText.append("INPUTS\n");

        String inputsString = Loggables.fromList(inputs, Function.identity()).getInputString();

        logText.append(inputsString);
        logText.append("\n\n");

        logText.append(getLogs());

        writeToFile(logText.toString());
    }

    // CHATGPT method (I couldn't be bothered)
    private void writeToFile(String content) {
        // Get the user's Documents directory
        Path documents = Paths.get(System.getProperty("user.home"), "Documents");

        // Define the target directory
        Path directory = documents.resolve("WerewolfHelper").resolve("RecordedGames");

        try {
            // Create directories if they don't exist
            Files.createDirectories(directory);

            // Generate current timestamp for filename
            String currentTime = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

            // Create filename
            Path file = directory.resolve(currentTime + "_Game.txt");

            // Write content to file
            Files.writeString(file, content, StandardOpenOption.CREATE_NEW);

            System.out.println("Game saved successfully: " + file);

        } catch (IOException e) {
            System.err.println("Failed to save game: " + e.getMessage());
            e.printStackTrace();
        }
    }
}