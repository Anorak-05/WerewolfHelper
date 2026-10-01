package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIPlayerRequest;
import anorak.werewolfhelper.model.structure.GamePhase;
import anorak.werewolfhelper.model.structure.GameStructure;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.effects.EffectSaved;

import java.util.List;

public class SaviorAction implements IGameEventAction {
    GameStructure structure;
    Player lastHealed;

    public SaviorAction(GameStructure structure) {
        this.structure = structure;
    }

    @Override
    public void respondToGameEvent() {
        List<Player> availableToHeal = GlobalState.getInstance().getGame().getAlivePlayers()
                .stream().filter(player -> player != lastHealed).toList();

        Player toHeal = UIPlayerRequest.fromPlayers("Healer",
                "The Healer chooses who to protect from Werewolves this night", availableToHeal).request();
        toHeal.addEffect(new EffectSaved(toHeal, structure));
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public List<GamePhase> getPhases() {
        return List.of(GamePhase.NIGHT);
    }
}
