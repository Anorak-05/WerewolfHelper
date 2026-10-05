package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.effects.EEffect;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.structure.GamePhase;
import anorak.werewolfhelper.model.structure.GameStructure;
import anorak.werewolfhelper.model.util.VoteResult;

import java.util.List;

public class LynchingAction extends Action {
    public LynchingAction() {
        super(EAction.LYNCHING);
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
        mostVoted.addEffect(EEffect.STAGED_FOR_LYNCHING);
        voteResult.discardVote();
    }
}
