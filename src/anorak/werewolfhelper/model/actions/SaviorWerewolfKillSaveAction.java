package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.effects.EEffect;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.structure.GamePhase;

import java.util.List;

public class SaviorWerewolfKillSaveAction extends Action {
    private final Player player;

    public SaviorWerewolfKillSaveAction(Player player) {
        super(EAction.SAVIOR_WEREWOLF_KILL_SAVE);

        this.player = player;
    }

    @Override
    public void respondToGameEvent() {
        if (player.removeEffect(EEffect.KILLED_BY_WEREWOLF)) {
            new UIDisplayRequest("Savior", player.getName() + " was saved by the Savior").request();
        }
    }
}