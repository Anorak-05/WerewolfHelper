package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIPlayerRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.structure.GamePhase;
import anorak.werewolfhelper.model.util.VoteResult;

import java.util.List;

public class CaptainTieBreakerAction implements IGameEventAction {

    @Override
    public void respondToGameEvent() {
        VoteResult voteResult = GlobalState.getInstance().getGame().getVoteResult();

        if (voteResult.noVote() || !voteResult.isTie()) return;

        Player toLynch = UIPlayerRequest.fromPlayers ("Captain",
                "TIEBREAKER: who did the Captain vote for?", voteResult.getAllMostVoted()).request();

        voteResult.setVotes(toLynch, voteResult.getVotes(toLynch) + 1);
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public List<GamePhase> getPhases() {
        return List.of(GamePhase.POST_VOTE);
    }
}
