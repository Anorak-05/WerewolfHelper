package anorak.werewolfhelper.view.console;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;
import java.util.List;

public class JLineConsole {
    private Terminal terminal;
    private LineReader reader;

    public JLineConsole() {
        try {
            terminal = TerminalBuilder.builder()
                    .system(true)
                    .build();
            reader = LineReaderBuilder.builder()
                    .terminal(terminal)
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private JLineConsole(Terminal terminal, LineReader reader) {
        this.terminal = terminal;
        this.reader = reader;
    }

    public static JLineConsole fromOptions(List<String> options) {

        try {
            Terminal terminal = TerminalBuilder.builder()
                    .system(true)
                    .build();
            LineReader reader = LineReaderBuilder.builder()
                    .terminal(terminal)
                    .completer(new StringsCompleter(options))
                    .option(LineReader.Option.AUTO_LIST, true) // Automatically list options
                    .option(LineReader.Option.LIST_PACKED, true) // Display completions in a compact form
                    .option(LineReader.Option.AUTO_MENU, true) // Show menu automatically
                    .option(LineReader.Option.MENU_COMPLETE, true) // Cycle through completions
                    .build();

            return new JLineConsole(terminal, reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String readLine() {
        return reader.readLine("> ");
    }
}
