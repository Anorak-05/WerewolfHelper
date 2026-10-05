package anorak.werewolfhelper.model.base;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.effects.EEffect;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PlayerList {
    private final Stream<Player> players;

    public PlayerList(List<Player> players) {
        this.players = players.stream();
    }

    private PlayerList(Stream<Player> players) {
        this.players = players;
    }

    public PlayerList includeHasEffect(EEffect effectEnum) {
        return new PlayerList(players.filter(p -> p.hasEffect(effectEnum)));
    }

    public PlayerList excludeHasEffect(EEffect effectEnum) {
        return new PlayerList(players.filter(p -> !p.hasEffect(effectEnum)));
    }

    public PlayerList includeHasRole(Class<? extends Role> roleClass) {
        return new PlayerList(players.filter(p -> p.getRole().getClass() == roleClass));
    }

    public PlayerList excludeHasRole(Class<? extends Role> roleClass) {
        return new PlayerList(players.filter(p -> p.getRole().getClass() != roleClass));
    }

    public PlayerList excludePlayer(Player player) {
        return new PlayerList(players.filter(p -> !p.equals(player)));
    }

    public PlayerList includePlayer(Player player) {
        List<Player> playerList = new ArrayList<>(players.toList());
        if (playerList.contains(player)) return this;

        playerList.add(player);
        return new PlayerList(playerList.stream());
    }

    public PlayerList excludeDead() {
        return new PlayerList(players.filter(Player::isAlive));
    }

    public PlayerList excludeLiving() {
        return new PlayerList(players.filter(p -> !p.isAlive()));
    }

    public List<Player> get() {
        return players.toList();
    }
}