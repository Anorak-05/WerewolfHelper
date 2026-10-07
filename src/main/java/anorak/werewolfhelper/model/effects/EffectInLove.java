package anorak.werewolfhelper.model.effects;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.KillRemainingLoverAction;
import anorak.werewolfhelper.model.actions.base.OneTimeAction;
import anorak.werewolfhelper.model.structure.GameStructure;

public class EffectInLove extends Effect {
    EffectInLove(EEffect effectEnum, Player player, GameStructure structure) {
        super(effectEnum, player, structure);

        OneTimeAction.createAndAdd(structure, new KillRemainingLoverAction(player));
    }
}