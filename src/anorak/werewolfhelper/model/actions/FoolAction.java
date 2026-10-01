package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.effects.EffectVoted;
import anorak.werewolfhelper.model.structure.GamePhase;
import anorak.werewolfhelper.model.structure.GameStructure;
import anorak.werewolfhelper.model.util.VoteResult;

import java.util.List;

public class FoolAction implements IGameEventAction {
    Player player;
    GameStructure structure;

    public FoolAction(Player player, GameStructure structure) {
        this.player = player;
        this.structure = structure;
    }

    @Override
    public void respondToGameEvent() {
        VoteResult voteResult = GlobalState.getInstance().getGame().getVoteResult();
        if (voteResult.isDiscarded()) return;

        if (voteResult.getLynchingCandidate() == player) {
            new UIDisplayRequest("Fool",
                    "The Fool shows his character token and is saved from Execution. There will be no executions today")
                    .request();

            List<Player> votedPlayers = GlobalState.getInstance().getGame().getPlayersByEffect(EffectVoted.class);

            for(Player voted : votedPlayers) {
                voted.removeEffect(EffectVoted.class);
            }

            structure.addAction(player, new ExposedFoolAction());
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
