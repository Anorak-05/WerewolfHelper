package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.effects.EffectStagedForLynching;
import anorak.werewolfhelper.model.structure.GamePhase;
import anorak.werewolfhelper.model.structure.GameStructure;
import anorak.werewolfhelper.model.util.VoteResult;

import java.util.List;

public class LynchingAction implements IGameEventAction {
    private GameStructure structure;

    public LynchingAction(GameStructure structure) {
        this.structure = structure;
    }

    @Override
    public void respondToGameEvent() {
        VoteResult voteResult = GlobalState.getInstance().getGame().getVoteResult();
        if (voteResult.isDiscarded()) return;
        if (voteResult.noVote()) {
            new UIDisplayRequest("Lynching", "No player was voted today").request();
            return;
        }

        if (voteResult.isTie()) {
           new UIDisplayRequest("Lynching", "No player was executed due to a draw in the voting").request();
           return;
        }
        Player mostVoted = voteResult.getLynchingCandidate();
        mostVoted.addEffect(new EffectStagedForLynching(mostVoted, structure));
        voteResult.discardVote();
    }

    @Override
    public int getPriority() {
        return 1000;
    }

    @Override
    public List<GamePhase> getPhases() {
        return List.of(GamePhase.POST_VOTE);
    }
}
