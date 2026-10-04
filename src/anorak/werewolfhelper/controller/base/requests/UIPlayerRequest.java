package anorak.werewolfhelper.controller.base.requests;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.IUIRequest;
import anorak.werewolfhelper.controller.base.UIAction;
import anorak.werewolfhelper.logging.Loggables;
import anorak.werewolfhelper.model.Player;

import java.util.List;

public class UIPlayerRequest extends UIAction implements IUIRequest<Player> {

    List<Player> fromPlayers;

    public UIPlayerRequest(String styling, String message) {
        super(styling, message);
    }

    public UIPlayerRequest fromPlayers(List<Player> fromPlayers) {
        this.fromPlayers = fromPlayers;
        return this;
    }

    @Override
    public Player request() {
        Player result = GlobalState.getInstance().getUiController().requestPlayer(styling, message, fromPlayers);
        GlobalState.getInstance().getLogger().addLog(message, Loggables.fromPlayer(result));
        return result;
    }
}