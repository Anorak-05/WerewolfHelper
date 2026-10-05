package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.Action;
import anorak.werewolfhelper.model.actions.base.EAction;
import anorak.werewolfhelper.model.effects.EEffect;
import anorak.werewolfhelper.model.structure.GameStructure;

public class WildChildTurnEvilAction extends Action {
    Player player;
    GameStructure structure;

    public WildChildTurnEvilAction(Player player, GameStructure structure) {
        super(EAction.WILD_CHILD_TURN_EVIL);

        this.player = player;
        this.structure = structure;
    }

    @Override
    public void respondToGameEvent() {
        boolean roleModelDied = GlobalState.getInstance().getGame().getPlayers()
                .excludeDead()
                .includeHasEffect(EEffect.ROLE_MODEL)
                .get().isEmpty();

        if (roleModelDied) {
            new UIDisplayRequest("WildChild",
                    "The wild child turns its back on the Villagers and becomes part of the Werewolf's team")
                    .request();

            player.addEffect(EEffect.IS_WEREWOLF);
            structure.removeAllActions(this);
        }
    }
}