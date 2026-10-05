package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;

import java.util.Arrays;
import java.util.List;

public class ScapeGoatVotingAction extends Action {
    private final List<Player> players;

    public ScapeGoatVotingAction(List<Player> players) {
        super(EAction.SCAPEGOAT_VOTING);

        this.players = players;
    }

    @Override
    public void respondToGameEvent() {
        new UIDisplayRequest("Scapegoat", "These players are allowed to vote today: "
        + Arrays.deepToString(players.toArray())).request();
    }
}