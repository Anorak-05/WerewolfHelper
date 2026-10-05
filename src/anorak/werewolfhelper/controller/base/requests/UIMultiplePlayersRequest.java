package anorak.werewolfhelper.controller.base.requests;

import anorak.werewolfhelper.controller.base.IUIRequest;
import anorak.werewolfhelper.controller.base.UIAction;
import anorak.werewolfhelper.model.Player;

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
        new UIDisplayRequest(styling, message).request();

        while (result.size() < max && (result.size() < min || new UIBooleanRequest(styling, "Add another?").request())) {
            result.add(new UIPlayerRequest(styling, "Choose Player").fromPlayers(fromPlayers).request());
        }

        return result;
    }
}