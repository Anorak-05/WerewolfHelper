package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.controller.base.requests.UIMultiplePlayersRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.structure.GamePhase;
import anorak.werewolfhelper.model.structure.GameStructure;
import anorak.werewolfhelper.model.util.VoteResult;

import java.util.List;

public class ScapegoatAction implements IGameEventAction {
    Player player;
    GameStructure structure;

    public ScapegoatAction(Player player, GameStructure structure) {
        this.player = player;
        this.structure = structure;
    }
    @Override
    public void respondToGameEvent() {
        VoteResult voteResult = GlobalState.getInstance().getGame().getVoteResult();
        if (voteResult.isDiscarded()) return;

        if(voteResult.isTie()) {
            new UIDisplayRequest("Scapegoat", "No one would be hanged today - The scapegoat takes the blame.").request();
            List<Player> allowedToVote = new UIMultiplePlayersRequest("Scapegoat", "Which players are allowed to vote tomorrow?").request();

            IGameEventAction voteAction = new ScapeGoatVotingAction(allowedToVote, structure);
            structure.addAction(voteAction, voteAction);

            player.die();

            voteResult.discardVote();
        }
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
