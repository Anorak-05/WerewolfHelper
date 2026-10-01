package anorak.werewolfhelper;

import anorak.werewolfhelper.controller.base.IUIController;
import anorak.werewolfhelper.logging.ILogger;
import anorak.werewolfhelper.logging.Logger;
import anorak.werewolfhelper.model.Game;
import anorak.werewolfhelper.view.console.ConsoleController;

public class GlobalState {
    private static GlobalState instance;

    private Game game;
    private final IUIController uiController;
    private final ILogger logger;

    private GlobalState() {
        game = new Game();
        uiController = new ConsoleController();
//        uiController = new TestController(
//                new String[]{
//                        "Tom", "Tom", "n", "n", "Heinz", "5", "n", "Tim", "Nils", "n", "y", "Hans"
//                }
//        );
        logger = new Logger();
    }

    public Game getGame() {
        return game;
    }

    public ILogger getLogger() {
        return logger;
    }

    public IUIController getUiController() {
        return uiController;
    }

    public static GlobalState getInstance() {
        if (instance == null) {
            instance = new GlobalState();
        }
        return instance;
    }
}