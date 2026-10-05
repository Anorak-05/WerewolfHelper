package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.Game;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.effects.EEffect;

import java.util.stream.Stream;

public class BearLeaderAction extends Action {
    Player player;

    public BearLeaderAction(Player player) {
        super(EAction.BEAR_LEADER);

        this.player = player;
    }

    @Override
    public void respondToGameEvent() {
        Game.Neighbors neighbors = GlobalState.getInstance().getGame().getAliveNeighbors(player);

        boolean sensingWerewolf = Stream.of(neighbors.left().getFirst(), player, neighbors.right().getFirst())
                .anyMatch(neighbor -> neighbor.hasEffect(EEffect.IS_WEREWOLF));

        if (sensingWerewolf) {
            new UIDisplayRequest("BearLeader", "The Bear leader senses a werewolf").request();
        }
    }
}
