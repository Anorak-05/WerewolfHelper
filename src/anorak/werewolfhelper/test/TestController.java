package anorak.werewolfhelper.test;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.IUIController;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.base.ERole;
import anorak.werewolfhelper.model.base.Role;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

// This testcontroller does absolutely not care about input correctness.
// It is just used to quickly simulate a game with all the inputs predefined

public class TestController implements IUIController {
    private final String[] input;
    private int index;

    public static TestController fromFile(String path) {
        File file = new File(path);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            reader.readLine();
            String inputs = reader.readLine().trim();
            String[] inputArray = inputs.replaceAll("[\\[\\]]", "").split(",\\s*");

            return new TestController(inputArray);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public TestController(String[] input) {
        this.input = input;
    }

    private String consumeToken(String message) {
        if (index >= input.length) {
            System.exit(42);
        }
        String token = input[index++];

        System.out.println("REQUEST:\t" + message + "\n\tRESPONSE:\t" + token);
        return token;
    }

    private Player requestPlayer(String styling, String message) {
        return GlobalState.getInstance().getGame().getPlayerByName(consumeToken(message));
    }

    @Override
    public String requestString(String styling, String message) {
        return consumeToken(message);
    }

    @Override
    public Player requestPlayer(String styling, String message, List<Player> fromPlayers) {
        return GlobalState.getInstance().getGame().getPlayerByName(consumeToken(message));
    }

    @Override
    public Role requestRole(String styling, String message, List<ERole> fromRoles) {
        return ERole.getRoleByName(consumeToken(message));
    }

    @Override
    public boolean requestBoolean(String styling, String message) {
        return Boolean.parseBoolean(consumeToken(message));
    }

    @Override
    public int requestInt(String styling, String message) {
        return Integer.parseInt(consumeToken(message));
    }

    @Override
    public void display(String styling, String message) {
        System.out.println("DISPLAY:\t" + message);
    }
}