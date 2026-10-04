package anorak.werewolfhelper.model.effects;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.structure.GamePhase;
import anorak.werewolfhelper.model.structure.GameStructure;

import java.util.List;

public class EffectStagedForLynching extends Effect {
    EffectStagedForLynching(EEffect effectEnum, Player player, GameStructure structure) {
        super(effectEnum, player, structure);

        structure.addAction(this, new IGameEventAction() {
            @Override
            public void respondToGameEvent() {
                player.die();
            }

            @Override
            public int getPriority() {
                return Integer.MAX_VALUE;
            }

            @Override
            public List<GamePhase> getPhases() {
                return List.of(GamePhase.POST_VOTE);
            }
        });
    }
}
