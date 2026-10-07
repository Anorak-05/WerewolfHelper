package anorak.werewolfhelper.model.effects;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.structure.GameStructure;

public class EffectKilledByWitchPoison extends Effect {
    EffectKilledByWitchPoison(EEffect effectEnum, Player player, GameStructure structure) {
        super(effectEnum, player, structure);
    }
}
