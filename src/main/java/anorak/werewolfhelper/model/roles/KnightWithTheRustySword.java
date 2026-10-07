package anorak.werewolfhelper.model.roles;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.base.Role;
import anorak.werewolfhelper.model.effects.EEffect;

import java.util.List;

public class KnightWithTheRustySword extends Role {

    public KnightWithTheRustySword(String name) {
        super(name);
    }

    @Override
    public void end() {
        super.end();

        List<Player> leftNeighbors = GlobalState.getInstance().getGame().getAliveNeighbors(player).left();

        Player toStrike = leftNeighbors.stream()
                .filter(neighbor -> neighbor.hasEffect(EEffect.IS_WEREWOLF)).findFirst().orElse(null);
        if (toStrike == null) return;

        toStrike.addEffect(EEffect.STRUCK_BY_RUSTY_SWORD);
    }
}