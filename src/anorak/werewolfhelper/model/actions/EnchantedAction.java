package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.structure.GamePhase;

import java.util.List;

public class EnchantedAction implements IGameEventAction {

    @Override
    public void respondToGameEvent() {
        new UIDisplayRequest("PiedPiper", "All enchanted players wake up and see each other")
                .request();
    }

    @Override
    public int getPriority() {
        return 60;
    }

    @Override
    public List<GamePhase> getPhases() {
        return List.of(GamePhase.FIRST_NIGHT, GamePhase.NIGHT);
    }
}
