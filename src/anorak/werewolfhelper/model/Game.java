package anorak.werewolfhelper.model;

import anorak.werewolfhelper.model.actions.BasicWinCheckAction;
import anorak.werewolfhelper.model.actions.LynchingAction;
import anorak.werewolfhelper.model.actions.VoteAction;
import anorak.werewolfhelper.model.actions.VoteCaptainAction;
import anorak.werewolfhelper.model.base.Effect;
import anorak.werewolfhelper.model.structure.GamePhase;
import anorak.werewolfhelper.model.structure.GameStructure;
import anorak.werewolfhelper.model.util.VoteResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Game {
    private final List<Player> players;
    private final GameStructure structure;

    private GamePhase overridePhase;
    private GamePhase currentPhase;


    private VoteResult voteResult;

    private boolean gameEnded = false;

    public GameStructure getStructure() {
        return structure;
    }

    public Game() {
        players = new ArrayList<>();
        structure = new GameStructure();
        currentPhase = GamePhase.getFirst();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void setup() {
        for (Player player : players) {
            player.setup(structure);
        }

        structure.addAction(this, new VoteAction());
        structure.addAction(this, new LynchingAction(structure));
        structure.addAction(this, new BasicWinCheckAction());
        structure.addAction(new VoteCaptainAction(structure));

        voteResult = new VoteResult();
    }

    public void start() {
        gameLoop();
    }

    public void OverrideGamePhase(GamePhase overridePhase) {
        this.overridePhase = overridePhase;
    }

    // TODO: rebuild for Stuttering Judge -> add ability to jump to Phase and continue from there
    public void gameLoop() {
        do {
            structure.getEvent(currentPhase).trigger();

            currentPhase = overridePhase == null ? currentPhase.next() : overridePhase;
            overridePhase = null;
        } while (!gameEnded);
    }

    public void endGame() {
        gameEnded = true;
    }

    public Player getPlayerByName(String name) {
        Optional<Player> player = players.stream()
                .filter(Player::isAlive)
                .filter(p -> p.getName().equals(name.trim()))
                .findAny();
        return player.orElse(null);
    }

    public List<Player> getPlayersByEffect(Class<? extends Effect> effect) {
        return players.stream().filter(p -> p.hasEffect(effect)).toList();
    }

    public List<Player> getAlivePlayers() {
        return players.stream().filter(Player::isAlive).toList();
    }

    public VoteResult getVoteResult() {
        return voteResult;
    }

    public void saveVoteResult() {
        this.voteResult = new VoteResult();
    }

    public record Neighbors(List<Player> left, List<Player> right) {}
    public Neighbors getAliveNeighbors(Player player) {
        List<Player> alivePlayers = getAlivePlayers();

        int idx = alivePlayers.indexOf(player);
        int leftIdx, rightIdx;

        List<Player> left = new ArrayList<>();
        List<Player> right = new ArrayList<>();

        leftIdx = (idx - 1 + alivePlayers.size()) % alivePlayers.size();
        Player neighbor;
        while ((neighbor = alivePlayers.get(leftIdx)) != player)
        {
            left.add(neighbor);
            leftIdx = (leftIdx - 1 + alivePlayers.size()) % alivePlayers.size();
        }

        rightIdx = (idx + 1) % alivePlayers.size();
        while ((neighbor = alivePlayers.get(rightIdx)) != player)
        {
            right.add(neighbor);
            rightIdx = (rightIdx + 1) % alivePlayers.size();
        }

        return new Neighbors(left, right);
    }
}