package anorak.werewolfhelper;

import anorak.werewolfhelper.controller.base.IUIController;
import anorak.werewolfhelper.logging.ILogger;
import anorak.werewolfhelper.logging.Logger;
import anorak.werewolfhelper.model.Game;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.test.TestController;
import anorak.werewolfhelper.view.console.ConsoleController;

import static anorak.werewolfhelper.model.base.ERole.*;

public class Main {

    public static void main(String[] args) {
        IUIController controller;
        ILogger logger = new Logger();

        //controller = TestController.fromFile("C:\\Users\\minec\\Documents\\WerewolfHelper\\RecordedGames\\2026-10-04_17-34-47_Game.txt");
        controller = new ConsoleController();

        GlobalState.createInstance(controller, logger);

        GlobalState.getInstance().getGame().startFromUI();

        logger.saveLogs();
    }
}