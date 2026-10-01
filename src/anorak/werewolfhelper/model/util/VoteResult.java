package anorak.werewolfhelper.model.util;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.effects.EffectVoted;

import java.util.List;

public class VoteResult {

    static class Vote {
        private Player player;
        private int votes;

        public Vote(Player player, int votes) {
            this.player = player;
            this.votes = votes;
        }
    }

    private List<Vote> voteList;
    private boolean discarded = false;

    public VoteResult() {
        System.out.println(GlobalState.getInstance().getGame().getPlayersByEffect(EffectVoted.class));

        voteList = getVoteResults();
    }

    private List<Vote> getVoteResults() {
        return GlobalState.getInstance().getGame().getPlayersByEffect(EffectVoted.class)
                .stream()
                .map(p -> new Vote(p, p.getEffect(EffectVoted.class).getVotes()))
                .sorted((a, b) -> b.votes - a.votes)
                .toList();
    }

    private void sortVotes() {
        voteList = voteList.stream().sorted((a, b) -> b.votes - a.votes).toList();
    }

    public void discardVote() {
        discarded = true;
    }

    public boolean isDiscarded() {
        return discarded;
    }

    public Player getLynchingCandidate() {
        if (isTie() || voteList.isEmpty()) return null;
        return voteList.getFirst().player;
    }

    public boolean noVote() {
        return voteList.isEmpty();
    }

    public boolean isTie() {
        return voteList.isEmpty()
                || voteList.getFirst().votes == 0
                || (voteList.size() >= 2 && voteList.get(0).votes == voteList.get(1).votes);
    }

    public List<Player> getAllVoted() {
        return voteList.stream().map(vote -> vote.player).toList();
    }

    public List<Player> getAllMostVoted() {
        if (voteList.isEmpty()) return null;
        int mostVotes = voteList.getFirst().votes;

        return voteList.stream()
                .filter(vote -> vote.votes == mostVotes)
                .map(vote -> vote.player)
                .toList();
    }

    public int getVotes(Player votedPlayer) {
        Vote result = voteList.stream().filter(vote -> vote.player == votedPlayer).findAny().orElse(null);

        if (result == null) return -1;
        return result.votes;
    }

    public void setVotes(Player votedPlayer, int newVotes) {
        Vote result = voteList.stream().filter(vote -> vote.player == votedPlayer).findAny().orElse(null);

        if (result == null) return;
        result.votes = newVotes;

        sortVotes();
    }
}