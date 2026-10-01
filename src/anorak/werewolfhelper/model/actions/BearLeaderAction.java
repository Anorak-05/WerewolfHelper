package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.Game;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.effects.EffectIsWerewolf;
import anorak.werewolfhelper.model.structure.GamePhase;

import java.util.List;
import java.util.stream.Stream;

public class BearLeaderAction implements IGameEventAction {
    Player player;

    public BearLeaderAction(Player player) {
        this.player = player;
    }

    @Override
    public void respondToGameEvent() {
        Game.Neighbors neighbors = GlobalState.getInstance().getGame().getAliveNeighbors(player);

        boolean sensingWerewolf = Stream.of(neighbors.left().getFirst(), player, neighbors.right().getFirst())
                .anyMatch(neighbor -> neighbor.hasEffect(EffectIsWerewolf.class));

        if (sensingWerewolf) {
            new UIDisplayRequest("BearLeader", "The Bear leader senses a werewolf").request();
        }
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public List<GamePhase> getPhases() {
        return List.of(GamePhase.MORNING);
    }
}
