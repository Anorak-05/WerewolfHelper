package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIBooleanRequest;
import anorak.werewolfhelper.controller.base.requests.UIIntRequest;
import anorak.werewolfhelper.controller.base.requests.UIPlayerRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.effects.EffectVoted;
import anorak.werewolfhelper.model.structure.GamePhase;

import java.util.List;

public class VoteAction implements IGameEventAction {
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
                toVote.addEffect(new EffectVoted(toVote, GlobalState.getInstance().getGame().getStructure(), votes));
            }
        } while (new UIBooleanRequest("voting", "Vote for another Player?").request());

        GlobalState.getInstance().getGame().saveVoteResult();
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public List<GamePhase> getPhases() {
        return List.of(GamePhase.VOTE);
    }
}