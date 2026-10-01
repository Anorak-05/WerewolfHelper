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

    private List<Vote> votes;
    private boolean discarded = false;

    public VoteResult() {
        votes = getVoteResults();
    }

    private List<Vote> getVoteResults() {
        return GlobalState.getInstance().getGame().getPlayersByEffect(EffectVoted.class)
                .stream()
                .map(p -> new Vote(p, p.getEffect(EffectVoted.class).getVotes()))
                .sorted((a, b) -> b.votes - a.votes)
                .toList();
    }

    private void sortVotes() {
        votes = votes.stream().sorted((a, b) -> b.votes - a.votes).toList();
    }

    public void discardVote() {
        discarded = true;
    }

    public boolean isDiscarded() {
        return discarded;
    }

    public Player getLynchingCandidate() {
        if (isTie() || votes.isEmpty()) return null;
        return votes.getFirst().player;
    }

    public boolean isTie() {
        return votes.isEmpty()
                || votes.getFirst().votes == 0
                || (votes.size() >= 2 && votes.get(0).votes == votes.get(1).votes);
    }

    public List<Player> getAllVoted() {
        return votes.stream().map(vote -> vote.player).toList();
    }

    public List<Player> getAllMostVoted() {
        if (votes.isEmpty()) return null;
        int mostVotes = votes.getFirst().votes;

        return votes.stream()
                .filter(vote -> vote.votes == mostVotes)
                .map(vote -> vote.player)
                .toList();
    }

    public int getVotes(Player votedPlayer) {
        Vote result = votes.stream().filter(vote -> vote.player == votedPlayer).findAny().orElse(null);

        if (result == null) return -1;
        return result.votes;
    }

    public void setVotes(Player votedPlayer, int newVotes) {
        Vote result = votes.stream().filter(vote -> vote.player == votedPlayer).findAny().orElse(null);

        if (result == null) return;
        result.votes = newVotes;

        sortVotes();
    }
}