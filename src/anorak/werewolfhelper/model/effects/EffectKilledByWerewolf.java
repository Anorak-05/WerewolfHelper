package anorak.werewolfhelper.model.effects;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.DieByWerewolfAction;
import anorak.werewolfhelper.model.base.IGameEventAction;
import anorak.werewolfhelper.model.structure.GameStructure;

public class EffectKilledByWerewolf extends Effect {
    EffectKilledByWerewolf(EEffect effectEnum, Player player, GameStructure structure) {
        super(effectEnum, player, structure);

        structure.addAction(this, new DieByWerewolfAction(player));
    }
}