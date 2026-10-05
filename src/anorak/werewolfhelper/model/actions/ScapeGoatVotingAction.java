package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.structure.GamePhase;
import anorak.werewolfhelper.model.structure.GameStructure;

import java.util.Arrays;
import java.util.List;

public class ScapeGoatVotingAction extends Action {
    List<Player> players;
    GameStructure structure;

    public ScapeGoatVotingAction(List<Player> players, GameStructure structure) {
        super(EAction.SCAPEGOAT_VOTING);

        this.players = players;
        this.structure = structure;
    }

    @Override
    public void respondToGameEvent() {
        new UIDisplayRequest("Scapegoat", "These players are allowed to vote today: "
        + Arrays.deepToString(players.toArray())).request();

        structure.removeAllActions(this);
    }
}