package anorak.werewolfhelper;

import anorak.werewolfhelper.controller.base.IUIController;
import anorak.werewolfhelper.logging.ILogger;
import anorak.werewolfhelper.logging.Logger;
import anorak.werewolfhelper.model.Game;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.base.ERole;
import anorak.werewolfhelper.model.roles.Mistress;
import anorak.werewolfhelper.model.roles.Werewolf;
import anorak.werewolfhelper.view.console.ConsoleController;
import anorak.werewolfhelper.view.console.JLineConsole;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        JLineConsole.fromOptions(List.of("Nils, Justus, Jenny, Nick")).readLine();
        System.exit(42);

        IUIController controller;
        ILogger logger = new Logger();

        //controller = TestController.fromFile("C:\\Users\\minec\\Documents\\WerewolfHelper\\RecordedGames\\2026-10-04_17-34-47_Game.txt");
        controller = new ConsoleController();

        GlobalState.createInstance(controller, logger);

        Game game = GlobalState.getInstance().getGame();

        game.addPlayer(new Player(ERole.MISTRESS.create(), "M"));
        game.addPlayer(new Player(ERole.WITCH.create(), "W"));
        game.addPlayer(new Player(ERole.WEREWOLF.create(), "WW"));

        for (int i = 0; i < 10; i++) {
            game.addPlayer(new Player(ERole.VILLAGER.create(), "V" + i));
        }

        game.start();

        //GlobalState.getInstance().getGame().startFromUI();

        logger.saveLogs();
    }
}