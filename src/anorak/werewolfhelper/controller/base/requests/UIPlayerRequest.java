package anorak.werewolfhelper.controller.base.requests;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.IUIRequest;
import anorak.werewolfhelper.controller.base.UIAction;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.base.Effect;

import java.util.List;

public class UIPlayerRequest extends UIAction implements IUIRequest<Player> {

    List<Player> fromPlayers;

    public UIPlayerRequest(String styling, String message) {
        super(styling, message);
    }

    public UIPlayerRequest(String styling, String message, List<Class<? extends Effect>> withEffects) {
        super(styling, message);

        //TODO make this better -> Filter Object that can be used by Game to find subset of Players
        fromPlayers = GlobalState.getInstance().getGame().getAlivePlayers().stream().filter(player -> withEffects.stream().allMatch(player::hasEffect)).toList();
    }

    public static UIPlayerRequest withEffects(String styling, String message, List<Class<? extends Effect>> effects) {
        return new UIPlayerRequest(styling, message, effects);
    }

    public static UIPlayerRequest fromPlayers(String styling, String message, List<Player> fromPlayers) {
        UIPlayerRequest request = new UIPlayerRequest(styling, message);
        request.fromPlayers = fromPlayers;
        return request;
    }

    @Override
    public Player request() {
        Player result = GlobalState.getInstance().getUiController().requestPlayer(styling, message, fromPlayers);
        GlobalState.getInstance().getLogger().addLog(message, result);
        return result;
    }
}