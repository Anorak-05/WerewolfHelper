package anorak.werewolfhelper.model.actions;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.base.ERole;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.structure.GamePhase;
import anorak.werewolfhelper.model.structure.GameStructure;

import java.util.List;

public class ActorSwitchBackAction implements IGameEventAction {
    GameStructure structure;
    Player player;

    public ActorSwitchBackAction(Player player, GameStructure structure) {
        this.structure = structure;
        this.player = player;
    }

    @Override
    public void respondToGameEvent() {
        player.changeRole(ERole.ACTOR.create());

        structure.removeAllActions(this);
    }

    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }

    @Override
    public List<GamePhase> getPhases() {
        return List.of(GamePhase.POST_VOTE);
    }
}
