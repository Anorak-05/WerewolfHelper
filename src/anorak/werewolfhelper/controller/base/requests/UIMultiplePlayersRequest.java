package anorak.werewolfhelper.controller.base.requests;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.IUIRequest;
import anorak.werewolfhelper.controller.base.UIAction;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.base.Effect;

import java.util.ArrayList;
import java.util.List;

public class UIMultiplePlayersRequest extends UIAction implements IUIRequest<List<Player>> {

    private List<Player> fromPlayers;
    private int max, min;

    public UIMultiplePlayersRequest(String styling, String message) {
        super(styling, message);
        max = Integer.MAX_VALUE;
        min = 0;
    }

    public UIMultiplePlayersRequest setMin(int min) {
        this.min = min;
        return this;
    }

    public UIMultiplePlayersRequest setMax(int max) {
        this.max = max;
        return this;
    }

    public UIMultiplePlayersRequest fromPlayers(List<Player> fromPlayers) {
        this.fromPlayers = fromPlayers;
        return this;
    }

    @Override
    public List<Player> request() {

        List<Player> result = new ArrayList<>();
        GlobalState.getInstance().getUiController().display(styling, message);

        while (result.size() < max && (result.size() < min || GlobalState.getInstance().getUiController().requestBoolean(styling, "Add another?"))) {
            result.add(GlobalState.getInstance().getUiController().requestPlayer(styling, "Choose Player", fromPlayers));
        }

        GlobalState.getInstance().getLogger().addLog(message, result);
        return result;
    }
}