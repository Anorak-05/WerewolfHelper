package anorak.werewolfhelper.model.effects;

import anorak.werewolfhelper.model.Player;
import anorak.werewolfhelper.model.actions.SaviorWerewolfKillSaveAction;
import anorak.werewolfhelper.model.structure.GameStructure;

public class EffectSaved extends Effect {
    EffectSaved(EEffect effectEnum, Player player, GameStructure structure) {
        super(effectEnum, player, structure);

        structure.addAction(this, new SaviorWerewolfKillSaveAction(player));
    }
}