package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIPlayerRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.structure.GamePhase;
import anorak.werewolfhelper.model.util.VoteResult;

import java.util.List;

public class CaptainTieBreakerAction extends Action {
    public CaptainTieBreakerAction() {
        super(EAction.CAPTAIN_TIEBREAKER);
    }

    @Override
    public void respondToGameEvent() {
        VoteResult voteResult = GlobalState.getInstance().getGame().getVoteResult();

        if (voteResult.noVote() || !voteResult.isTie()) return;

        Player toLynch = new UIPlayerRequest("Captain",
                "TIEBREAKER: who did the Captain vote for?")
                .fromPlayers(voteResult.getAllMostVoted())
                .request();

        voteResult.setVotes(toLynch, voteResult.getVotes(toLynch) + 1);
    }
}
