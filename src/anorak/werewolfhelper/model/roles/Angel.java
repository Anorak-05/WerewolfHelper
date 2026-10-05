package anorak.werewolfhelper.model.roles;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.AngelActivateAction;
import anorak.werewolfhelper.model.actions.AngelDeactiveAction;
import anorak.werewolfhelper.model.actions.base.OneTimeAction;
import anorak.werewolfhelper.model.effects.EEffect;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.base.Role;
import anorak.werewolfhelper.model.structure.GamePhase;
import anorak.werewolfhelper.model.structure.GameStructure;

import java.util.List;

public class Angel extends Role {
    boolean firstMorning = true;

    public Angel(String name) {
        super(name);
    }

    @Override
    public void setup(Player player, GameStructure structure) {
        super.setup(player, structure);

        OneTimeAction.createAndAdd(structure, new AngelActivateAction(player));

        structure.addAction(new AngelDeactiveAction(player));
    }

    @Override
    public void end() {
        super.end();

        if (player.hasEffect(EEffect.ANGEL_ACTIVE)) {
            new UIDisplayRequest("Angel", "Game ended - Angel won").request();
            GlobalState.getInstance().getGame().endGame();
        }
    }
}