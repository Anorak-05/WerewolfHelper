package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.GlobalState;
import anorak.werewolfhelper.controller.base.requests.UIDisplayRequest;
import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.effects.EffectIsWerewolf;
import anorak.werewolfhelper.model.effects.EffectRoleModel;
import anorak.werewolfhelper.model.structure.GamePhase;
import anorak.werewolfhelper.model.structure.GameStructure;

import java.util.List;

public class WildChildTurnEvilAction implements IGameEventAction {
    Player player;
    GameStructure structure;

    public WildChildTurnEvilAction(Player player, GameStructure structure) {
        this.player = player;
        this.structure = structure;
    }

    @Override
    public void respondToGameEvent() {
        boolean roleModelDied = GlobalState.getInstance().getGame().getPlayersByEffect(EffectRoleModel.class).isEmpty();

        if (roleModelDied) {
            new UIDisplayRequest("WildChild",
                    "The wild child turns its back on the Villagers and becomes part of the Werewolf's team")
                    .request();

            player.addEffect(new EffectIsWerewolf(player, structure));
            structure.removeAllActions(this);
        }
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public List<GamePhase> getPhases() {
        return List.of(GamePhase.PLAYER_KILLED);
    }
}