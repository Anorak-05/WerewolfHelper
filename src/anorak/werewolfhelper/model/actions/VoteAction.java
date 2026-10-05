package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIBooleanRequest;
import anorak.werewolfhelper.controller.base.requests.UIIntRequest;
import anorak.werewolfhelper.controller.base.requests.UIPlayerRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.effects.EEffect;
import anorak.werewolfhelper.model.effects.EffectVoted;

public class VoteAction extends Action {

    public VoteAction() {
        super(EAction.VOTE_LYNCHING);
    }

    @Override
    public void respondToGameEvent() {
        do {
            Player toVote = new UIPlayerRequest("voting", "The village chooses player to hang")
                    .fromPlayers(
                            GlobalState.getInstance().getGame().getPlayers()
                                    .excludeDead()
                                    .get()
                    )
                    .request();
            int votes = new UIIntRequest("voting", "How many votes does " + toVote + " get?").request();

            if (votes > 0) {
                toVote.addEffect(EEffect.VOTED);
                toVote.getEffect(EEffect.VOTED, EffectVoted.class).setVotes(votes);

            }
        } while (new UIBooleanRequest("voting", "Vote for another Player?").request());

        GlobalState.getInstance().getGame().saveVoteResult();
    }
}