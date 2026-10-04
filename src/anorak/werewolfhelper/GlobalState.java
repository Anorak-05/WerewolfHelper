package anorak.werewolfhelper;

import anorak.werewolfhelper.controller.base.IUIController;
import anorak.werewolfhelper.logging.ILogger;
import anorak.werewolfhelper.logging.Logger;
import anorak.werewolfhelper.model.Game;
import anorak.werewolfhelper.view.console.ConsoleController;

public class GlobalState {
    private static GlobalState instance;

    private final Game game;
    private final IUIController uiController;
    private final ILogger logger;

    private GlobalState(IUIController uiController, ILogger logger) {
        this.uiController = uiController;
        this.logger = logger;

        this.game = new Game();
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

    public static boolean createInstance(IUIController controller, ILogger logger) {
        if (instance == null) {
            instance = new GlobalState(controller, logger);
            return true;
        }
        return false;
    }

    public static GlobalState getInstance() {
        if (instance == null) {
            throw new UnsupportedOperationException("GlobalInstanced was not initialized yet");
        }
        return instance;
    }
}