package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.controller.base.requests.UIPlayerRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.roles.Seer;
import anorak.werewolfhelper.model.structure.GamePhase;

import java.util.List;

public class SeerAction extends Action {
    public SeerAction() {
        super(EAction.SEER);
    }

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
}
