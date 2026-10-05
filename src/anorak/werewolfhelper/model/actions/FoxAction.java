package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIBooleanRequest;
import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.controller.base.requests.UIPlayerRequest;
import anorak.werewolfhelper.model.Game;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.effects.EEffect;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.structure.GamePhase;

import java.util.List;

public class FoxAction extends Action {
    boolean usedAction = false;

    public FoxAction() {
        super(EAction.FOX);
    }

    @Override
    public void respondToGameEvent() {
        if (usedAction) return;

        if (new UIBooleanRequest("Fox", "Does the Fox want to sniff out a Werewolf tonight?").request()) {
            Player target = new UIPlayerRequest("Fox", "Choose a Player")
                    .fromPlayers(
                            GlobalState.getInstance().getGame().getPlayers().get()
                    )
                    .request();

            Game.Neighbors neighbors = GlobalState.getInstance().getGame().getAliveNeighbors(target);

            List<Player> sniffingTargets = List.of(neighbors.left().getFirst(), target, neighbors.right().getFirst());

            boolean werewolfAmongGroup = sniffingTargets.stream().anyMatch(player -> player.hasEffect(EEffect.IS_WEREWOLF));

            if(werewolfAmongGroup) {
                new UIDisplayRequest("Fox", "There is a werewolf among the Group").request();
                usedAction = true;
            } else {
                new UIDisplayRequest("Fox", "There is no werewolf among the Group").request();
            }
        }
    }
}
