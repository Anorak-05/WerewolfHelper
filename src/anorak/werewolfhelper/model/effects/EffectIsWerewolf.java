package anorak.werewolfhelper.model.effects;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.WerewolfAction;
import anorak.werewolfhelper.model.structure.GameStructure;

public class EffectIsWerewolf extends Effect {

    EffectIsWerewolf(EEffect effectEnum, Player player, GameStructure structure) {
        super(effectEnum, player, structure);


        structure.addAction(this, new WerewolfAction());
    }
}
