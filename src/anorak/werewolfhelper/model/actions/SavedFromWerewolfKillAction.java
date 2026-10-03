package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.effects.EffectKilledByWerewolf;
import anorak.werewolfhelper.model.structure.GamePhase;

import java.util.List;

public class SavedFromWerewolfKillAction implements IGameEventAction {
    private final Player player;

    public SavedFromWerewolfKillAction(Player player) {
        this.player = player;
    }

    @Override
    public void respondToGameEvent() {
        if (player.removeEffect(EffectKilledByWerewolf.class)) {
            new UIDisplayRequest("Savior", player.getName() + " was saved by the Savior").request();
        }
    }

    @Override
    public int getPriority() {
        return 31;
    }

    @Override
    public List<GamePhase> getPhases() {
        return List.of(GamePhase.NIGHT);
    }
}