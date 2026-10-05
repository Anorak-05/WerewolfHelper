package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.structure.GamePhase;
import anorak.werewolfhelper.model.structure.GameStructure;
import anorak.werewolfhelper.model.util.VoteResult;

import java.util.List;

public class FoolAction extends Action {
    Player player;
    GameStructure structure;

    public FoolAction(Player player, GameStructure structure) {
        super(EAction.FOOL);

        this.player = player;
        this.structure = structure;
    }

    @Override
    public void respondToGameEvent() {
        VoteResult voteResult = GlobalState.getInstance().getGame().getVoteResult();
        if (voteResult.isDiscarded() || voteResult.noVote()) return;

        if (voteResult.getLynchingCandidate() == player) {
            new UIDisplayRequest("Fool",
                    "The Fool shows his character token and is saved from Execution. There will be no executions today")
                    .request();

            structure.addAction(player, new ExposedFoolAction());
            voteResult.discardVote();
        }
    }
}
