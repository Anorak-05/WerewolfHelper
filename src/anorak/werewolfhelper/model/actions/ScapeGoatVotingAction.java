package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.structure.GamePhase;
import anorak.werewolfhelper.model.structure.GameStructure;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.base.IGameEventAction;

import java.util.Arrays;
import java.util.List;

public class ScapeGoatVotingAction implements IGameEventAction {
    List<Player> players;
    GameStructure structure;

    public ScapeGoatVotingAction(List<Player> players, GameStructure structure) {
        this.players = players;
        this.structure = structure;
    }

    @Override
    public void respondToGameEvent() {
        new UIDisplayRequest("Scapegoat", "These players are allowed to vote today: "
        + Arrays.deepToString(players.toArray())).request();

        structure.removeAllActions(this);
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public List<GamePhase> getPhases() {
        return List.of(GamePhase.PRE_VOTE);
    }
}