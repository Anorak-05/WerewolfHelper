package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIPlayerRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.effects.EEffect;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.structure.GamePhase;

import java.util.List;

public class WerewolfAction implements IGameEventAction {
    @Override
    public void respondToGameEvent() {
        List<Player> allowedToKill = GlobalState.getInstance().getGame().getPlayers()
                .excludeDead()
                .excludeHasEffect(EEffect.IS_WEREWOLF)
                .get();

        Player toKill = new UIPlayerRequest("Werewolf", "Werewolves choose their Victim")
                .fromPlayers(allowedToKill)
                .request();
        toKill.addEffect(EEffect.KILLED_BY_WEREWOLF);
    }

    @Override
    public int getPriority() {
        return 10;
    }

    @Override
    public List<GamePhase> getPhases() {
        return List.of(GamePhase.FIRST_NIGHT, GamePhase.NIGHT);
    }

    @Override
    public boolean isGroupAction() {
        return true;
    }
}