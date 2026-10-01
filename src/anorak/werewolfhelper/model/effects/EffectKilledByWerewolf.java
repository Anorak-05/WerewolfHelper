package anorak.werewolfhelper.model.effects;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.base.Effect;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.structure.GamePhase;
import anorak.werewolfhelper.model.structure.GameStructure;

import java.util.List;

public class EffectKilledByWerewolf extends Effect {

    IGameEventAction action;

    public EffectKilledByWerewolf(Player player, GameStructure structure) {
        super(player, structure);

        action = new IGameEventAction() {
            @Override
            public void respondToGameEvent() {
                player.die();
            }

            @Override
            public int getPriority() {
                return 0;
            }

            @Override
            public List<GamePhase> getPhases() {
                return List.of(GamePhase.PRE_MORNING);
            }
        };

        structure.addAction(this, action);
    }
}