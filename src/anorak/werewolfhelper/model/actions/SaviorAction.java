package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIPlayerRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.effects.EffectSaved;
import anorak.werewolfhelper.model.structure.GamePhase;
import anorak.werewolfhelper.model.structure.GameStructure;

import java.util.List;

public class SaviorAction implements IGameEventAction {
    GameStructure structure;
    Player lastHealed;

    public SaviorAction(GameStructure structure) {
        this.structure = structure;
    }

    @Override
    public void respondToGameEvent() {
        List<Player> availableToHeal = GlobalState.getInstance().getGame().getPlayers()
                .excludeDead()
                .excludePlayer(lastHealed)
                .get();

        Player toHeal = new UIPlayerRequest("Healer",
                "The Healer chooses who to protect from Werewolves this night")
                .fromPlayers(availableToHeal)
                .request();
        toHeal.addEffect(new EffectSaved(toHeal, structure));
    }

    @Override
    public int getPriority() {
        return 110;
    }

    @Override
    public List<GamePhase> getPhases() {
        return List.of(GamePhase.PRE_FIRST_NIGHT, GamePhase.PRE_NIGHT);
    }
}
