package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.controller.base.requests.UIPlayerRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.roles.Seer;
import anorak.werewolfhelper.model.structure.GamePhase;

import java.util.List;

public class SeerAction implements IGameEventAction {
    @Override
    public void respondToGameEvent() {
        Player toSee = new UIPlayerRequest("Seer", "Which Player does the Seer want to see?")
                .fromPlayers(
                        GlobalState.getInstance().getGame().getPlayers()
                                .excludeDead()
                                .excludeHasRole(Seer.class)
                                .get()
                )
                .request();
       new UIDisplayRequest("Seer", "The Seer sees " + toSee.getRole().toString()).request();
    }

    @Override
    public int getPriority() {
        return 5;
    }

    @Override
    public List<GamePhase> getPhases() {
        return List.of(GamePhase.FIRST_NIGHT, GamePhase.NIGHT);
    }
}
