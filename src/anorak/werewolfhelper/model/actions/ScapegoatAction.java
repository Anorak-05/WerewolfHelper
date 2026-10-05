package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.controller.base.requests.UIMultiplePlayersRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.actions.base.OneTimeAction;
import anorak.werewolfhelper.model.roles.Scapegoat;
import anorak.werewolfhelper.model.structure.GameStructure;
import anorak.werewolfhelper.model.util.VoteResult;

import java.util.List;

public class ScapegoatAction extends Action {
    private final Player player;
    private final GameStructure structure;

    public ScapegoatAction(Player player, GameStructure structure) {
        super(EAction.SCAPEGOAT);

        this.player = player;
        this.structure = structure;
    }
    @Override
    public void respondToGameEvent() {
        VoteResult voteResult = GlobalState.getInstance().getGame().getVoteResult();
        if (voteResult.isDiscarded() || voteResult.noVote()) return;

        if(voteResult.isTie()) {
            new UIDisplayRequest("Scapegoat", "No one would be hanged today - The scapegoat takes the blame.").request();
            List<Player> allowedToVote = new UIMultiplePlayersRequest("Scapegoat", "Which players are allowed to vote tomorrow?")
                    .fromPlayers(
                            GlobalState.getInstance().getGame().getPlayers()
                                    .excludeDead()
                                    .excludeHasRole(Scapegoat.class)
                                    .get()
                    )
                    .request();

            OneTimeAction.createAndAdd(structure, new ScapeGoatVotingAction(allowedToVote));

            player.die();

            voteResult.discardVote();
        }
    }
}
