package anorak.werewolfhelper.controller.base.requests;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.IUIRequest;
import anorak.werewolfhelper.controller.base.UIAction;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.base.Effect;

import java.util.ArrayList;
import java.util.List;

public class UIMultiplePlayersRequest extends UIAction implements IUIRequest<List<Player>> {

    List<Player> fromPlayers;

    public UIMultiplePlayersRequest(String styling, String message) {
        super(styling, message);
    }

    public UIMultiplePlayersRequest(String styling, String message, List<Class<? extends Effect>> withEffects) {
        super(styling, message);

        //TODO make this better -> Filter Object that can be used by Game to find subset of Players
        fromPlayers = GlobalState.getInstance().getGame().getAlivePlayers()
                .stream()
                .filter(
                        player -> withEffects.stream().allMatch(player::hasEffect)
                ).toList();
    }

    @Override
    public List<Player> request() {

        List<Player> result = new ArrayList<>();
        result.add(GlobalState.getInstance().getUiController().requestPlayer(styling, message, fromPlayers));

        while (GlobalState.getInstance().getUiController().requestBoolean(styling, "Add another?")) {
            result.add(GlobalState.getInstance().getUiController().requestPlayer(styling, "Choose Player", fromPlayers));
        }

        GlobalState.getInstance().getLogger().addLog(message, result);
        return result;
    }
}