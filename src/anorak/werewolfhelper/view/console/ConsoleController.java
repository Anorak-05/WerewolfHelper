package anorak.werewolfhelper.view.console;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.IUIController;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.base.ERole;
import anorak.werewolfhelper.model.base.Role;

import java.util.List;
import java.util.Scanner;

public class ConsoleController implements IUIController {
    static Scanner scanner = new Scanner(System.in);

    @Override
    public void display(String styling, String message) {
        System.out.println("[" + styling + "]\t" + message);
    }

    @Override
    public String requestString(String styling, String message) {
        System.out.println("[" + styling + "]\t" + message);
        System.out.print("> ");
        return scanner.nextLine();
    }

    @Override
    public boolean requestBoolean(String styling, String message) {
        while (true) {
            System.out.println("[" + styling + "]\t" + message + "\t[y|n]");
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.isEmpty()) continue;

            switch (input.toCharArray()[0]) {
                case 'y', 'Y':
                    return true;
                case 'n', 'N':
                    return false;
            }
        }
    }

    @Override
    public int requestInt(String styling, String message) {
        String input;

        while (true) {
            System.out.println("[" + styling + "]\t" + message);
            System.out.print("> ");
            input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException _) {}
        }
    }

    private Player requestPlayer(String styling, String message) {
        Player player;

        do {
            String playerName = requestString(styling, message);
            player = GlobalState.getInstance().getGame().getPlayerByName(playerName);
        } while (player == null);

        return player;
    }

    @Override
    public Player requestPlayer(String styling, String message, List<Player> fromPlayers) {
        if (fromPlayers == null || fromPlayers.isEmpty())
            return requestPlayer(styling, message);

        Player player;

        display(styling, "Choose a Player: " + fromPlayers);

        do {
            player = requestPlayer(styling, message);

            if (!fromPlayers.contains(player)) {
                player = null;
            }
        } while (player == null);

        return player;
    }

    @Override
    public Role requestRole(String styling, String message, List<ERole> fromRoles) {
        Role role = null;

        if (fromRoles != null)
            display(styling, "Choose a Role: " + fromRoles);

        do {
            String roleName = requestString(styling, message).trim();

            if (fromRoles != null && fromRoles.stream().noneMatch(r -> r.getName().equals(roleName)))
                continue;

            role = ERole.getRoleByName(roleName);
        } while (role == null);

        return role;
    }
}