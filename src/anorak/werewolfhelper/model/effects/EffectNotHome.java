package anorak.werewolfhelper.model.effects;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.base.OneTimeAction;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.structure.GamePhase;
import anorak.werewolfhelper.model.structure.GameStructure;

import java.util.List;

public class EffectNotHome extends Effect {
    EffectNotHome(EEffect effectEnum, Player player, GameStructure structure) {
        super(effectEnum, player, structure);

        OneTimeAction.createAndAdd(structure, new IGameEventAction() {
            @Override
            public void respondToGameEvent() {
                player.removeEffect(EEffect.NOT_HOME);
            }

            @Override
            public int getPriority() {
                return 20000;
            }

            @Override
            public List<GamePhase> getPhases() {
                return List.of(GamePhase.POST_NIGHT);
            }
        });
    }

    @Override
    public boolean allowAddOtherEffect(IEffect effect) {
        return !effect.is(EEffectType.KILL_EFFECT);
    }
}